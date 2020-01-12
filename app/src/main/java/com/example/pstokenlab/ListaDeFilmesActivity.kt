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

        val call = RetrofitInitializer().createFilmesService().pegaLista()
        call.enqueue(object : Callback<List<ListaDeFilmes>?> {
            override fun onResponse(call: Call<List<ListaDeFilmes>?>?, response: Response<List<ListaDeFilmes>?>?) {
                configuraLista(response!!.body()!!)
            }

            override fun onFailure(
                call: Call<List<ListaDeFilmes>?>?,
                t: Throwable?
            ) {
                Log.e("onFailure error", t?.message)
            }
        })
    }

    private fun configuraLista(filmes: List<ListaDeFilmes>) {
        val adapter = ListaDeFilmesAdapter(filmes, this)
        adapter.configuraClique {
            val call = RetrofitInitializer().createFilmesService().pegaFilme(it.id)
            call.enqueue(object : Callback<Filme?> {
                override fun onResponse(call: Call<Filme?>?, response: Response<Filme?>?) {
                    configuraFilme(response!!.body())
                }

                private fun configuraFilme(filme: Filme?) {
                    val detalhesFilme = Intent (this@ListaDeFilmesActivity, DetalhesFilme::class.java)
                    detalhesFilme.putExtra("filme", filme as Serializable)
                    startActivity(detalhesFilme)
                }

                override fun onFailure(
                    call: Call<Filme?>?,
                    t: Throwable?
                ) {
                    Log.e("onFailure error", t?.message)
                }
            })
        }
        lista_de_filmes_recyclerview.adapter = adapter
        lista_de_filmes_recyclerview.layoutManager = LinearLayoutManager(this)
        }
}


