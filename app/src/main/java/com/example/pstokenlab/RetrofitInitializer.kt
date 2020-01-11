package com.example.pstokenlab
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInitializer {

       val retrofit =  Retrofit.Builder()
            .baseUrl("https://desafio-mobile.nyc3.digitaloceanspaces.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        fun createFilmesService() = retrofit.create(FilmesService::class.java)

}