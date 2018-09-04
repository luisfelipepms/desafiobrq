package com.example.lfpms.desafiobrq02;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.example.lfpms.desafiobrq02.Model.Carro;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "LUIS";
    List<Carro> carroList;
    private static List<Carro> carrinho;
    RecyclerView recyclerView;
    RecyclerAdapter recyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ProgressBar progressBar = (ProgressBar)findViewById(R.id.progressBar);
        FloatingActionButton fab_carrinho = (FloatingActionButton)findViewById(R.id.fab_carrinho);
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
                recyclerAdapter.setCarrosLista(carroList);
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<List<Carro>> call, Throwable t) {

            }
        });


        fab_carrinho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ActivityCarrinho.class);
                startActivity(intent);
            }
        });

    }


}
