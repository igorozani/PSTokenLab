package com.example.pstokenlab.scenarios

import android.content.Context
import com.example.pstokenlab.entities.Movie
import com.example.pstokenlab.entities.MovieList
import com.example.pstokenlab.network.RetrofitInitializer
import io.paperdb.Paper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieListPresenter (val view: MovieListContract.View): MovieListContract.Presenter {

    override fun onLoadList(context: Context){
        view.showLoading()

        Paper.init(context)

        var movies: List<MovieList>?

        val moviesService = RetrofitInitializer().createMovieService()

        val call = moviesService.getList()
        call.enqueue(object : Callback<List<MovieList>> {
            override fun onResponse(call: Call<List<MovieList>>, response: Response<List<MovieList>>) {
                movies = response.body()
                if (movies != null) {
                    Paper.book().write("MovieList", movies)
                    view.hideLoading()
                    view.showList(movies!!)
                } else {
                    movies = Paper.book().read("MovieList")
                    if (movies != null){
                        view.showMessage("Falha ao carregar. Os dados podem estar desatualizados.")
                        view.hideLoading()
                        view.showList(movies!!)
                    } else {
                        view.hideLoading()
                        view.showMessage("Falha ao carregar.")
                    }
                }
            }

            override fun onFailure(
                call: Call<List<MovieList>>,
                t: Throwable
            ) {
                movies = Paper.book().read("MovieList")
                if (movies != null){
                    view.showMessage("Falha ao carregar. Os dados podem estar desatualizados.")
                    view.hideLoading()
                    view.showList(movies!!)
                } else {
                    view.hideLoading()
                    view.showMessage("Falha ao carregar. Verifique a conexão")
                }
            }
        })


    }

    override fun onLoadMovie(movieList: MovieList){

        view.showLoading()

        var movie: Movie?


        val moviesService = RetrofitInitializer().createMovieService()

        val call = moviesService.getMovie(movieList.id)

        call.enqueue(object : Callback<Movie> {
            override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                movie = response.body()
                if(movie != null){
                    Paper.book().write("Movie", movie)
                    view.hideLoading()
                    view.showMovie(movie!!)
                } else {
                    movie = Paper.book().read("Movie")
                    if (movie != null){
                        if (movie!!.id == movieList.id){
                            view.showMessage("Falha ao carregar. Os dados podem estar desatualizados.")
                            view.hideLoading()
                            view.showMovie(movie!!)
                        } else {
                            view.hideLoading()
                            view.showMessage("Falha ao carregar.")
                        }
                    } else {
                        view.hideLoading()
                        view.showMessage("Falha ao carregar.")
                    }
                }
            }

            override fun onFailure(
                call: Call<Movie>,
                t: Throwable
            ) {
                movie = Paper.book().read("Movie")
                if (movie != null){
                    if (movie!!.id == movieList.id){
                        view.showMessage("Falha ao carregar. Os dados podem estar desatualizados.")
                        view.hideLoading()
                        view.showMovie(movie!!)
                    } else {
                        view.hideLoading()
                        view.showMessage("Falha ao carregar. Verifique a conexão.")
                    }
                } else {
                    view.hideLoading()
                    view.showMessage("Falha ao carregar. Verifique a conexão.")
                }
            }
        })
    }
}