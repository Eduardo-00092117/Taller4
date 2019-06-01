package com.example.bookdex_propio.database.Dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.bookdex_propio.database.Entities.Autor
import com.example.bookdex_propio.database.Entities.LibroxAutor

@Dao
interface LibroxAutorDao {

    @Query("SELECT at.id, at.nombre FROM libro_x_autor la, libro_table lt, autor_table at WHERE la.autor_id = at.id and la.libro_isbn = lt.ISBN and la.libro_isbn = :id")
    fun getUnLibro(id : String) : LiveData<List<Autor>>

    @Query("SELECT COUNT(*) FROM libro_x_autor")
    fun getCont() : Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(new : LibroxAutor)

    @Query("DELETE FROM libro_x_autor")
    fun deleteAll()
}