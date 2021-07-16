package com.example.gallery

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import java.lang.Exception

class ImageDetailsLandscapeFragment : Fragment() {
    var cos: Int = 0
    var stars: Float = 0.0F
    private var idd: Int = 0
    private var description: String? = ""
    lateinit var imagee: ImageView
    lateinit var desc: EditText
    lateinit var ratingBar: RatingBar

    private val viewModel: ItemViewModel by activityViewModels()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_image_details_landscape, container, false)
        imagee = view.findViewById(R.id.imageView)
        desc = view.findViewById(R.id.editTextTextPersonName)
        ratingBar = view.findViewById(R.id.ratingBar2)

        cos = activity?.getIntent()?.extras?.getInt("cos", 0)!!
        stars = activity?.getIntent()?.extras?.getFloat("stars", 0.0F)!!
        idd = activity?.getIntent()?.extras?.getInt("id", 0)!!
        description = activity?.getIntent()?.extras?.getString("description")

        Glide.with(this).load(cos).apply(RequestOptions().centerCrop()).into(imagee)
        desc.setText(description)
        ratingBar.setRating(stars)

        view.findViewById<Button>(R.id.button2).setOnClickListener { click(it) }
        return view
    }

    private fun click(view: View) {
        var myIntent = Intent()
        myIntent.putExtra("description", desc.getText().toString())
        myIntent.putExtra("stars", ratingBar.getRating().toFloat())
        myIntent.putExtra("id", idd)
        myIntent.putExtra("cos", cos)
        activity?.setResult(Activity.RESULT_OK, myIntent)
        activity?.finish()
    }

    override fun onSaveInstanceState(outState: Bundle) {

        try {
            viewModel.selectItem(ratingBar.rating.toFloat())
            viewModel.selectItem2(desc.text.toString())
        }
        catch (e: Exception) {
        }

        super.onSaveInstanceState(outState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.selectedItem.observe(viewLifecycleOwner, Observer { item ->
            ratingBar.setRating(item)
        })
        viewModel.selectedItem2.observe(viewLifecycleOwner, Observer { item ->
            desc.setText(item)
        })
    }

}