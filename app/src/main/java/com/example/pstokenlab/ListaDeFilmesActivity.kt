package com.example.pstokenlab

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_lista_de_filmes.*
import kotlinx.android.synthetic.main.progress_bar.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.Serializable

class ListaDeFilmesActivity: AppCompatActivity () {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_de_filmes)

        pbLoading.visibility = ProgressBar.VISIBLE

        val call = RetrofitInitializer().createFilmesService().pegaLista()
        call.enqueue(object : Callback<List<ListaDeFilmes>?> {
            override fun onResponse(call: Call<List<ListaDeFilmes>?>?, response: Response<List<ListaDeFilmes>?>?) {
                pbLoading.visibility = ProgressBar.INVISIBLE
                configuraLista(response!!.body()!!)
            }

            override fun onFailure(
                call: Call<List<ListaDeFilmes>?>?,
                t: Throwable?
            ) {
                pbLoading.visibility = ProgressBar.INVISIBLE
                Toast.makeText(this@ListaDeFilmesActivity,"Falha. Tente novamente mais tarde.", Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun configuraLista(filmes: List<ListaDeFilmes>) {
        val adapter = ListaDeFilmesAdapter(filmes, this)
        adapter.configuraClique {
            pbLoading.visibility = ProgressBar.VISIBLE
            val call = RetrofitInitializer().createFilmesService().pegaFilme(it.id)
            call.enqueue(object : Callback<Filme?> {
                override fun onResponse(call: Call<Filme?>?, response: Response<Filme?>?) {
                    pbLoading.visibility = ProgressBar.INVISIBLE
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
                    pbLoading.visibility = ProgressBar.INVISIBLE
                    Toast.makeText(this@ListaDeFilmesActivity,"Falha. Tente novamente mais tarde.", Toast.LENGTH_LONG).show()
                }
            })
        }
        lista_de_filmes_recyclerview.adapter = adapter
        lista_de_filmes_recyclerview.layoutManager = LinearLayoutManager(this)
        }
}


