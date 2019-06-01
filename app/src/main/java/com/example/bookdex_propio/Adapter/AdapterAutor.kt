package com.example.bookdex_propio.Adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bookdex_propio.R
import com.example.bookdex_propio.database.Entities.Autor
import kotlinx.android.synthetic.main.recyclercatalogo.view.*

class AdapterAutor(var autor : List<Autor>, var clickListener : (Autor) -> Unit, var eliminarListener : (Autor) -> Unit) : RecyclerView.Adapter<AdapterAutor.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterAutor.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.recyclercatalogo, parent, false))
    }

    override fun getItemCount(): Int = autor.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.onBind(autor[position], clickListener, eliminarListener)

    internal fun setAutor(autor : List<Autor>){
        this.autor = autor
        notifyDataSetChanged()
    }

    class ViewHolder(item : View) : RecyclerView.ViewHolder(item){
        fun onBind(autor : Autor, clickListener : (Autor) -> Unit, eliminarListener: (Autor) -> Unit){
            itemView.btn_catalogo.text = autor.nombre

            itemView.btn_catalogo.setOnClickListener {
                clickListener(autor)
            }

            itemView.tv_eliminar.setOnClickListener { eliminarListener(autor) }
        }
    }
}