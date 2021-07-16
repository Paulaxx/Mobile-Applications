package com.example.l2z2

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.l2z2.databinding.ActivityBoardBinding
import java.util.*

class Board : AppCompatActivity() {

    private lateinit var binding:ActivityBoardBinding
    private var random_word = ""
    private var words = arrayOf("")
    private var length = 0
    private var display = ""
    private var how_many_wrong_guesses = 0
    private var wrong_guess = ArrayList<String>()
    private var guessed = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBoardBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        words = resources.getStringArray(R.array.words)
        val random = Random()
        val r = random.nextInt(1524 - 0) + 0
        random_word = words[r]

        length = random_word.length
        for (i in 1..length) {
            display += "_ "
        }
        binding.textView3.text = display
    }

    fun refresh(){
        if(how_many_wrong_guesses == 6){
            Toast.makeText(this, "You died:(", Toast.LENGTH_SHORT).show()

            val myIntent = Intent(this, MainActivity::class.java)
            startActivity(myIntent)
        }

        var show = "Wrong letters:\n"
        for(i in wrong_guess){
            show += i
            show += ", "
        }
        show.drop(show.length)
        show.drop(show.length)
        binding.textView4.text = show

        show = ""
        var find = -1
        var won = 0
        for(i in 0..random_word.length-1){
            val l = random_word.get(i)
            find = guessed.indexOf(l.toString())

            if(find == -1){
                show += "_ "
                won ++
            }
            else
                show += l
        }
        binding.textView3.text = show

        if(won == 0){
            Toast.makeText(this, "You won!!!", Toast.LENGTH_SHORT).show()

            val myIntent = Intent(this, MainActivity::class.java)
            startActivity(myIntent)
        }
    }

    fun letter(view: View) {
        val b: Button = view as Button
        val letter: String = b.getText().toString()

        val find = random_word.indexOf(letter)

        if(find == -1){

            val find2 = wrong_guess.indexOf(letter)
            if(find2 == -1){
                wrong_guess.add(letter)
                var imageView: ImageView = binding.imageView
                when (how_many_wrong_guesses) {
                    0 -> imageView.setImageResource(R.drawable.wisielec1);
                    1 -> imageView.setImageResource(R.drawable.wisielec2);
                    2 -> imageView.setImageResource(R.drawable.wisielec3);
                    3 -> imageView.setImageResource(R.drawable.wisielec4);
                    4 -> imageView.setImageResource(R.drawable.wisielec5);
                    5 -> imageView.setImageResource(R.drawable.wisielec6);
                }
                how_many_wrong_guesses ++
            }
            else{
                Toast.makeText(this@Board, "You already used this letter", Toast.LENGTH_SHORT).show()
            }
        }
        else{
            val find3 = guessed.indexOf(letter)
            if(find3 == -1)
                guessed.add(letter)
            else
                Toast.makeText(this@Board, "You already used this letter", Toast.LENGTH_SHORT).show()
        }

        refresh()
    }
}