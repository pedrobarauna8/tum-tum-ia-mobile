package com.example.tumtumia.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tumtumia.R;

public class NegativeResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.negative_result);

        Button btnAnalyseAgain = findViewById(R.id.btn_analyze_again);

        btnAnalyseAgain.setOnClickListener(l -> {
            Intent intent = new Intent(NegativeResultActivity.this, FormActivity1.class);
            startActivity(intent);
        });
    }
}