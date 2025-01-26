package com.example.tumtumia.service;

import com.example.tumtumia.dto.ModelRequestDTO;
import com.example.tumtumia.dto.ModelResponseDTO;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {
    @POST("predict")
    Call<ModelResponseDTO> getUsuario(@Body ModelRequestDTO modelRequestDTO);
}
