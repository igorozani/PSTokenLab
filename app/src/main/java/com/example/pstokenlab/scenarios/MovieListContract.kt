package com.example.pstokenlab.scenarios

import android.content.Context
import com.example.pstokenlab.entities.Movie
import com.example.pstokenlab.entities.MovieList

interface MovieListContract {

    interface View{
        fun showLoading()
        fun hideLoading()
        fun showList(movies: List<MovieList>)
        fun showMovie(movie: Movie)
        fun showMessage(msg: String)
        fun refreshList()
        fun hideRefreshLoading()
    }

    interface Presenter{
        fun onLoadList(context: Context, alreadyRefresh: Boolean)
        fun onLoadMovie(movieList: MovieList)
    }

}