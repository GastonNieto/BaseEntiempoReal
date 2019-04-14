package com.example.recyclerviewadapter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.recyclerviewadapter.Adapter.AdapterRecycler;
import com.example.recyclerviewadapter.Retrofit.ApiRetrofit;
import com.example.recyclerviewadapter.modelo.Posts;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    ArrayList<Posts> arrayList;
    RecyclerView recyclerView;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.error);
        recyclerView = findViewById(R.id.rvRecycler);
        arrayList = new ArrayList<>();
        //creo una instancia de mi recyclerview para pasarle un layout
        //le seteo un linear vertical
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
      /*  //relleno la lista
        for (int i = 0; i <= 50; i++) {
            listDatos.add("Dato: " + i + " cargado..");
        }*/
        //envio lista como parametros al adaptador
        //AdapterRecycler adapterRecycler = new AdapterRecycler(listDatos);
        // le envio el adaptador a nuestro recycler
        //recyclerView.setAdapter(adapterRecycler);
        getsPotst();
    }

    private void getsPotst() {
        //creo una instancia de retrofit
        //ademas debo agregar un coverterfactory (Gson)
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.0.13/android/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //llamamos a la interfaz y paso por parametro la clase de la interfas .class
        ApiRetrofit apiRetrofit = retrofit.create(ApiRetrofit.class);
        // este call tiene que ser igual al de la interfaz
        // y lo refenrenciamos con el de la interfaz
        Call<List<Posts>> call = apiRetrofit.getPost();
        //execute lo hace en el main thread no lo backcroud
        //enqueue lo hace en otro thread

        call.enqueue(new Callback<List<Posts>>() {
            @Override
            public void onResponse(Call<List<Posts>> call, Response<List<Posts>> response) {
                //si la respuesta no se pudo obtener ejemplo no esta autenticado para obetner la informacion
                if (!response.isSuccessful()) {
                    int respuesta = response.code();
                    textView.setText(String.valueOf(respuesta));
                    return;
                }
                //Gson parsea los datos automaticamente y mediante response.body extraemos los datos en la list
                List<Posts> listDatos = response.body();
                for(Posts posts:listDatos ){
                    arrayList.add(posts);
                }
                AdapterRecycler adapterRecycler = new AdapterRecycler(arrayList);
                recyclerView.setAdapter(adapterRecycler);
                adapterRecycler.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Posts>> call, Throwable t) {
                String failure = t.getMessage().toString();
                textView.setText(failure);
               // Toast.makeText(this,failure,Toast.LENGTH_LONG).show();
            }
        });
    }
}
