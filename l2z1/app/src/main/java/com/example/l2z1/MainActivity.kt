package com.example.l2z1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun newActivity3(view: View) {
        val myIntent = Intent(this, Activity3x3::class.java)
        startActivity(myIntent)
    }
    fun newActivity5(view: View) {
        val myIntent = Intent(this, Activity5x5::class.java)
        startActivity(myIntent)
    }
}