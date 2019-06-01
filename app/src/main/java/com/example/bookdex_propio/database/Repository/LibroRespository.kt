package com.example.bookdex_propio.database.Repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.bookdex_propio.database.Dao.*
import com.example.bookdex_propio.database.Entities.*

class LibroRespository(
    private val Libro : LibroDao,
    private val Autor : AutorDao,
    private val Editorial : EditorialDao,
    private val Tag : TagDao,
    private val LibroxAutor : LibroxAutorDao,
    private val LibroxEditorial : LibroxEditorialDao,
    private val LibroxTag: LibroxTagDao) {

    //--------------------------------------------------------------------------------------------
    fun getAllLibro() : LiveData<List<Libro>> = Libro.getAll()

    fun getFavorito() : LiveData<List<Libro>> = Libro.getFavorito()

    @WorkerThread
    suspend fun insertLibro(libro : Libro) = Libro.insert(libro)

    fun updateFav(fav : Int, id : String) = Libro.updateFavorito(fav, id)

    fun deleteAllLibro() = Libro.deleteAll()

    //--------------------------------------------------------------------------------------------
    fun getAllAutor() : LiveData<List<Autor>> = Autor.getAll()

    @WorkerThread
    suspend fun insertAutor(autor : Autor) = Autor.insert(autor)

    fun deleteAllAutor() = Autor.deleteAll()

    fun deleteAutor(id : Int) = Autor.deleteOne(id)

    //--------------------------------------------------------------------------------------------
    fun getAllEditorial() : LiveData<List<Editorial>> = Editorial.getAll()

    @WorkerThread
    suspend fun insertEditorial(editorial : Editorial) = Editorial.insert(editorial)

    fun deleteAllEditorial() = Editorial.deleteAll()

    fun deleteEditorial(id : Int) = Editorial.deleteOne(id)

    //--------------------------------------------------------------------------------------------
    fun getAllTag() : LiveData<List<Tag>> = Tag.getAll()

    @WorkerThread
    suspend fun insertTag(tag : Tag) = Tag.insert(tag)

    fun deleteAllTag() = Tag.deleteAll()

    fun deleteTag(id : Int) = Tag.deleteOne(id)

    //--------------------------------------------------------------------------------------------
    fun getUnLibroAutor(id : String) : LiveData<List<Autor>> = LibroxAutor.getUnLibro(id)

    @WorkerThread
    suspend fun insertLibroAutor(new : LibroxAutor) = LibroxAutor.insert(new)

    fun deleteAllLibroAutor() = LibroxAutor.deleteAll()

    //--------------------------------------------------------------------------------------------
    fun getUnLibroEditorial(id : String) : LiveData<List<Editorial>> = LibroxEditorial.getUnLibro(id)

    @WorkerThread
    suspend fun insertLibroEditorial(new : LibroxEditorial) = LibroxEditorial.insert(new)

    fun deleteAll() = LibroxEditorial.deleteAll()

    //--------------------------------------------------------------------------------------------
    fun getUnLibroTag(id : String) : LiveData<List<Tag>> = LibroxTag.getUnLibro(id)

    @WorkerThread
    suspend fun insertLibroTag(new : LibroxTag) = LibroxTag.insert(new)

    fun deleteAllLibroTag() = LibroxTag.deleteAll()

}