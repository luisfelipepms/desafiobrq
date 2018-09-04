package com.example.lfpms.desafiobrq02;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lfpms.desafiobrq02.Model.Carro;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {


    Context context;

    List<Carro> carroLista;

    public RecyclerAdapter(Context context, List<Carro> carrosLista){
        this.context = context;
        this.carroLista = carrosLista;
    }

    public void setCarrosLista(List<Carro> carroLista){
        this.carroLista = carroLista;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recyclerview_adapter, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.txt_marcanome.setText(carroLista.get(position).getMarca().toString() + " " + carroLista.get(position).getNome().toString());
        holder.txt_preco.setText("R$"+String.valueOf(carroLista.get(position).getPreco()) + ",00");
        Picasso.get().load(carroLista.get(position).getImagem()).into(holder.img_carro);
        holder.btn_detalhes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(v.getContext(), "Modelo: " + carroLista.get(position).getNome().toString(), Toast.LENGTH_LONG).show();
                Intent intent = new Intent(v.getContext(), ActivityDetalhes.class);
                intent.putExtra("carro", carroLista.get(position)).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(carroLista != null){
            return carroLista.size();
        }
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView txt_marcanome, txt_preco;
        ImageView img_carro;
        Button btn_detalhes;

        public MyViewHolder(View itemView){
            super(itemView);
            txt_marcanome = (TextView)itemView.findViewById(R.id.txt_marcanome);
            txt_preco = (TextView)itemView.findViewById(R.id.txt_preco);
            img_carro = (ImageView)itemView.findViewById(R.id.img_carro);
            btn_detalhes = (Button)itemView.findViewById(R.id.btb_detalhes);
        }
        
    }

}
