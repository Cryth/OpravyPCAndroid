package com.example.splat.tarika_opravypc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

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
        //PridajListener();
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
}
