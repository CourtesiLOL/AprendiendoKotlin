package com.example.primeraappkotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.primeraappkotlin.IMC.IMC_Activity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val BotonIMC = findViewById<Button>(R.id.BotonIMC)
        BotonIMC.setOnClickListener {
            navegateIMC()
        }
    }

    private fun navegateIMC(){
        val intent = Intent(this,IMC_Activity::class.java)
        startActivity(intent)
    }
}