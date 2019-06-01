package com.example.bookdex_propio.database.Dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.bookdex_propio.database.Entities.Tag

@Dao
interface TagDao {
    @Query("SELECT * FROM tag_table")
    fun getAll() : LiveData<List<Tag>>

    @Query("SELECT COUNT(*) FROM tag_table")
    suspend fun getCont() : Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(tag : Tag)

    @Query("DELETE FROM tag_table")
    fun deleteAll()

    @Query("DELETE FROM tag_table WHERE id = :id")
    fun deleteOne(id : Int)
}