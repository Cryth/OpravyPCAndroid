package com.example.splat.tarika_opravypc;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

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
        //PridajCustomers();
        PridajListener();
    }



    private void PridajCustomers() {
        Customer c = new Customer(0, "Patrik Tariška", "pattar19@icloud.com");
        dbh.addCustomer(c);
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
                startActivity(new Intent(getApplicationContext(), computersActivity.class).putExtra("index", id));
            }
        };
        ListView lv = findViewById(R.id.listcustomer);
        lv.setOnItemClickListener(clickListener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_customer, menu);
        return true;
    }


    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {
            case R.id.addCustomer:
                //doplnit activitu
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
