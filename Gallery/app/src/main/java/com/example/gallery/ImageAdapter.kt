package com.example.gallery

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import java.util.*
import kotlin.collections.ArrayList


class ImageAdapter(var context: Context, var imagesList: ArrayList<Image>): RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {


    class ImageViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var image: ImageView? = null

        init{
            image = itemView.findViewById(R.id.row_image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.recycler_item, parent, false)
        return ImageViewHolder(view)
    }

    override fun getItemCount(): Int {
        return imagesList.size
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val currentImage = imagesList[position]
        var name = currentImage.cos
        Glide.with(context).load(name).apply(RequestOptions().centerCrop()).into(holder.image!!)

        holder.image?.setOnClickListener{
            (context as MainActivity).sentIntent(imagesList[position].cos, imagesList[position].stars, position, imagesList[position].description)
        }
    }

    fun changes(desc: String, pos: Int, cos: Int, stars: Float){
        imagesList[pos].description = desc
        imagesList[pos].cos = cos
        imagesList[pos].stars = stars
        sort()
    }

    fun sort(){
        imagesList = ArrayList( imagesList.sortedWith(compareByDescending { it.stars }))
        notifyDataSetChanged()
    }

}