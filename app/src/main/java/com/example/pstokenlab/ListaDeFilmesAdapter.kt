package com.example.pstokenlab

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.filme_item.view.*

class ListaDeFilmesAdapter (private val filmes: List<Filme>,
                            private val context: Context) : RecyclerView.Adapter<ListaDeFilmesAdapter.ViewHolder>(){

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val filme = filmes[position]
        holder?.let {
            it.bindView(filme)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.filme_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return filmes.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindView(filme: Filme) {
          itemView.filme_item_titulo.text = filme.titulo
        }

    }

}