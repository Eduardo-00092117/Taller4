package com.example.bookdex_propio.database.Dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.bookdex_propio.database.Entities.Autor

@Dao
interface AutorDao {

    @Query("SELECT * FROM autor_table")
    fun getAll() : LiveData<List<Autor>>

    @Query("SELECT COUNT(*) FROM autor_table")
    fun getCont() : Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(autor : Autor)

    @Query("DELETE FROM autor_table")
    fun deleteAll()

    @Query("DELETE FROM autor_table WHERE id = :id")
    fun deleteOne(id : Int)

}