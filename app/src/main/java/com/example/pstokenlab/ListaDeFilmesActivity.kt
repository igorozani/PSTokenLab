package com.example.pstokenlab

import android.content.Intent
import android.os.Bundle
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import io.paperdb.Paper
import kotlinx.android.synthetic.main.activity_lista_de_filmes.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.Serializable

class ListaDeFilmesActivity: AppCompatActivity () {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_de_filmes)

        pbLoading.visibility = ProgressBar.VISIBLE

        Paper.init(this)
        var filmes: List<ListaDeFilmes>?

        val call = RetrofitInitializer().createFilmesService().pegaLista()
        call.enqueue(object : Callback<List<ListaDeFilmes>?> {
            override fun onResponse(call: Call<List<ListaDeFilmes>?>?, response: Response<List<ListaDeFilmes>?>?) {
                filmes = response!!.body()
                if (filmes != null) {
                    Paper.book().write("ListaDeFilmes", filmes)
                    pbLoading.visibility = ProgressBar.INVISIBLE
                    mostraLista(filmes!!)
                } else {
                    filmes = Paper.book().read("ListaDeFilmes")
                    if (filmes != null){
                        Toast.makeText(this@ListaDeFilmesActivity,
                            "Falha ao carregar. Os dados podem estar desatualizados.",
                            Toast.LENGTH_LONG)
                            .show()
                        pbLoading.visibility = ProgressBar.INVISIBLE
                        mostraLista(filmes!!)
                    } else {
                        pbLoading.visibility = ProgressBar.INVISIBLE
                        Toast.makeText(this@ListaDeFilmesActivity,
                            "Falha ao carregar. Verifique a conexão",
                            Toast.LENGTH_LONG)
                            .show()
                    }
                }
            }

            override fun onFailure(
                call: Call<List<ListaDeFilmes>?>?,
                t: Throwable?
            ) {
                filmes = Paper.book().read("ListaDeFilmes")
                if (filmes != null){
                    Toast.makeText(this@ListaDeFilmesActivity,
                        "Falha ao carregar. Os dados podem estar desatualizados.",
                        Toast.LENGTH_LONG)
                        .show()
                    pbLoading.visibility = ProgressBar.INVISIBLE
                    mostraLista(filmes!!)
                } else {
                    pbLoading.visibility = ProgressBar.INVISIBLE
                    Toast.makeText(this@ListaDeFilmesActivity,
                        "Falha ao carregar. Verifique a conexão.",
                        Toast.LENGTH_LONG)
                        .show()
                }
            }
        })
    }

    private fun mostraLista(filmes: List<ListaDeFilmes>) {
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
                    Toast.makeText(this@ListaDeFilmesActivity,"Falha ao carregar. Verifique a conexão.", Toast.LENGTH_LONG).show()
                }
            })
        }
        lista_de_filmes_recyclerview.adapter = adapter
        lista_de_filmes_recyclerview.layoutManager = LinearLayoutManager(this)
        }
}


