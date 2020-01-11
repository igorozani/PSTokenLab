package com.example.pstokenlab

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import kotlinx.android.synthetic.main.activity_lista_de_filmes.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListaDeFilmesActivity: AppCompatActivity () {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_de_filmes)

        val call = RetrofitInitializer().createFilmesService().list()
        call.enqueue(object: Callback<List<Filme>?> {
            override fun onResponse(call: Call<List<Filme>?>?,
                                    response: Response<List<Filme>?>?) {
                response?.body()?.let{
                    val filmes =  it
                    configuraLista(filmes)
                }
            }

            override fun onFailure(call: Call<List<Filme>?>?,
                                   t: Throwable?) {
                Log.e("onFailure error", t?.message)
            }
        })
    }

    private fun configuraLista(filmes: List<Filme>) {
        val recyclerView = lista_de_filmes_recyclerview
        recyclerView.adapter = ListaDeFilmesAdapter(filmes, this)
        val layoutManager = StaggeredGridLayoutManager(
            2, StaggeredGridLayoutManager.VERTICAL)
        recyclerView.layoutManager = layoutManager
    }
}