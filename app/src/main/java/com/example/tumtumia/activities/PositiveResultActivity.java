package com.example.tumtumia.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tumtumia.R;

public class PositiveResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.positive_result);

        Button btnAnalyzeAgain = findViewById(R.id.btn_analyze_again);

        btnAnalyzeAgain.setOnClickListener(l -> {
            Intent intent = new Intent(PositiveResultActivity.this, FormActivity1.class);
            startActivity(intent);
        });
    }
}