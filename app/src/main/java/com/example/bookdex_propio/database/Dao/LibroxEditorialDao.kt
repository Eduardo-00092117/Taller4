package com.example.bookdex_propio.database.Dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.bookdex_propio.database.Entities.Editorial
import com.example.bookdex_propio.database.Entities.LibroxEditorial

@Dao
interface LibroxEditorialDao {

    @Query("SELECT et.id, et.nombre FROM libro_x_editorial le, libro_table lt, editorial_table et WHERE le.editorial_id = et.id and le.libro_isbn = lt.ISBN and le.libro_isbn = :id")
    fun getUnLibro(id : String) : LiveData<List<Editorial>>

    @Query("SELECT COUNT(*) FROM libro_x_editorial")
    fun getCont() : Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(new : LibroxEditorial)

    @Query("DELETE FROM libro_x_editorial")
    fun deleteAll()

}