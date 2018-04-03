package com.example.splat.tarika_opravypc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

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
    }

    private void PripojAdapter() {
        String[] from = new String[]{MojaDat.Computers.COLUMN_BRAND, MojaDat.Computers.COLUMN_MODEL};
        int[] into = new int[]{R.id.listBrand, R.id.listModel};
        myAdapt = new SimpleCursorAdapter(this, R.layout.list_computer, dbh.getCursor("SELECT * FROM "+MojaDat.Computers.TABLE_NAME+" WHERE "+MojaDat.Computers.COLUMN_CID+" = ?", new String[]{intent.getStringExtra("index")}), from, into, 0);
        ListView lv = findViewById(R.id.listcomputer);
        lv.setAdapter(myAdapt);
    }
}
