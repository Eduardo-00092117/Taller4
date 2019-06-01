package com.example.bookdex_propio.database.Dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.bookdex_propio.database.Entities.Libro

@Dao
interface LibroDao {

    @Query("SELECT * FROM libro_table")
    fun getAll() : LiveData<List<Libro>>

    @Query("SELECT COUNT(*) FROM libro_table")
    fun getCont() : Int

    @Query("SELECT * FROM libro_table WHERE favorito = 1")
    fun getFavorito() : LiveData<List<Libro>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(new : Libro)

    @Query("UPDATE libro_table SET favorito = :fav WHERE ISBN = :id")
    fun updateFavorito(fav : Int, id : String)

    @Query("DELETE FROM libro_table")
    fun deleteAll()
}