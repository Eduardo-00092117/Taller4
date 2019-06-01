package com.example.bookdex_propio

import android.content.Intent
import android.content.res.Configuration
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import com.example.bookdex_propio.database.Entities.Libro
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), fragment_principal.OnFunctionClick {
    override fun agregarLibro() {
        var intent = Intent(this, InsertActivity::class.java)
        startActivity(intent)
    }

    override fun mostrarLibro(libro: Libro) {
        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE){
            var instance = second_fragment.newInstance(libro)
            supportFragmentManager.beginTransaction().replace(R.id.secundario, instance).commit()
        } else{
            var intent = Intent(this, Main2Activity::class.java)
            intent.putExtra("Hola", libro as Parcelable)
            startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction().replace(R.id.principal, fragment_principal()).commit()

        if (secundario != null){
            supportFragmentManager.beginTransaction().replace(R.id.secundario, second_fragment()).commit()
        }
    }
}
