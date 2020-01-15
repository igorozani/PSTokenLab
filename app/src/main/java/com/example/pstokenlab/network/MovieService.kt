package com.example.pstokenlab.network

import com.example.pstokenlab.entities.Movie
import com.example.pstokenlab.entities.MovieList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieService {
    companion object
    @GET("movies")
    fun getList(): Call<List<MovieList>>

    @GET("movies/{id}")
    fun getMovie(@Path("id") id: String):Call<Movie>


}