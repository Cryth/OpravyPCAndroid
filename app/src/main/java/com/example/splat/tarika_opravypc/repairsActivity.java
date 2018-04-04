package com.example.splat.tarika_opravypc;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class repairsActivity extends AppCompatActivity {
    Intent intent;
    DBHelper dbh = new DBHelper(this);
    SimpleCursorAdapter myAdapt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repairs);
        intent = getIntent();
        setTitle(intent.getStringExtra("pc"));
        PripojAdapter();
        PridajListener();
    }

    private void PripojAdapter() {
        String[] from = new String[]{MojaDat.Repairs.COLUMN_OBJECT, MojaDat.Repairs.COLUMN_DATE};
        int[] into = new int[]{R.id.object, R.id.date};
        myAdapt = new SimpleCursorAdapter(this, R.layout.list_repair, dbh.getCursor("SELECT * FROM "+MojaDat.Repairs.TABLE_NAME+" WHERE "+MojaDat.Repairs.COLUMN_COID+" = ?", new String[]{intent.getStringExtra("index")}), from, into, 0);
        ListView lv = findViewById(R.id.listrepair);
        lv.setAdapter(myAdapt);

    }

    private void PridajListener() {
        AdapterView.OnItemClickListener clickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ListView lv = findViewById(R.id.listrepair);
                Cursor c = ((SimpleCursorAdapter)lv.getAdapter()).getCursor();
                c.moveToPosition(i);
                long id = c.getLong(c.getColumnIndex(MojaDat.Repairs.COLUMN_ID));
                String object = c.getString(c.getColumnIndex(MojaDat.Repairs.COLUMN_OBJECT));
                String about = c.getString(c.getColumnIndex(MojaDat.Repairs.COLUMN_ABOUT));
                //startActivity(new Intent(repairsActivity.this, aboutActivity.class).putExtra("index", Long.toString(id)).putExtra("pc", pc));
            }
        };
        AdapterView.OnItemLongClickListener longClickListener = new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int position, long l) {
                AlertDialog.Builder builder = new AlertDialog.Builder(adapterView.getContext());
                builder.setMessage("Vymazať naozaj?").setTitle("Mazanie").setPositiveButton("Ano", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        ListView lv = findViewById(R.id.listrepair);
                        Cursor c = ((SimpleCursorAdapter) lv.getAdapter()).getCursor();
                        c.moveToPosition(position);
                        long ID = c.getLong(c.getColumnIndex(MojaDat.Repairs.COLUMN_ID));
                        dbh.deleteRepair(ID);
                        PripojAdapter();
                        dialogInterface.dismiss();
                        Toast.makeText(repairsActivity.this, "Zmazané", Toast.LENGTH_SHORT).show();
                    }
                }).setNegativeButton("Nie", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        Toast.makeText(repairsActivity.this, "Nemažem", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.show();
                return false;
            }
        };
        ListView lv = findViewById(R.id.listrepair);
        lv.setOnItemClickListener(clickListener);
        lv.setOnItemLongClickListener(longClickListener);
    }
}
