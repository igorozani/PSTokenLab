package com.example.pstokenlab

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface FilmesService {
    @GET("movies")
    fun pegaLista(): Call<List<ListaDeFilmes>>

    @GET("movies/{id}")
    fun pegaFilme(@Path("id") id: String):Call<Filme>


}