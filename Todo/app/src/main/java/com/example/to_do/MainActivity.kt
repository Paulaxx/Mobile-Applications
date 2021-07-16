package com.example.to_do

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    var data: ArrayList<Event> = ArrayList()
    var model: Event? = Event()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        try{
            data = intent.getSerializableExtra("events") as ArrayList<Event>
        }
        catch (e:TypeCastException){

        }

        val recyclerview = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerview.layoutManager = LinearLayoutManager(this)
        recyclerview.adapter = Adapter(generateData(), this)
        recyclerview.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
    }

    fun newItem(view: View) {
        val myIntent = Intent(this, NewItemFormula::class.java)
        myIntent.putExtra("events", data)
        startActivity(myIntent)
    }

    private fun generateData() : ArrayList<Event>{
        return data
    }

    fun sort1(view: View) {
        data = ArrayList(data.sortedWith(compareBy({it.date2})))
        val recyclerview = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerview.adapter = Adapter(generateData(), this)
        recyclerview.adapter?.notifyDataSetChanged()
    }

    fun sort2(view: View) {
        data = ArrayList(data.sortedWith(compareBy({it.type})))
        val recyclerview = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerview.adapter = Adapter(generateData(), this)
        recyclerview.adapter?.notifyDataSetChanged()
    }
}