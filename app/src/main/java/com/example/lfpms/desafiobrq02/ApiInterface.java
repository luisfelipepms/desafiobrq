package com.example.lfpms.desafiobrq02;

import com.example.lfpms.desafiobrq02.Model.Carro;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("carro")
    Call<List<Carro>> getCarros();


}
