package com.example.lfpms.desafiobrq02;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.lfpms.desafiobrq02.Model.Carro;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "LUIS";
    List<Carro> carroList;
    RecyclerView recyclerView;
    RecyclerAdapter recyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        carroList = new ArrayList<>();

        recyclerView = (RecyclerView)findViewById(R.id.recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerAdapter = new RecyclerAdapter(getApplicationContext(), carroList);
        recyclerView.setAdapter(recyclerAdapter);

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<List<Carro>> call = apiService.getCarros();

        call.enqueue(new Callback<List<Carro>>() {
            @Override
            public void onResponse(Call<List<Carro>> call, Response<List<Carro>> response) {
                carroList = response.body();
                Log.d(TAG, "Response "+carroList);
                recyclerAdapter.setCarrosLista(carroList);
            }

            @Override
            public void onFailure(Call<List<Carro>> call, Throwable t) {
                Log.d(TAG, "Response: " + t.toString());
            }
        });




    }
}
