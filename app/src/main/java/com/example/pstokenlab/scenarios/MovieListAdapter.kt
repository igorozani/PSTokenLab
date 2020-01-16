package com.example.pstokenlab.scenarios

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pstokenlab.R
import com.example.pstokenlab.entities.MovieList
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.movie_item.view.*

class MovieListAdapter (private val filmes: List<MovieList>,
                        private val context: Context) : RecyclerView.Adapter<MovieListAdapter.ViewHolder>() {

    var clique: ((filme: MovieList) -> Unit)? = null

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val filme = filmes[position]
        holder.let {
            it.bindView(filme, clique)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.movie_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return filmes.size
    }

    fun configuraClique(clique: ((filme: MovieList) -> Unit)) {
        this.clique = clique
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(filme: MovieList, clique: ((filme: MovieList) -> Unit)?) {
            itemView.filme_item_titulo.text = filme.title
            Picasso.get().load(filme.poster_url).fit().into(itemView.filme_item_poster)
            if (clique != null) {
                itemView.setOnClickListener {
                    clique.invoke(filme)
                }
            }

        }

    }
}