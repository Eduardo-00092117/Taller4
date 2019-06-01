package com.example.bookdex_propio.database

import android.content.Context
import android.util.Log
import androidx.lifecycle.Observer
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.bookdex_propio.database.Dao.*
import com.example.bookdex_propio.database.Entities.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [Libro::class, Autor::class, Tag::class, Editorial::class, LibroxEditorial::class, LibroxAutor::class, LibroxTag::class], version = 2, exportSchema = false)
abstract class BookRoomDatabase : RoomDatabase(){

    abstract fun LibroDao() : LibroDao
    abstract fun AutorDao() : AutorDao
    abstract fun EditorialDao() : EditorialDao
    abstract fun TagDao() : TagDao
    abstract fun LibroxTagDao() : LibroxTagDao
    abstract fun LibroxEditorialDao() : LibroxEditorialDao
    abstract fun LibroxAutor() : LibroxAutorDao

    companion object{
        @Volatile
        private var INSTANCE : BookRoomDatabase? = null

        fun getDatabase(
            context : Context,
            scope : CoroutineScope
        ) : BookRoomDatabase{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    BookRoomDatabase::class.java,
                    "Libro_database"
                )
                    .fallbackToDestructiveMigration()
                    .addCallback(LibroDatabasesCallback(scope))
                    .build()
                INSTANCE = instance
                instance
            }
        }

        private class LibroDatabasesCallback(
            private val scope : CoroutineScope
        ) : RoomDatabase.Callback(){
            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)

                INSTANCE?.let { database ->
                    scope.launch(Dispatchers.IO) {
                        populateDatabase(database.LibroDao())
                        populateDatabase(database.TagDao())
                        populateDatabase(database.EditorialDao())
                        populateDatabase(database.AutorDao())
                        populateDatabase(database.LibroxTagDao())
                        populateDatabase(database.LibroxEditorialDao())
                        populateDatabase(database.LibroxAutor())
                    }
                }
            }
        }

        suspend fun populateDatabase(libro : LibroDao){
            if (libro.getCont() == 0){
                libro.insert(Libro("1uf483y89","50 sombres de Grey","Latigazos","3",0))
                libro.insert(Libro("uf483y89","pokemony","pikas","2",1))
            }
        }

        suspend fun populateDatabase(tag: TagDao){
            if (tag.getCont() == 0){
                tag.insert(Tag(0, "Romance"))
                tag.insert(Tag(0, "Acción"))
                tag.insert(Tag(0, "Ciencia Ficción"))
                tag.insert(Tag(0, "Caricatura"))
            }
        }

        suspend fun populateDatabase(editorial: EditorialDao){
            if (editorial.getCont() == 0){
                editorial.insert(Editorial(0, "Uca editores"))
                editorial.insert(Editorial(0, "Editorial Perico"))
                editorial.insert(Editorial(0, "Editorial Salesiana"))
                editorial.insert(Editorial(0, "Editorial Bosques"))
            }
        }

        suspend fun populateDatabase(autor: AutorDao){
            if (autor.getCont() == 0){
                autor.insert(Autor(0, "Roque Dalton"))
                autor.insert(Autor(0, "Paulino Espinoza"))
                autor.insert(Autor(0, "Maria Del Carmen"))
                autor.insert(Autor(0, "Editorial Bosques"))
            }
        }

        suspend fun populateDatabase(libroxTag: LibroxTagDao){
            if (libroxTag.getCont() == 0){
                libroxTag.insert(LibroxTag(1, "1uf483y89"))
                libroxTag.insert(LibroxTag(2, "1uf483y89"))
                libroxTag.insert(LibroxTag(3, "uf483y89"))
                libroxTag.insert(LibroxTag(4, "uf483y89"))
            }
        }

        suspend fun populateDatabase(libroxEditorial: LibroxEditorialDao){
            if (libroxEditorial.getCont() == 0){
                libroxEditorial.insert(LibroxEditorial(1, "uf483y89"))
                libroxEditorial.insert(LibroxEditorial(2, "uf483y89"))
                libroxEditorial.insert(LibroxEditorial(3, "1uf483y89"))
                libroxEditorial.insert(LibroxEditorial(4, "1uf483y89"))
            }
        }

        suspend fun populateDatabase(libroxAutor: LibroxAutorDao){
            if (libroxAutor.getCont() == 0){
                libroxAutor.insert(LibroxAutor(1, "1uf483y89"))
                libroxAutor.insert(LibroxAutor(2, "1uf483y89"))
                libroxAutor.insert(LibroxAutor(3, "uf483y89"))
                libroxAutor.insert(LibroxAutor(4, "uf483y89"))
            }
        }
    }

}