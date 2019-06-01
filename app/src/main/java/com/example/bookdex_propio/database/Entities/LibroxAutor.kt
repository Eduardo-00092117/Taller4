package com.example.bookdex_propio.database.Entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(tableName = "libro_x_autor", primaryKeys = ["autor_id", "libro_isbn"])
data class LibroxAutor(
    @ForeignKey(
        entity = Autor::class,
        parentColumns = ["id"],
        childColumns = ["autor_id"]
    )
    @ColumnInfo(name = "autor_id") val autor_id : Int,

    @ForeignKey(
        entity = Libro::class,
        parentColumns = ["ISBN"],
        childColumns = ["libro_isbn"]
    )
    @ColumnInfo(name = "libro_isbn") val libro_isbn : String
)