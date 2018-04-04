package com.example.splat.tarika_opravypc;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    DBHelper dbh = new DBHelper(this);
    SimpleCursorAdapter myAdapt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Opravy počítačov - Zákazníci");
        PripojAdapter();
        PridajListener();
    }


    private void PripojAdapter() {
        String[] from = new String[]{MojaDat.Customers.COLUMN_NAME, MojaDat.Customers.COLUMN_EMAIL};
        int[] into = new int[]{R.id.listMeno, R.id.listEmail};
        myAdapt = new SimpleCursorAdapter(this, R.layout.list_customer, dbh.getCursor("SELECT * FROM "+MojaDat.Customers.TABLE_NAME, null), from, into, 0);
        ListView lv = findViewById(R.id.listcustomer);
        lv.setAdapter(myAdapt);
    }

    private void PridajListener() {
        AdapterView.OnItemClickListener clickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ListView lv = findViewById(R.id.listcustomer);
                Cursor c = ((SimpleCursorAdapter)lv.getAdapter()).getCursor();
                c.moveToPosition(i);
                long id = c.getLong(c.getColumnIndex(MojaDat.Customers.COLUMN_ID));
                String meno = c.getString(c.getColumnIndex(MojaDat.Customers.COLUMN_NAME));
                startActivity(new Intent(MainActivity.this, computersActivity.class).putExtra("index", Long.toString(id)).putExtra("meno", meno));
            }
        };
        AdapterView.OnItemLongClickListener longClickListener = new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int position, long l) {
                AlertDialog.Builder builder = new AlertDialog.Builder(adapterView.getContext());
                builder.setMessage("Vymazať naozaj?").setTitle("Mazanie").setPositiveButton("Ano", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        ListView lv = findViewById(R.id.listcustomer);
                        Cursor c = ((SimpleCursorAdapter) lv.getAdapter()).getCursor();
                        c.moveToPosition(position);
                        long ID = c.getLong(c.getColumnIndex(MojaDat.Customers.COLUMN_ID));
                        dbh.deleteCustomer(ID);
                        dbh.deleteComputers(ID);
                        PripojAdapter();
                        dialogInterface.dismiss();
                        Toast.makeText(MainActivity.this, "Zmazané", Toast.LENGTH_SHORT).show();
                    }
                }).setNegativeButton("Nie", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        Toast.makeText(MainActivity.this, "Nemažem", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.show();
                return false;
            }
        };
        ListView lv = findViewById(R.id.listcustomer);
        lv.setOnItemClickListener(clickListener);
        lv.setOnItemLongClickListener(longClickListener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_customer, menu);
        return true;
    }


    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {
            case R.id.addCustomer:
                startActivityForResult(new Intent(MainActivity.this, pridajCustomer.class), 1);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case 1:
                if(resultCode == RESULT_OK){
                    String meno = data.getStringExtra("meno");
                    String email = data.getStringExtra("email");
                    dbh.addCustomer(new Customer(0, meno, email));
                    PripojAdapter();
                }
                break;
        }

    }
}
