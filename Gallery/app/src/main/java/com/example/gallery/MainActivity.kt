package com.example.gallery

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.lang.reflect.Field


class MainActivity : AppCompatActivity() {

    private val DETAILS_REQUEST = 1

    var imageRecycler: RecyclerView? = null
    var imagesList: ArrayList<Image> = ArrayList()
    var imagesList2: ArrayList<Image> = ArrayList()
    var i: Int = 0
    var lastFirstVisiblePosition: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageRecycler = findViewById(R.id.images_recycle)
        imageRecycler?.layoutManager = GridLayoutManager(this, 3)
        imageRecycler?.setHasFixedSize(true)

        if(imagesList.size == 0){
            val drawables: Array<Field> = com.example.gallery.R.drawable::class.java.fields;
            for (field in drawables) {
                if(field.name.take(5) == "image"){
                    try {
                        Log.i("LOG_TAG", field.name)
                        var new_image = Image()
                        new_image.description = resources.getStringArray(R.array.descriptions)[i]
                        i ++
                        new_image.name = field.name
                        new_image.cos = getImage(field.name)
                        imagesList.add(new_image)
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }

            imageRecycler?.adapter = ImageAdapter(this, imagesList!!)
        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState?.run {
            imagesList2 = imagesList
            putParcelableArrayList("IMAGES", imagesList2)
        }

        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        savedInstanceState?.run {
            imagesList = getParcelableArrayList<Image>("IMAGES") as ArrayList<Image>
        }
        imageRecycler?.adapter = ImageAdapter(this, imagesList!!)
        imageRecycler?.adapter?.notifyDataSetChanged()
        (imageRecycler?.adapter as ImageAdapter).sort()
    }

    private fun getImage(imageName: String?): Int {
        return resources.getIdentifier(imageName, "drawable", packageName);
    }

    fun sentIntent(cos: Int, stars: Float, position: Int, description: String?) {
        val intent = Intent(this, ImageDetailsActivity::class.java)
        intent.putExtra("cos", cos)
        intent.putExtra("stars", stars)
        intent.putExtra("id", position)
        intent.putExtra("description", description)
        startActivityForResult(intent, DETAILS_REQUEST)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        var desc: String = ""
        var idd: Int = 0
        var cos: Int = 0
        var stars: Float = 0.0F
        if (requestCode == DETAILS_REQUEST) {
            if (resultCode == Activity.RESULT_OK && data != null) {
                desc = data.getStringExtra("description").toString()
                idd = data.getIntExtra("id", 0)
                cos = data.getIntExtra("cos", 0)
                stars = data.getFloatExtra("stars", 0.0F)
                (imageRecycler?.adapter as ImageAdapter).changes(desc, idd, cos, stars)
            }
        }
    }

    override fun onPause() {
        super.onPause()
        lastFirstVisiblePosition = (imageRecycler?.layoutManager as GridLayoutManager).findFirstCompletelyVisibleItemPosition()
    }

    override fun onResume() {
        super.onResume()
        (imageRecycler?.layoutManager as GridLayoutManager).scrollToPosition(lastFirstVisiblePosition)
    }
}