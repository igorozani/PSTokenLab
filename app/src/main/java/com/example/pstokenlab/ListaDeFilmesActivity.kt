package com.example.pstokenlab

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_lista_de_filmes.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.Serializable

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
        val adapter = ListaDeFilmesAdapter(filmes, this)
        adapter.configuraClique {
            val detalhesFilme = Intent (this, DetalhesFilme::class.java)
            detalhesFilme.putExtra("filme", it as Serializable)
            startActivity(detalhesFilme)
        }
        lista_de_filmes_recyclerview.adapter = adapter
        lista_de_filmes_recyclerview.layoutManager = LinearLayoutManager(this)
        }
    }


