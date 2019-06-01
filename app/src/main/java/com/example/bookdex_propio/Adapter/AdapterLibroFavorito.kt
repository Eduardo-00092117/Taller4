package com.example.bookdex_propio.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bookdex_propio.R
import com.example.bookdex_propio.database.Entities.Libro
import kotlinx.android.synthetic.main.recylerviewitem.view.*

class AdapterLibroFavorito(var libro : List<Libro>, var clickListener : (Libro) -> Unit, var clickMostrar : (Libro) -> Unit) : RecyclerView.Adapter<AdapterLibroFavorito.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.recylerviewitem, parent,false))
    }

    override fun getItemCount(): Int = libro.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.onBind(libro[position], clickListener, clickMostrar)

    internal fun setLibros(libro: List<Libro>) {
        this.libro = libro
        notifyDataSetChanged()
    }

    class ViewHolder(item : View) : RecyclerView.ViewHolder(item){
        fun onBind(libro : Libro, clickListener: (Libro) -> Unit, clickMostrar : (Libro) -> Unit){
            itemView.tv_isbn.text = "ISBN: " + libro.isbn
            itemView.tv_nombre.text = "Título: " + libro.titulo

            if (libro.favorito == 0){
                itemView.tv_favorite.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_star_border_black_24dp, 0)
            } else{
                itemView.tv_favorite.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_star_black_24dp, 0)
            }

            itemView.tv_favorite.setOnClickListener {
                clickListener(libro)
            }

            itemView.setOnClickListener{
                clickMostrar(libro)
            }
        }
    }
}