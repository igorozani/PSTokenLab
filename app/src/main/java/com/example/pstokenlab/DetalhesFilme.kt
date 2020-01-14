package com.example.pstokenlab

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detalhes_filme.*

class DetalhesFilme: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhes_filme)

        val filme: Filme = intent.getSerializableExtra("filme") as Filme

        carregaDadosFilme(filme)
    }

    private fun carregaDadosFilme(filme: Filme) {
        Picasso.with(this).load(filme.backdrop_url).fit().into(filme_poster)
        movie_title.text = filme.title
        val vote = (filme.vote_average!!*10).toInt()
        vote_average.text = vote.toString() + "%"
        release_date.text = filme.release_date!!.subSequence(0,4)
        ratingBar.rating = filme.vote_average!! / 2
        overview.text = filme.overview
        val genero: String = filme.genres.toString()
        genres.text = genero.subSequence(1,genero.lastIndex)
        runtime.text = filme.runtime + " min"
    }
}