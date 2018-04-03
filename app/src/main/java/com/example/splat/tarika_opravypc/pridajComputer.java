package com.example.splat.tarika_opravypc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class pridajComputer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pridaj_computer);
        setTitle("Pridanie počítača");
    }


    public void pridanie(View view) {
        EditText brand = findViewById(R.id.brandEnter);
        EditText model = findViewById(R.id.modelEnter);
        if(TextUtils.isEmpty(brand.getText()) || TextUtils.isEmpty(model.getText())){
            Toast.makeText(this, "Zadajte všetky údaje!", Toast.LENGTH_SHORT).show();
        }else{
            Intent intent = new Intent();
            intent.putExtra("brand", brand.getText().toString());
            intent.putExtra("model", model.getText().toString());
            setResult(RESULT_OK, intent);
            finish();
        }
    }

    public void zrusenie(View view) {
        finish();
    }
}
