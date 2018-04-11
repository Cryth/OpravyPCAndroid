package com.example.splat.tarika_opravypc;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class aboutActivity extends AppCompatActivity {
    Intent intent;
    TextView header, object, about;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);
        intent = getIntent();
        setTitle("Počítač: "+intent.getStringExtra("pc"));
        header = findViewById(R.id.header);
        object = findViewById(R.id.objectAbout);
        about = findViewById(R.id.aboutRepair);
        Nastav();
    }

    private void Nastav() {
        header.setText("Detail opravy z dňa: "+intent.getStringExtra("date"));
        object.setText("Predmet: "+intent.getStringExtra("object"));
        about.setText(intent.getStringExtra("about"));
    }

    public void zrusenie(View view) {
        finish();
    }
}
