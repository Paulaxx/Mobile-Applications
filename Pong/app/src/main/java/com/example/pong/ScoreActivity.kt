package com.example.pong

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.lang.Exception

class ScoreActivity : AppCompatActivity() {

    private var points:Int = 0
    private var level: Int = 0
    private var level2: String = ""
    private lateinit var database: AppDatabase
    private lateinit var scores: List<Game>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score)

        val intent = intent
        points = intent.getIntExtra("points", 0)
        level = intent.getIntExtra("level", 0)
        level2 = when(level){
            5 -> "easy"
            10 -> "medium"
            20 -> "hard"
            else -> "another"
        }

        add_and_read_db(level2, points)
    }

    fun play_again(view: View) {
        val myIntent = Intent(this, MainActivity::class.java)
        startActivity(myIntent)
    }

    fun add_and_read_db(level: String, lastScore: Int){
        GlobalScope.launch {
            try{
                database = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "games.db").build()
            } catch (e: Exception){
                //blad
            }

            val game = Game(level, lastScore)
            database.gameDao().insertAll(game)

            scores = database.gameDao().getAll()

            print_scores()
        }
    }

    fun print_scores(){
        var to_print =  "Your last 5 results: \n\n"
        var i = 0

        scores.asReversed().forEach {
            if(i < 5) {
                to_print += "difficulty: ${it.level} points: ${it.points} \n"
                i++
            }
        }

        findViewById<TextView>(R.id.scores).text = to_print
    }
}