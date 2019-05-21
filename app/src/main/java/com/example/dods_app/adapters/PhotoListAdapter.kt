package com.example.dods_app.adapters

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.dods_app.httpServices.BitmapLoader
import com.example.dods_app.R

class PhotoListAdapter(private val photoList: List<String>, private val resources: Resources) :
    RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val rootView = LayoutInflater.from(parent.context).inflate(R.layout.image_layout, parent, false)
        return ViewHolder(rootView)
    }

    override fun getItemCount(): Int {
        return photoList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val imagePath: String = photoList[position]
        BitmapLoader(holder.imageView, resources).loadBitmap(imagePath)
    }
}


class ViewHolder(card: View) : RecyclerView.ViewHolder(card) {
    val imageView: ImageView = card.findViewById(R.id.imageView)
}