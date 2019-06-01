package com.example.bookdex_propio.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bookdex_propio.R
import com.example.bookdex_propio.database.Entities.Editorial
import kotlinx.android.synthetic.main.recyclercatalogo.view.*

class AdapterEditorial(var editorial : List<Editorial>, var clickListener : (Editorial) -> Unit, var eliminarListener : (Editorial) -> Unit) : RecyclerView.Adapter<AdapterEditorial.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.recyclercatalogo, parent, false))
    }

    override fun getItemCount(): Int = editorial.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.onBind(editorial[position], clickListener, eliminarListener)

    internal fun setEditorial(editorial: List<Editorial>){
        this.editorial = editorial
        notifyDataSetChanged()
    }

    class ViewHolder(item : View) : RecyclerView.ViewHolder(item){
        fun onBind(editorial: Editorial, clickListener: (Editorial) -> Unit, eliminarListener: (Editorial) -> Unit){
            itemView.btn_catalogo.text = editorial.nombre

            itemView.btn_catalogo.setOnClickListener {
                clickListener(editorial)
            }

            itemView.tv_eliminar.setOnClickListener { eliminarListener(editorial) }
        }
    }
}