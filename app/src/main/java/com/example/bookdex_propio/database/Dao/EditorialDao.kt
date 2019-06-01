package com.example.bookdex_propio.database.Dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.bookdex_propio.database.Entities.Editorial

@Dao
interface EditorialDao {

    @Query("SELECT * FROM editorial_table")
    fun getAll() : LiveData<List<Editorial>>

    @Query("SELECT COUNT(*) FROM editorial_table")
    fun getCont() : Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(editorial : Editorial)

    @Query("DELETE FROM editorial_table")
    fun deleteAll()

    @Query("DELETE FROM editorial_table WHERE id = :id")
    fun deleteOne(id : Int)

}