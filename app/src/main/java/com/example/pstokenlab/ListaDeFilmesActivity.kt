package com.example.pstokenlab

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_lista_de_filmes.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListaDeFilmesActivity: AppCompatActivity () {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_de_filmes)

        val call = RetrofitInitializer().createFilmesService().list()
        call.enqueue(object : Callback<List<Filme>?> {
            override fun onResponse(call: Call<List<Filme>?>?, response: Response<List<Filme>?>?) {
                configuraLista(response!!.body()!!)
            }

            override fun onFailure(
                call: Call<List<Filme>?>?,
                t: Throwable?
            ) {
                Log.e("onFailure error", t?.message)
            }
        })


    }

    private fun configuraLista(filmes: List<Filme>) {
        lista_de_filmes_recyclerview.apply {
            layoutManager = LinearLayoutManager(this@ListaDeFilmesActivity)
            adapter = ListaDeFilmesAdapter(filmes, this@ListaDeFilmesActivity)
        }
    }
}


