package com.example.tumtumia.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tumtumia.R;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnAnalyseAgain = findViewById(R.id.btn_resume);

        btnAnalyseAgain.setOnClickListener(l -> {
            Intent intent = new Intent(MainActivity.this, FormActivity1.class);
            startActivity(intent);
        });
    }
}
