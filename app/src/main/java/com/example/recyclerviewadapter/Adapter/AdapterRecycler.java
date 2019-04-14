package com.example.recyclerviewadapter.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.recyclerviewadapter.R;
import com.example.recyclerviewadapter.modelo.Posts;

import java.util.ArrayList;
import java.util.List;

public class AdapterRecycler extends RecyclerView.Adapter<AdapterRecycler.ViewHolderDatos> {
    ArrayList<Posts> listDatos;

    public AdapterRecycler(ArrayList<Posts> listDatos) {
        this.listDatos = listDatos;
        notifyDataSetChanged();
    }

    //enlaza este adaptador con el archivo item_recycler.xml
    @NonNull
    @Override
    public AdapterRecycler.ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //Inflo el adapter con mi viewholder datos, pasandole como parametros un contexto y el Layout
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_recycler, null, false);
        return new AdapterRecycler.ViewHolderDatos(view);
    }
//se encarga de establecer la comunicacion entre nuestro adaptador y el viewholder

    @Override
    public void onBindViewHolder(@NonNull AdapterRecycler.ViewHolderDatos viewHolderDatos, int i) {
        int e = listDatos.get(i).getId();
        viewHolderDatos.datos.setText(String.valueOf(e));
    }

    // a este adaptador le llega una lista de datos, por lo tanto se debe aclara su tamaño
    @Override
    public int getItemCount() {
        return listDatos.size();
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder {
        // hago referencia a los elementos del item_recycler
        TextView datos;

        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);
            datos = itemView.findViewById(R.id.tvItem);
        }

    }
}
