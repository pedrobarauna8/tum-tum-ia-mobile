package com.example.tumtumia.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tumtumia.R;
import com.example.tumtumia.config.ApiClient;
import com.example.tumtumia.dto.ModelRequestDTO;
import com.example.tumtumia.dto.ModelResponseDTO;
import com.example.tumtumia.service.ApiService;

import java.util.Optional;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FormActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        Spinner spChestPainType = findViewById(R.id.sp_chest_pain_type);
        Spinner spRestECG = findViewById(R.id.sp_rest_ECG);
        Spinner spExerciseAngina = findViewById(R.id.sp_exercise_angina);
        Spinner spECGPattern = findViewById(R.id.sp_ECG_pattern);
        EditText etInsufficientBloodFlow = findViewById(R.id.et_insufficient_blood_flow);

        configureSpinner(spChestPainType, spRestECG, spExerciseAngina, spECGPattern);

        Button btnRateCardiacRisk = findViewById(R.id.btn_rate_cardiac_risk);

        btnRateCardiacRisk.setOnClickListener(l -> {

            Integer age = getIntent().getIntExtra("age", 0);
            String gender = getIntent().getStringExtra("gender");
            Integer bloodPressure = getIntent().getIntExtra("bloodPressure", 0);
            Integer cholesterol = getIntent().getIntExtra("cholesterol", 0);
            String bloodGlucose = getIntent().getStringExtra("bloodGlucose");
            Integer maxHR = getIntent().getIntExtra("maxHR", 0);
            String chestPainType = spChestPainType.getSelectedItem().toString();
            String restECG = spRestECG.getSelectedItem().toString();
            String exerciseAngina = spExerciseAngina.getSelectedItem().toString();
            Double insufficientBloodFlow = Double.valueOf(etInsufficientBloodFlow.getText().toString());
            String patternECG = spECGPattern.getSelectedItem().toString();

            var modelRequest = new ModelRequestDTO(age, gender, bloodPressure, cholesterol, bloodGlucose, maxHR, chestPainType, restECG, exerciseAngina, insufficientBloodFlow, patternECG);

            getCardiacRiskAndRedirectPage(modelRequest);
        });
    }

    private void getCardiacRiskAndRedirectPage(ModelRequestDTO modelRequestDTO) {
        ApiService apiService = ApiClient.getClient().create(ApiService.class);

        Call<ModelResponseDTO> call = apiService.getUsuario(modelRequestDTO);

        call.enqueue(new Callback<>() {

            @Override
            public void onResponse(@NonNull Call<ModelResponseDTO> call, @NonNull Response<ModelResponseDTO> response) {
                if (response.isSuccessful()) {

                    var modelResponse = response.body();

                    var result = Optional.ofNullable(modelResponse)
                            .map(ModelResponseDTO::getResult)
                            .orElse(null);

                    Intent intent = null;

                    if (Boolean.TRUE == result) {
                        intent = new Intent(FormActivity.this, PositiveResultActivity.class);
                    }
                    if (Boolean.FALSE == result) {
                        intent = new Intent(FormActivity.this, NegativeResultActivity.class);
                    }

                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(@NonNull Call<ModelResponseDTO> call, @NonNull Throwable t) {
            }
        });
    }

    private void configureSpinner(Spinner spChestPainType, Spinner spRestECG, Spinner spExerciseAngina, Spinner spECGPattern) {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.chest_pain_type_array,
                R.layout.spinner_item
        );

        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(
                this,
                R.array.rest_ECG,
                R.layout.spinner_item
        );

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(
                this,
                R.array.exercise_angina,
                R.layout.spinner_item
        );

        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(
                this,
                R.array.pattern_ECG,
                R.layout.spinner_item
        );


        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spChestPainType.setAdapter(adapter);
        spRestECG.setAdapter(adapter1);
        spExerciseAngina.setAdapter(adapter2);
        spECGPattern.setAdapter(adapter3);
    }
}
