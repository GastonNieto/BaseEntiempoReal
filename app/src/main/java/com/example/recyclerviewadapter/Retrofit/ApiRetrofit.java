package com.example.recyclerviewadapter.Retrofit;

//interfaz que permite indicar que metdo vamos a usar para esta pagina

import com.example.recyclerviewadapter.modelo.Posts;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiRetrofit {
    //metodod encargadoa de obetner toda la informacion
    //envia un request al servidor y devuelve una respuesta
    //creo una lista de mi modelo
    @GET("posts")//indico operacion y de donde buscar en el servidor
    Call<List<Posts>> getPost();
}
