package com.example.to_do

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.Paint
import android.util.Log
import android.view.*
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.PopupWindow
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import java.util.*
import kotlin.collections.ArrayList


class Adapter(private val dataSet: ArrayList<Event>, private val context: Context) : RecyclerView.Adapter<Adapter.ViewHolder>(){


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val view: View = view
        val textView: TextView = view.findViewById(R.id.event_name)
        val textView2: TextView = view.findViewById(R.id.date)
        val strike: TextView = view.findViewById(R.id.event_name)
        val picture: ImageView = view.findViewById(R.id.image)
        var checkbox: CheckBox = view.findViewById(R.id.checkBox)
        var layout: ConstraintLayout = view.findViewById(R.id.item_layout)

    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.list_item_view, viewGroup, false)

        return ViewHolder(view)
    }

    @SuppressLint("InflateParams")
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        when(dataSet[position].type){
            "task" -> viewHolder.picture.setImageResource(R.drawable.learn)
            "meeting" -> viewHolder.picture.setImageResource(R.drawable.meeting)
            "training/exercises" -> viewHolder.picture.setImageResource(R.drawable.sport)
            "entertainment" -> viewHolder.picture.setImageResource(R.drawable.game)
            "shopping" -> viewHolder.picture.setImageResource(R.drawable.shopp)
            "charges" -> viewHolder.picture.setImageResource(R.drawable.bills)
            "another" -> viewHolder.picture.setImageResource(R.drawable.no)
        }
        viewHolder.textView.text = dataSet[position].name
        viewHolder.textView2.text = dataSet[position].date + "   " + dataSet[position].hour


        viewHolder.itemView.setOnLongClickListener{
            delete(position)
            true
        }

        viewHolder.itemView.setOnClickListener{
            val popupView: View = LayoutInflater.from(context).inflate(R.layout.event_details, null)
            val popupWindow = PopupWindow(popupView, WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT)
            val txt1: TextView = popupView.findViewById(R.id.textView2)
            val txt2: TextView = popupView.findViewById(R.id.textView3)
            val txt3: TextView = popupView.findViewById(R.id.textView4)
            val txt4: TextView = popupView.findViewById(R.id.textView5)
            val txt5: TextView = popupView.findViewById(R.id.textView6)
            txt1.text = dataSet[position].name
            txt2.text = dataSet[position].description
            txt3.text = dataSet[position].priority
            txt4.text = dataSet[position].date + dataSet[position].hour
            txt5.text = dataSet[position].type
            popupWindow.isFocusable = true;
            popupWindow.showAtLocation(popupView, Gravity.CENTER, 0, 0)
        }

        viewHolder.checkbox.setOnCheckedChangeListener(null);
        viewHolder.checkbox.isChecked = dataSet[position].done
        viewHolder.checkbox.setOnCheckedChangeListener { _, _ ->
            //dataSet[position].done = !dataSet[position].done

            if(dataSet[position].done){
                viewHolder.strike.paintFlags = 0
                //viewHolder.layout.setBackgroundColor(Color.parseColor("#9e9e9e"));
                dataSet[position].done = false
                move2(position)
            }
            else{
                //viewHolder.layout.setBackgroundColor(Color.parseColor("#fbe9e7"));
                //viewHolder.strike.paintFlags = viewHolder.strike.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                //viewHolder.strike.setTextColor(Color.BLACK);
                dataSet[position].done = true
                move(position)
            }
        }
    }

    override fun getItemCount() = dataSet.size

    private fun delete(position: Int){
        dataSet.removeAt(position)
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, itemCount);
    }

    private fun move(position: Int){
        dataSet[position].lastPosition = position
        val toMove = dataSet[position]
        dataSet.removeAt(position)
        dataSet.add(toMove)
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, itemCount);
        notifyDataSetChanged()
    }

    private fun move2(position: Int){
        var newIndex: Int = dataSet[position].lastPosition
        dataSet.add(newIndex, dataSet[position])
        Log.i("logg", newIndex.toString())
        Log.i("logg", position.toString())
        dataSet.removeAt(position+1)
        notifyItemRemoved(position+1);
        notifyItemRangeChanged(position+1, itemCount);
        notifyDataSetChanged()
    }

}