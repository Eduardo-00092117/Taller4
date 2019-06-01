package com.example.bookdex_propio

import android.content.Context
import android.net.Uri
import android.opengl.Visibility
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bookdex_propio.Adapter.AdapterLibro
import com.example.bookdex_propio.Adapter.AdapterLibroFavorito
import com.example.bookdex_propio.ViewModel.LibroViewModel
import com.example.bookdex_propio.database.Entities.Libro
import kotlinx.android.synthetic.main.fragment_fragment_principal.*
import kotlinx.android.synthetic.main.fragment_fragment_principal.view.*
import kotlinx.android.synthetic.main.recylerviewitem.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [fragment_principal.OnFunctionClick] interface
 * to handle interaction events.
 * Use the [fragment_principal.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class fragment_principal : Fragment() {
    // TODO: Rename and change types of parameters
    private var listener: OnFunctionClick? = null



    interface OnFunctionClick {
        fun mostrarLibro(libro : Libro)
        fun agregarLibro()
    }


    private lateinit var libroRepoViewModel : LibroViewModel
    private lateinit var libroAdapter : AdapterLibro
    private lateinit var libroFavoritoAdapter : AdapterLibroFavorito


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        var view = inflater.inflate(R.layout.fragment_fragment_principal, container, false)

        libroRepoViewModel = ViewModelProviders.of(activity!!).get(LibroViewModel::class.java)

        //------------------------------------------------------------------------------------------------------------------
        libroAdapter = AdapterLibro(emptyList(), { libro:Libro->modificarFavorito(libro)}, {libro: Libro->listener?.mostrarLibro(libro)})
        view.rv_libros.adapter = libroAdapter
        view.rv_libros.layoutManager = LinearLayoutManager(view.context)

        libroRepoViewModel.allLibro.observe(this, Observer {libros ->
            libros?.let { libroAdapter.setLibros(it) }
        })
        //-----------------------------------------------------------------------------------------------------------------

        //---------------------------------------------------------------------------------------------------------------------
        libroFavoritoAdapter = AdapterLibroFavorito(emptyList(), { libro:Libro->modificarFavorito(libro)}, {libro: Libro->listener?.mostrarLibro(libro)})
        view.rv_librosfavoritos.adapter = libroFavoritoAdapter
        view.rv_librosfavoritos.layoutManager = LinearLayoutManager(view.context)

        libroRepoViewModel.favoritoLibro.observe(this, Observer {libros ->
            libros?.let { libroFavoritoAdapter.setLibros(it) }
        })
        //---------------------------------------------------------------------------------------------------------------------


        view.btn_todos.setOnClickListener {
            view.rv_libros.visibility = View.VISIBLE
            view.rv_librosfavoritos.visibility = View.GONE
        }

        view.btn_favoritos.setOnClickListener {
            view.rv_libros.visibility = View.GONE
            view.rv_librosfavoritos.visibility = View.VISIBLE
        }

        view.btn_insertar.setOnClickListener {
            listener?.agregarLibro()
        }

        return view
    }

    fun modificarFavorito(libro : Libro){
        libroRepoViewModel.updateFav(libro)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFunctionClick) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }
}
