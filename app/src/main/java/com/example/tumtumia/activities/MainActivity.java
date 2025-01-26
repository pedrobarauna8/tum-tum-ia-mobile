package com.example.tumtumia.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tumtumia.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner spGender = findViewById(R.id.sp_gender);
        Spinner spBloodGlucose = findViewById(R.id.sp_bloodGlucose);

        configureSpinner(spGender, spBloodGlucose);

        Button btnResume = findViewById(R.id.btn_resume);

        btnResume.setOnClickListener(v -> {

            EditText etAge = findViewById(R.id.et_age);
            EditText etBloodPressure = findViewById(R.id.et_bloodPressure);
            EditText etCholesterol = findViewById(R.id.et_cholesterol);
            EditText etMaxHR = findViewById(R.id.et_maxHR);

            var age = Integer.valueOf(etAge.getText().toString());
            var gender = spGender.getSelectedItem().toString();
            var bloodPressure = Integer.valueOf(etBloodPressure.getText().toString());
            var cholesterol = Integer.valueOf(etCholesterol.getText().toString());
            var bloodGlucose = spBloodGlucose.getSelectedItem().toString();
            var maxHR = Integer.valueOf(etMaxHR.getText().toString());

            Intent intent = new Intent(MainActivity.this, FormActivity.class);

            intent.putExtra("age", age);
            intent.putExtra("gender", gender);
            intent.putExtra("bloodPressure", bloodPressure);
            intent.putExtra("bloodGlucose", bloodGlucose);
            intent.putExtra("cholesterol", cholesterol);
            intent.putExtra("maxHR", maxHR);

            startActivity(intent);
        });
    }

    private void configureSpinner(Spinner spGender, Spinner spBloodGlucose) {

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.gender,
                R.layout.spinner_item
        );

        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(
                this,
                R.array.blood_glucose,
                R.layout.spinner_item
        );

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spGender.setAdapter(adapter);
        spBloodGlucose.setAdapter(adapter1);
    }
}
