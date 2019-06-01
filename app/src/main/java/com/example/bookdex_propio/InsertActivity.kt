package com.example.bookdex_propio

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bookdex_propio.Adapter.AdapterAutor
import com.example.bookdex_propio.Adapter.AdapterEditorial
import com.example.bookdex_propio.Adapter.AdapterTag
import com.example.bookdex_propio.ViewModel.LibroViewModel
import com.example.bookdex_propio.database.Entities.*
import kotlinx.android.synthetic.main.activity_insert.*

class InsertActivity : AppCompatActivity() {

    private lateinit var libroViewModel : LibroViewModel
    private lateinit var tagAdapter : AdapterTag
    private lateinit var autorAdapter : AdapterAutor
    private lateinit var editorialAdapter : AdapterEditorial

    private var listTag = ArrayList<Tag>()
    private var listEditorial = ArrayList<Editorial>()
    private var listAutor = ArrayList<Autor>()

    private var pestaña = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insert)

        libroViewModel = ViewModelProviders.of(this).get(LibroViewModel::class.java)

        tagAdapter = AdapterTag(emptyList(), { tag:Tag->(agregarTag(tag))}, { tag:Tag->(eliminarTag(tag))})
        autorAdapter = AdapterAutor(emptyList(), {autor:Autor->(agregarAutor(autor))}, {autor:Autor->(eliminarAutor(autor))})
        editorialAdapter = AdapterEditorial(emptyList(), {editorial:Editorial->(agregarEditorial(editorial))}, {editorial:Editorial->(eliminarEditorial(editorial))})

        rv_catalogo.adapter = tagAdapter
        rv_catalogo.layoutManager = LinearLayoutManager(this)

        libroViewModel.allAutor.observe(this, Observer {autor ->
            rv_catalogo.adapter = autorAdapter
            autor?.let { autorAdapter.setAutor(it) }
        })

        btn_autor.setOnClickListener {
            pestaña = 0
            libroViewModel.allAutor.observe(this, Observer {autor ->
                rv_catalogo.adapter = autorAdapter
                autor?.let { autorAdapter.setAutor(it) }
            })
        }

        btn_editorial.setOnClickListener {
            pestaña = 1
            libroViewModel.allEditorial.observe(this, Observer {autor ->
                rv_catalogo.adapter = editorialAdapter
                autor?.let { editorialAdapter.setEditorial(it) }
            })
        }

        btn_tag.setOnClickListener {
            pestaña = 2
            libroViewModel.allTag.observe(this, Observer {tag ->
                rv_catalogo.adapter = tagAdapter
                tag?.let { tagAdapter.setLibros(it) }
            })
        }

        btn_agregarcatalgo.setOnClickListener {
            when(pestaña){
                0 -> {
                    libroViewModel.insertAutor(Autor(0, et_catalogo.text.toString()))
                }
                1 ->{
                    libroViewModel.insertEditorial(Editorial(0, et_catalogo.text.toString()))
                }
                2 ->{
                    libroViewModel.insertTag(Tag(0, et_catalogo.text.toString()))
                }
            }
            et_catalogo.setText("")
            Toast.makeText(this, "Se ingreso con exito a la tabla!", Toast.LENGTH_LONG).show()
        }

        btn_guardar.setOnClickListener {
            libroViewModel.insert(Libro(et_isbn.text.toString(), et_titulo.text.toString(), et_resumen.text.toString(), et_edicion.text.toString(), 0))
            Toast.makeText(this, "Libro ingresado correctamente!!!!!", Toast.LENGTH_LONG).show()
            for (i in 0 .. listTag.size-1){
                libroViewModel.insertLibroxTag(LibroxTag(listTag[i].id, et_isbn.text.toString()))
            }
            for(i in 0..listAutor.size-1){
                libroViewModel.insertLibroxAutor(LibroxAutor(listAutor[i].id, et_isbn.text.toString()))
            }
            for (i in 0..listEditorial.size-1){
                libroViewModel.insertLibroxEditorial(LibroxEditorial(listEditorial[i].id, et_isbn.text.toString()))
            }
            var intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        btn_cancelar.setOnClickListener {
            onBackPressed()
        }
    }

    fun agregarTag(tag : Tag){
        if (listTag.contains(tag) == false){
            if (listTag.size == 0){
                et_tag.setText(tag.tag)
            } else{
                et_tag.setText(et_tag.text.toString() + ", " + tag.tag)
            }
            listTag.add(tag)
        }
    }

    fun agregarEditorial(editorial: Editorial){
        if (listEditorial.contains(editorial) == false){
            if (listEditorial.size == 0){
                et_editorial.setText(editorial.nombre)
            } else{
                et_editorial.setText(et_editorial.text.toString() + ", " + editorial.nombre)
            }
            listEditorial.add(editorial)
        }
    }

    fun agregarAutor(autor : Autor){
        Log.d("Hola", "Si entra")
        if (listAutor.contains(autor) == false){
            if (listAutor.size == 0){
                et_autor.setText(autor.nombre)
            } else{
                et_autor.setText(et_autor.text.toString() + ", " + autor.nombre)
            }
            listAutor.add(autor)
        }
    }

    fun eliminarAutor(autor : Autor){
        libroViewModel.eliminarAutor(autor.id)
    }

    fun eliminarEditorial(editorial: Editorial){
        libroViewModel.eliminarEditorial(editorial.id)
    }

    fun eliminarTag(tag: Tag){
        libroViewModel.eliminarTag(tag.id)
    }
}
