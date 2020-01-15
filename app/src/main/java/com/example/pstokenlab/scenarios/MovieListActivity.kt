package com.example.pstokenlab.scenarios

import android.content.Intent
import android.os.Bundle
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pstokenlab.R
import com.example.pstokenlab.entities.Movie
import com.example.pstokenlab.entities.MovieList
import kotlinx.android.synthetic.main.activity_movie_list.*
import java.io.Serializable

class MovieListActivity: AppCompatActivity (), MovieListContract.View {

    val presenter: MovieListContract.Presenter = MovieListPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_list)

        presenter.onLoadList(this)

    }

    override fun showMessage(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }

    override fun showMovie(movie: Movie){
        val movieDetails = Intent (this, MoviesDetails::class.java)
        movieDetails.putExtra("Movie", movie as Serializable)
        startActivity(movieDetails)
    }

    override fun showList(movies: List<MovieList>) {
        val adapter = MovieListAdapter(movies, this)
        adapter.configuraClique {
            presenter.onLoadMovie(it)
        }
        lista_de_filmes_recyclerview.adapter = adapter
        lista_de_filmes_recyclerview.layoutManager = LinearLayoutManager(this)
        }

    override fun showLoading() {
        pbLoading.visibility = ProgressBar.VISIBLE
    }

    override fun hideLoading() {
        pbLoading.visibility = ProgressBar.INVISIBLE
    }
}


