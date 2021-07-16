package com.example.pong

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class GameActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        var intent = intent
        var level = intent.getIntExtra("level", 15)
        findViewById<GameView>(R.id.mySurafceView).dx = level.toFloat()
        findViewById<GameView>(R.id.mySurafceView).dy = level.toFloat()
    }
}