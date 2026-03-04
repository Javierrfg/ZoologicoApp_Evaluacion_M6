package com.example.zoologicoapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Esto le dice a la actividad que use el diseño del "marco de fotos"
        setContentView(R.layout.activity_main)
    }
}