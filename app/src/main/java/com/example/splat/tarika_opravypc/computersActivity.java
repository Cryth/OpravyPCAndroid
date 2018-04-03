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
import android.widget.TextView;
import android.widget.Toast;

public class computersActivity extends AppCompatActivity {
    Intent intent;
    DBHelper dbh = new DBHelper(this);
    SimpleCursorAdapter myAdapt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_computers);
        intent = getIntent();
        setTitle("Počítače zákazníka: ");
        TextView tv = findViewById(R.id.nadpis);
        tv.setText(intent.getStringExtra("meno"));
        PripojAdapter();
        PridajListener();
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
                //startActivity(new Intent(computersActivity.this, repairsActivity.class).putExtra("index", Long.toString(id)).putExtra("meno", meno));
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
                        dbh.deleteComputer(ID);
                        PripojAdapter();
                        dialogInterface.dismiss();
                        Toast.makeText(computersActivity.this, "Zmazané", Toast.LENGTH_SHORT).show();
                    }
                }).setNegativeButton("Nie", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        Toast.makeText(computersActivity.this, "Nemažem", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.show();
                return false;
            }
        };
        ListView lv = findViewById(R.id.listcomputer);
        lv.setOnItemClickListener(clickListener);
        lv.setOnItemLongClickListener(longClickListener);
    }

    private void PripojAdapter() {
        String[] from = new String[]{MojaDat.Computers.COLUMN_BRAND, MojaDat.Computers.COLUMN_MODEL};
        int[] into = new int[]{R.id.listBrand, R.id.listModel};
        myAdapt = new SimpleCursorAdapter(this, R.layout.list_computer, dbh.getCursor("SELECT * FROM "+MojaDat.Computers.TABLE_NAME+" WHERE "+MojaDat.Computers.COLUMN_CID+" = ?", new String[]{intent.getStringExtra("index")}), from, into, 0);
        ListView lv = findViewById(R.id.listcomputer);
        lv.setAdapter(myAdapt);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_computer, menu);
        return true;
    }


    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {
            case R.id.addComputer:
                startActivityForResult(new Intent(computersActivity.this, pridajComputer.class), 1);
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
                    String brand = data.getStringExtra("brand");
                    String model = data.getStringExtra("model");
                    dbh.addComputer(new Computer(0, Long.parseLong(intent.getStringExtra("index")), brand, model));
                    PripojAdapter();
                }
                break;
        }

    }
}
