package com.example.pstokenlab

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detalhes_filme.*
import kotlinx.android.synthetic.main.filme_item.view.*

class DetalhesFilme: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhes_filme)

        val filme: Filme = intent.getSerializableExtra("filme") as Filme

        carregaDadosReserva(filme)
    }

    private fun carregaDadosReserva(filme: Filme) {
        Picasso.with(this).load(filme.poster_url).fit().into(filme_poster)
        movie_title.text = filme.title
        vote_average.text = filme.vote_average
        release_date.text = filme.release_date!!.subSequence(0,4)




    }
}