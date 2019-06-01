package com.example.bookdex_propio.database.Entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(tableName = "libro_x_editorial", primaryKeys = ["editorial_id", "libro_isbn"])
data class LibroxEditorial(
    @ForeignKey(
        entity = Editorial::class,
        parentColumns = ["id"],
        childColumns = ["editorial_id"]
    )
    @ColumnInfo(name = "editorial_id") val editorial_id : Int,

    @ForeignKey(
        entity = Libro::class,
        parentColumns = ["ISBN"],
        childColumns = ["libro_isbn"]
    )
    @ColumnInfo(name = "libro_isbn") val libro_isbn : String
)