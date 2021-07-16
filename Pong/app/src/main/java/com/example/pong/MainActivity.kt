package com.example.pong

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun easy_level(view: View) {
        val myIntent = Intent(this, GameActivity::class.java)
        myIntent.putExtra("level", 5)
        startActivity(myIntent)
    }

    fun medium_level(view: View) {
        val myIntent = Intent(this, GameActivity::class.java)
        myIntent.putExtra("level", 10)
        startActivity(myIntent)
    }

    fun hard_level(view: View) {
        val myIntent = Intent(this, GameActivity::class.java)
        myIntent.putExtra("level", 20)
        startActivity(myIntent)
    }
}