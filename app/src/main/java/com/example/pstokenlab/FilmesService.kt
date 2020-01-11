package com.example.pstokenlab

import retrofit2.Call
import retrofit2.http.GET

interface FilmesService {
    @GET("movies")
    fun list(): Call<List<Filme>>
}