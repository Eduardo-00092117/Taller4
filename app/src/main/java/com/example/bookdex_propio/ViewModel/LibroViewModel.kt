package com.example.bookdex_propio.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.bookdex_propio.database.Repository.LibroRespository
import com.example.bookdex_propio.database.BookRoomDatabase
import com.example.bookdex_propio.database.Entities.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LibroViewModel (application: Application) : AndroidViewModel(application) {
    private val repository : LibroRespository
    val allLibro : LiveData<List<Libro>>
    val favoritoLibro : LiveData<List<Libro>>
    val allTag : LiveData<List<Tag>>
    val allAutor : LiveData<List<Autor>>
    val allEditorial : LiveData<List<Editorial>>

    init {
        val libroDao = BookRoomDatabase.getDatabase(application, viewModelScope).LibroDao()
        val autorDao = BookRoomDatabase.getDatabase(application, viewModelScope).AutorDao()
        val editorialDao = BookRoomDatabase.getDatabase(application, viewModelScope).EditorialDao()
        val tagDao = BookRoomDatabase.getDatabase(application, viewModelScope).TagDao()
        val libroxautorDao = BookRoomDatabase.getDatabase(application, viewModelScope).LibroxAutor()
        val libroxeditorialDao = BookRoomDatabase.getDatabase(application, viewModelScope).LibroxEditorialDao()
        val libroxtagDao = BookRoomDatabase.getDatabase(application, viewModelScope).LibroxTagDao()

        repository= LibroRespository(libroDao,autorDao,editorialDao,tagDao,libroxautorDao,libroxeditorialDao,libroxtagDao)
        allLibro = repository.getAllLibro()
        favoritoLibro = repository.getFavorito()
        allTag = repository.getAllTag()
        allAutor = repository.getAllAutor()
        allEditorial = repository.getAllEditorial()
    }

    fun insert(libro : Libro) = viewModelScope.launch (Dispatchers.IO){
        repository.insertLibro(libro)
    }

    fun insertTag(tag : Tag) = viewModelScope.launch (Dispatchers.IO) {
        repository.insertTag(tag)
    }

    fun insertAutor(autor : Autor) = viewModelScope.launch (Dispatchers.IO) {
        repository.insertAutor(autor)
    }

    fun insertEditorial(editorial: Editorial) = viewModelScope.launch (Dispatchers.IO) {
        repository.insertEditorial(editorial)
    }

    fun insertLibroxTag(new : LibroxTag) = viewModelScope.launch (Dispatchers.IO) {
        repository.insertLibroTag(new)
    }

    fun insertLibroxAutor(new : LibroxAutor) = viewModelScope.launch (Dispatchers.IO) {
        repository.insertLibroAutor(new)
    }

    fun insertLibroxEditorial(new : LibroxEditorial) = viewModelScope.launch (Dispatchers.IO) {
        repository.insertLibroEditorial(new)
    }

    fun updateFav(libro : Libro) = viewModelScope.launch (Dispatchers.IO) {
        if (libro.favorito == 0) repository.updateFav(1, libro.isbn)
        else repository.updateFav(0, libro.isbn)
    }

    fun eliminarTag(id : Int) = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteTag(id)
    }

    fun eliminarAutor(id : Int) = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteAutor(id)
    }

    fun eliminarEditorial(id : Int) = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteEditorial(id)
    }

    fun mostrarTag(id : String) = repository.getUnLibroTag(id)

    fun mostrarEditorial(id : String) = repository.getUnLibroEditorial(id)

    fun mostrarAutor(id : String) = repository.getUnLibroAutor(id)
}