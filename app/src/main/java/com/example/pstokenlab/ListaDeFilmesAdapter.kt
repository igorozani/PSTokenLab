package com.example.pstokenlab

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.filme_item.view.*

class ListaDeFilmesAdapter (private val filmes: List<ListaDeFilmes>,
                            private val context: Context) : RecyclerView.Adapter<ListaDeFilmesAdapter.ViewHolder>() {

    var clique: ((filme: ListaDeFilmes) -> Unit)? = null

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val filme = filmes[position]
        holder.let {
            it.bindView(filme, context, clique)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.filme_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return filmes.size
    }

    fun configuraClique(clique: ((filme: ListaDeFilmes) -> Unit)) {
        this.clique = clique
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(filme: ListaDeFilmes, context: Context, clique: ((filme: ListaDeFilmes) -> Unit)?) {
            itemView.filme_item_titulo.text = filme.title
            Picasso.with(context).load(filme.poster_url).fit().into(itemView.filme_item_poster)
            if (clique != null) {
                itemView.setOnClickListener {
                    clique.invoke(filme)
                }
            }

        }

    }
}