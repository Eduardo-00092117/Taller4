package com.example.bookdex_propio

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.bookdex_propio.ViewModel.LibroViewModel
import com.example.bookdex_propio.database.Entities.Libro
import kotlinx.android.synthetic.main.fragment_second_fragment.view.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [second_fragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [second_fragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class second_fragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var LibroOne: Libro? = null

    companion object {
        @JvmStatic
        fun newInstance(libro: Libro) : second_fragment {
            var instance = second_fragment()
            instance.LibroOne = libro
            return instance
        }
    }

    private lateinit var libroRepoViewModel : LibroViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_second_fragment, container, false)
        libroRepoViewModel = ViewModelProviders.of(activity!!).get(LibroViewModel::class.java)
        if (LibroOne != null){
            libroRepoViewModel.mostrarTag(LibroOne!!.isbn).observe(this, Observer {libros ->
                libros?.let {
                    if (it.size == 0){
                        view.tv_tagUno.text = "Tag(s): No disponible!"
                    } else{
                        view.tv_tagUno.text = "Tag(s)"
                    }
                    for (i in 0..it.size-1){
                        view.tv_tagUno.text = view.tv_tagUno.text.toString() + "\n" + (i+1).toString() + ") " + it[i].tag
                    }
                }
            })
            libroRepoViewModel.mostrarEditorial(LibroOne!!.isbn).observe(this, Observer {libros ->
                libros?.let {
                    if (it.size == 0){
                        view.tv_EditorialUno.text = "Editorial(es): No disponible!"
                    } else{
                        view.tv_EditorialUno.text = "Editorial(es)"
                    }
                    for (i in 0..it.size-1){
                        view.tv_EditorialUno.text = view.tv_EditorialUno.text.toString() + "\n" + (i+1).toString() + ") " + it[i].nombre
                    }
                }
            })
            libroRepoViewModel.mostrarAutor(LibroOne!!.isbn).observe(this, Observer {libros ->
                libros?.let {
                    if (it.size == 0){
                        view.tv_AutorUno.text = "Autor(es): No disponible!"
                    } else{
                        view.tv_AutorUno.text = "Autor(es)"
                    }
                    for (i in 0..it.size-1){
                        view.tv_AutorUno.text = view.tv_AutorUno.text.toString() + "\n" + (i+1).toString() + ") " + it[i].nombre
                    }
                }
            })
            view.tv_isbnUno.text = "ISBN: " + LibroOne?.isbn
            view.tv_tituloUno.text = "Título: " + LibroOne?.titulo
            view.tv_resumenUno.text = "Resumen: " + LibroOne?.resumen
            view.tv_edicionUno.text = "Edición: " + LibroOne?.edicion
            if (LibroOne?.favorito == 0){
                view.tv_favoriteUno.text = "Favorito: No"
            } else{
                view.tv_favoriteUno.text = "Favorito: Si"
            }
        }
        return view
    }
}
