package com.example.tumtumia.dto;

import java.util.List;

public class ModelRequestDTO {
    private final Integer age;
    private final Integer gender;
    private final Integer bloodPressure;
    private final Integer cholesterol;
    private final Integer bloodGlucose;
    private final Integer maxHR;
    private final List<Integer> chestPainType;
    private final List<Integer> restECG;
    private final Integer exerciseAngina;
    private final Double insufficientBloodFlow;
    private final List<Integer> patternECG;

    public ModelRequestDTO(Integer age,
                           String gender,
                           Integer bloodPressure,
                           Integer cholesterol,
                           String bloodGlucose,
                           Integer maxHR,
                           String chestPainType,
                           String ecg_repouso,
                           String exerciseAngina,
                           Double insufficientBloodFlow,
                           String patternECG) {
        this.age = age;
        this.gender = convertBoolean("Masculino".equals(gender));
        this.bloodPressure = bloodPressure;
        this.cholesterol = cholesterol;
        this.bloodGlucose = convertBoolean("Normal (menor que 120 mg/dL)".equals(bloodGlucose));
        this.maxHR = maxHR;
        this.chestPainType = convertChestPainType(chestPainType);
        this.restECG = convertRestECG(ecg_repouso);
        this.exerciseAngina = convertBoolean("Sim".equals(exerciseAngina));
        this.insufficientBloodFlow = insufficientBloodFlow;
        this.patternECG = convertECGPattern(patternECG);
    }

    private static int convertBoolean(Boolean bool) {
        return bool ? 1 : 0;
    }

    public static List<Integer> convertChestPainType(String chestPainType) {
        if ("Sem Dor".equals(chestPainType)) {
            return List.of(0, 0, 0);
        } else if ("Angina Típica".equals(chestPainType)) {
            return List.of(1, 0, 0);
        } else if ("Dor Não-Anginal".equals(chestPainType)) {
            return List.of(0, 1, 0);
        } else if ("Angina Atípica".equals(chestPainType)) {
            return List.of(0, 0, 1);
        } else {
            return List.of(0, 0, 0);
        }
    }

    public static List<Integer> convertRestECG(String ecgRepouso) {
        if ("Normal".equals(ecgRepouso)) {
            return List.of(1, 0);
        } else if ("Anormalidade ST".equals(ecgRepouso)) {
            return List.of(0, 1);
        } else if ("Hipertrofia VE".equals(ecgRepouso)) {
            return List.of(0, 0);
        } else {
            return List.of(0, 0);
        }
    }

    public static List<Integer> convertECGPattern(String ECGPattern) {
        if ("Elevação Suave do Segmento ST".equals(ECGPattern)) {
            return List.of(0, 1);
        } else if ("Segmento ST Plano".equals(ECGPattern)) {
            return List.of(1, 0);
        } else if ("Depressão do Segmento ST".equals(ECGPattern)) {
            return List.of(0, 0);
        } else {
            return List.of(0, 0);
        }
    }
}