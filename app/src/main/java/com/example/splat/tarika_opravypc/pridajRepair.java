package com.example.splat.tarika_opravypc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.w3c.dom.Text;

public class pridajRepair extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pridaj_repair);
        Intent intent = getIntent();
        setTitle("Pridanie opravy: "+intent.getStringExtra("pc"));
    }
    public void pridanie(View view) {
        EditText object = findViewById(R.id.objectEnter);
        EditText about = findViewById(R.id.aboutEnter);
        EditText date = findViewById(R.id.dateEnter);
        if(TextUtils.isEmpty(object.getText()) || TextUtils.isEmpty(about.getText()) || TextUtils.isEmpty(date.getText())){
            Toast.makeText(this, "Zadajte všetky údaje!", Toast.LENGTH_SHORT).show();
        }else{
            Intent intent = new Intent();
            intent.putExtra("object", object.getText().toString());
            intent.putExtra("about", about.getText().toString());
            intent.putExtra("date", date.getText().toString());
            setResult(RESULT_OK, intent);
            finish();
        }
    }

    public void zrusenie(View view) {
        finish();
    }
}

