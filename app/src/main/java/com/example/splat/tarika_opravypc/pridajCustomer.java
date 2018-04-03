package com.example.splat.tarika_opravypc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class pridajCustomer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pridaj_customer);
        setTitle("Pridanie zákazníka");
    }

    public void pridanie(View view) {
        EditText meno = findViewById(R.id.menoEnter);
        EditText email = findViewById(R.id.emailEnter);
        if(TextUtils.isEmpty(meno.getText()) || TextUtils.isEmpty(email.getText())){
            Toast.makeText(this, "Zadajte všetky údaje!", Toast.LENGTH_SHORT).show();
        }else{
            Intent intent = new Intent();
            intent.putExtra("meno", meno.getText().toString());
            intent.putExtra("email", email.getText().toString());
            setResult(RESULT_OK, intent);
            finish();
        }
    }

    public void zrusenie(View view) {
        finish();
    }
}
