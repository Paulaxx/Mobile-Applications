package com.example.l2z1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.l2z1.databinding.ActivityActivity3x3Binding
import com.example.l2z1.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val extra : String? = intent.getStringExtra("winner")
        binding.textView5.text = extra
    }

    fun comeBack(view: View) {
        val myIntent = Intent(this, MainActivity::class.java)
        startActivity(myIntent)
    }
}