package com.example.lfpms.desafiobrq02;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lfpms.desafiobrq02.Model.Carro;
import com.squareup.picasso.Picasso;

public class ActivityDetalhes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes);
        Carro carroDetalhe;
        ImageView img_detalhes_carro = (ImageView)findViewById(R.id.img_detalhes_carro);
        TextView txt_detalhes_marcamodelo = (TextView)findViewById(R.id.txt_detalhes_marcamodelo);
        TextView txt_detalhes_descricao = (TextView)findViewById(R.id.txt_detalhes_descricao);
        TextView txt_detalhes_preco = (TextView)findViewById(R.id.txt_detalhes_preco);
        Button btn_comprar = (Button)findViewById(R.id.btn_comprar);
        Button btn_voltar = (Button)findViewById(R.id.btn_voltar);

        carroDetalhe = (Carro)getIntent().getSerializableExtra("carro");


        Picasso.get().load(carroDetalhe.getImagem()).into(img_detalhes_carro);
        txt_detalhes_marcamodelo.setText(carroDetalhe.getNome().toString() + " " + carroDetalhe.getMarca().toString());
        txt_detalhes_descricao.setText(carroDetalhe.getDescricao().toString());
        txt_detalhes_preco.setText("R$ " + String.valueOf(carroDetalhe.getPreco() + ",00"));


        btn_voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }
}
