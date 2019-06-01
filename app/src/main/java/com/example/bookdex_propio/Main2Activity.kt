package com.example.bookdex_propio

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.bookdex_propio.database.Entities.Libro
import kotlinx.android.synthetic.main.activity_main2.*

class Main2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        var intent = intent
        var instance = second_fragment.newInstance(intent.getParcelableExtra<Libro>("Hola"))
        supportFragmentManager.beginTransaction().replace(R.id.secundario, instance).commit()
        tv_back.setOnClickListener {
            onBackPressed()
        }
    }
}
