package com.example.bookdex_propio.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bookdex_propio.R
import com.example.bookdex_propio.database.Entities.Tag
import kotlinx.android.synthetic.main.recyclercatalogo.view.*

class AdapterTag(var tag : List<Tag>, var clickListener: (Tag) -> Unit, var eliminarListener : (Tag) -> Unit) : RecyclerView.Adapter<AdapterTag.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.recyclercatalogo, parent,false))
    }

    override fun getItemCount(): Int = tag.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.onBind(tag[position], clickListener, eliminarListener)

    internal fun setLibros(tag: List<Tag>) {
        this.tag = tag
        notifyDataSetChanged()
    }

    class ViewHolder(item : View) : RecyclerView.ViewHolder(item){
        fun onBind(tag : Tag, clickListener: (Tag) -> Unit, eliminarListener: (Tag) -> Unit){
            itemView.btn_catalogo.text = tag.tag
            itemView.btn_catalogo.setOnClickListener { clickListener(tag) }
            itemView.tv_eliminar.setOnClickListener { eliminarListener(tag) }
        }
    }
}