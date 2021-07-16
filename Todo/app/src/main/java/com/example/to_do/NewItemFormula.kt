package com.example.to_do

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.app.TimePickerDialog.OnTimeSetListener
import android.content.Intent
import android.icu.util.Calendar
import android.icu.util.TimeZone
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.DatePicker
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import com.example.to_do.databinding.ActivityNewItemFormulaBinding


class NewItemFormula : AppCompatActivity(), DatePickerDialog.OnDateSetListener {

    private lateinit var binding:ActivityNewItemFormulaBinding
    var data: ArrayList<Event> = ArrayList()
    var date: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewItemFormulaBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val spinner = findViewById<Spinner>(R.id.item_priority)
        val pr = resources.getStringArray(R.array.priority)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, pr)
        spinner.adapter = adapter

        val spinner2 = findViewById<Spinner>(R.id.item_type)
        val t = resources.getStringArray(R.array.types)
        val adapter2 = ArrayAdapter(this, android.R.layout.simple_spinner_item, t)
        spinner2.adapter = adapter2

        data = intent.getSerializableExtra("events") as ArrayList<Event>
    }

    fun add(view: View) {
        val name = binding.itemName.getText().toString()
        val description = binding.itemDescription.getText().toString()
        val date = binding.itemDate.getText().toString()
        val time = binding.itemTime.getText().toString()
        var date2 = ""

        if(date != ""){
            val dateSplited = date.split(".")
            date2 = dateSplited[2] + "." + dateSplited[1] + "." + dateSplited[0] + "."
        }

        val spinner = findViewById<Spinner>(R.id.item_priority)
        val priority = spinner.selectedItem.toString()

        val spinner2 = findViewById<Spinner>(R.id.item_type)
        val type = spinner2.selectedItem.toString()

        var event = Event()
        event.name = name
        event.date = date
        event.date2 = date2
        event.hour = time
        event.description = description
        event.priority = priority
        event.type = type


        var index: Int = newPosition()
        data.add(index, event)
        val myIntent = Intent(this, MainActivity::class.java)
        myIntent.putExtra("events", data)
        startActivity(myIntent)
    }

    fun showCalendar(view: View) {
        val datePickerDialog = DatePickerDialog(
                this,
                R.style.calendar,
                this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH))
        datePickerDialog.show()
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        var m: String = ""
        var d: String = ""

        if(month < 10){
            m = "0" + month.toString()
        }
        else{
            m = month.toString()
        }
        if(dayOfMonth < 10){
            d = "0" + dayOfMonth.toString()
        }
        else{
            d = dayOfMonth.toString()
        }
        date = d + "." + m + "." + year.toString()
        binding.itemDate.setText(date)
    }

    fun showClock(view: View) {


        val c = Calendar.getInstance();
        c.setTimeZone(TimeZone.getTimeZone("Europe/Berlin"))

        val timePickerDialog = TimePickerDialog(
            this,
            R.style.calendar,
            OnTimeSetListener { view, hourOfDay, minute -> binding.itemTime.setText("$hourOfDay:$minute") },
            c.get(Calendar.HOUR),
            c.get(Calendar.MINUTE),
            false
        )
        timePickerDialog.show()
    }

    fun newPosition(): Int {
        var i: Int = 0
        for (i in data.lastIndex downTo 0) {
           if(!data[i].done)
               return i+1
        }
        return i
    }

}