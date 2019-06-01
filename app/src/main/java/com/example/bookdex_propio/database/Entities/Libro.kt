package com.example.bookdex_propio.database.Entities

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "libro_table")
data class Libro(
    @PrimaryKey @ColumnInfo(name = "ISBN") val isbn : String,
    @ColumnInfo(name = "titulo") val titulo : String,
    @ColumnInfo(name = "resumen") val resumen : String,
    @ColumnInfo(name = "edicion") val edicion :String,
    @ColumnInfo(name = "favorito") val favorito : Int
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(isbn)
        parcel.writeString(titulo)
        parcel.writeString(resumen)
        parcel.writeString(edicion)
        parcel.writeInt(favorito)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Libro> {
        override fun createFromParcel(parcel: Parcel): Libro {
            return Libro(parcel)
        }

        override fun newArray(size: Int): Array<Libro?> {
            return arrayOfNulls(size)
        }
    }
}