package com.example.bookdex_propio.database.Entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(tableName = "libro_x_tag", primaryKeys = ["tag_id", "libro_isbn"])
data class LibroxTag(
    @ForeignKey(
        entity = Tag::class,
        parentColumns = ["id"],
        childColumns = ["tag_id"]
    )
    @ColumnInfo(name = "tag_id") val tag_id : Int,

    @ForeignKey(
        entity = Libro::class,
        parentColumns = ["ISBN"],
        childColumns = ["libro_isbn"]
    )
    @ColumnInfo(name = "libro_isbn") val libro_isbn : String
)