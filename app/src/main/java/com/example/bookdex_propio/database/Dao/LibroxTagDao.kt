package com.example.bookdex_propio.database.Dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.bookdex_propio.database.Entities.LibroxTag
import com.example.bookdex_propio.database.Entities.Tag

@Dao
interface LibroxTagDao {

    @Query("SELECT tt.id, tt.tag FROM libro_x_tag lxt, libro_table lt, tag_table tt WHERE lxt.tag_id = tt.id and lxt.libro_isbn = lt.ISBN and lxt.libro_isbn = :id")
    fun getUnLibro(id : String) : LiveData<List<Tag>>

    @Query("SELECT COUNT(*) FROM libro_x_tag")
    fun getCont() : Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(new : LibroxTag)

    @Query("DELETE FROM libro_x_tag")
    fun deleteAll()
}