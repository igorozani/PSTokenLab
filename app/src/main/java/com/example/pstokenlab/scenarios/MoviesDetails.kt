package com.example.pstokenlab.scenarios

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.pstokenlab.R
import com.example.pstokenlab.entities.Movie
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detalhes_filme.*

class MoviesDetails: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhes_filme)

        val movie: Movie = intent.getSerializableExtra("Movie") as Movie

        carregaDadosFilme(movie)
    }

    private fun carregaDadosFilme(movie: Movie) {
        Picasso.with(this).load(movie.backdrop_url).fit().into(filme_poster)
        movie_title.text = movie.title
        val vote = (movie.vote_average!!*10).toInt()
        vote_average.text = vote.toString() + "%"
        release_date.text = movie.release_date!!.subSequence(0,4)
        ratingBar.rating = movie.vote_average / 2
        overview.text = movie.overview
        val genero: String = movie.genres.toString()
        genres.text = genero.subSequence(1,genero.lastIndex)
        runtime.text = movie.runtime + " min"
    }


}