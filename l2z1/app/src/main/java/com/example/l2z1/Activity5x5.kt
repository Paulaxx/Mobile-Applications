package com.example.l2z1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.l2z1.databinding.ActivityActivity3x3Binding
import com.example.l2z1.databinding.ActivityActivity5x5Binding

class Activity5x5 : AppCompatActivity() {

    private lateinit var binding: ActivityActivity5x5Binding
    private var board = Array(5) {Array(5){0}}
    private var which_symbol = "X"
    private var which_move = 0
    private var winner = "End of the game. Nobody won:("

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityActivity5x5Binding.inflate(layoutInflater)
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

        if((board[0][0] == last_player && board[0][1] == last_player && board[0][2] == last_player && board[0][3] == last_player && board[0][4] == last_player)
            || (board[1][0] == last_player && board[1][1] == last_player && board[1][2] == last_player && board[1][3] == last_player && board[1][4] == last_player)
            || (board[2][0] == last_player && board[2][1] == last_player && board[2][2] == last_player && board[2][3] == last_player && board[2][4] == last_player)
            || (board[3][0] == last_player && board[3][1] == last_player && board[3][2] == last_player && board[3][3] == last_player && board[3][4] == last_player)
            || (board[4][0] == last_player && board[4][1] == last_player && board[4][2] == last_player && board[4][3] == last_player && board[4][4] == last_player)
            || (board[0][0] == last_player && board[1][0] == last_player && board[2][0] == last_player && board[3][0] == last_player && board[4][0] == last_player)
            || (board[0][1] == last_player && board[1][1] == last_player && board[2][1] == last_player && board[3][1] == last_player && board[4][1] == last_player)
            || (board[0][2] == last_player && board[1][2] == last_player && board[2][2] == last_player && board[3][2] == last_player && board[4][2] == last_player)
            || (board[0][3] == last_player && board[1][3] == last_player && board[2][3] == last_player && board[3][3] == last_player && board[4][3] == last_player)
            || (board[0][4] == last_player && board[1][4] == last_player && board[2][4] == last_player && board[3][4] == last_player && board[4][4] == last_player)
            || (board[0][0] == last_player && board[1][1] == last_player && board[2][2] == last_player && board[3][3] == last_player && board[4][4] == last_player)
            || (board[4][0] == last_player && board[3][1] == last_player && board[2][2] == last_player && board[1][3] == last_player && board[0][4] == last_player)){
            winner = "The winner is player " + last_symbol
            return true
        }

        return false
    }

    fun after_click(){
        var won = check_if_sb_won()

        if(which_move == 25 || won){
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
            R.id.button10 -> {
                board[0][0] = actual_player
                binding.button10.text = which_symbol
            }
            R.id.button11 -> {
                board[0][1] = actual_player
                binding.button11.text = which_symbol
            }
            R.id.button12 -> {
                board[0][2] = actual_player
                binding.button12.text = which_symbol
            }
            R.id.button13 -> {
                board[0][3] = actual_player
                binding.button13.text = which_symbol
            }
            R.id.button14 -> {
                board[0][4] = actual_player
                binding.button14.text = which_symbol
            }
            R.id.button15 -> {
                board[1][0] = actual_player
                binding.button15.text = which_symbol
            }
            R.id.button16 -> {
                board[1][1] = actual_player
                binding.button16.text = which_symbol
            }
            R.id.button17 -> {
                board[1][2] = actual_player
                binding.button17.text = which_symbol
            }
            R.id.button18 -> {
                board[1][3] = actual_player
                binding.button18.text = which_symbol
            }
            R.id.button19 -> {
                board[1][4] = actual_player
                binding.button19.text = which_symbol
            }
            R.id.button20 -> {
                board[2][0] = actual_player
                binding.button20.text = which_symbol
            }
            R.id.button21 -> {
                board[2][1] = actual_player
                binding.button21.text = which_symbol
            }
            R.id.button22 -> {
                board[2][2] = actual_player
                binding.button22.text = which_symbol
            }
            R.id.button23 -> {
                board[2][3] = actual_player
                binding.button23.text = which_symbol
            }
            R.id.button24 -> {
                board[2][4] = actual_player
                binding.button24.text = which_symbol
            }
            R.id.button25 -> {
                board[3][0] = actual_player
                binding.button25.text = which_symbol
            }
            R.id.button26 -> {
                board[3][1] = actual_player
                binding.button26.text = which_symbol
            }
            R.id.button27 -> {
                board[3][2] = actual_player
                binding.button27.text = which_symbol
            }
            R.id.button28 -> {
                board[3][3] = actual_player
                binding.button28.text = which_symbol
            }
            R.id.button29 -> {
                board[3][4] = actual_player
                binding.button29.text = which_symbol
            }
            R.id.button30 -> {
                board[4][0] = actual_player
                binding.button30.text = which_symbol
            }
            R.id.button31 -> {
                board[4][1] = actual_player
                binding.button31.text = which_symbol
            }
            R.id.button32 -> {
                board[4][2] = actual_player
                binding.button32.text = which_symbol
            }
            R.id.button33 -> {
                board[4][3] = actual_player
                binding.button33.text = which_symbol
            }
            R.id.button34 -> {
                board[4][4] = actual_player
                binding.button34.text = which_symbol
            }
        }

        if(which_symbol == "X")
            which_symbol = "O"
        else
            which_symbol = "X"

        binding.textView4.text = "Player " + which_symbol + "'s move"

        which_move ++

        after_click()
    }
}