package com.example.l2z1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.l2z1.databinding.ActivityActivity3x3Binding

class Activity3x3 : AppCompatActivity() {

    private lateinit var binding: ActivityActivity3x3Binding
    private var board = Array(3) {Array(3){0}}
    private var which_symbol = "X"
    private var which_move = 0
    private var winner = "End of the game. Nobody won:("

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityActivity3x3Binding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    fun check_if_sb_won(): Boolean{
        var last_player = 1
        var last_symbol = "X"
        if(which_symbol  == "X"){
            last_player = 2
            last_symbol = "O"
        }
        else
            last_player = 1

        if((board[0][0] == last_player && board[0][1] == last_player && board[0][2] == last_player)
            || (board[1][0] == last_player && board[1][1] == last_player && board[1][2] == last_player)
            || (board[2][0] == last_player && board[2][1] == last_player && board[2][2] == last_player)
            || (board[0][0] == last_player && board[1][0] == last_player && board[2][0] == last_player)
            || (board[0][1] == last_player && board[1][1] == last_player && board[2][1] == last_player)
            || (board[0][2] == last_player && board[1][2] == last_player && board[2][2] == last_player)
            || (board[0][0] == last_player && board[1][1] == last_player && board[2][2] == last_player)
            || (board[2][0] == last_player && board[1][1] == last_player && board[0][2] == last_player)){
            winner = "The winner is player " + last_symbol
            return true
        }

        return false
    }

    fun after_click(){
        var won = check_if_sb_won()

        if(which_move == 9 || won){
            val myIntent = Intent(this, ResultActivity::class.java)
            myIntent.putExtra("winner", winner)
            startActivity(myIntent)
        }
    }

    fun click(view: View) {
        var actual_player = 1
        if(which_symbol == "X")
            actual_player = 1
        else
            actual_player = 2

        when (view.id) {
            R.id.button1 -> {
                board[0][0] = actual_player
                binding.button1.text = which_symbol
            }
            R.id.button2 -> {
                board[0][1] = actual_player
                binding.button2.text = which_symbol
            }
            R.id.button3 -> {
                board[0][2] = actual_player
                binding.button3.text = which_symbol
            }
            R.id.button4 -> {
                board[1][0] = actual_player
                binding.button4.text = which_symbol
            }
            R.id.button5 -> {
                board[1][1] = actual_player
                binding.button5.text = which_symbol
            }
            R.id.button6 -> {
                board[1][2] = actual_player
                binding.button6.text = which_symbol
            }
            R.id.button7 -> {
                board[2][0] = actual_player
                binding.button7.text = which_symbol
            }
            R.id.button8 -> {
                board[2][1] = actual_player
                binding.button8.text = which_symbol
            }
            R.id.button9 -> {
                board[2][2] = actual_player
                binding.button9.text = which_symbol
            }
        }

        if(which_symbol == "X")
            which_symbol = "O"
        else
            which_symbol = "X"

        binding.textView3.text = "Player " + which_symbol + "'s move"

        which_move ++

        after_click()
    }
}