package com.example.dods_app.adapters

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.dods_app.BitmapLoader
import com.example.dods_app.ItemClickListener
import com.example.dods_app.R

class PhotoListAdapter(val photoList: List<String>, val resources: Resources) :
    RecyclerView.Adapter<ViewHolder>(), ItemClickListener {
    override fun onItemClick(position: Int) {
//        notifyItemChanged(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val rootView = LayoutInflater.from(parent.context).inflate(R.layout.image_layout, parent, false)
        val holder = ViewHolder(rootView)
//        val lp = LinearLayout.LayoutParams(imageSize, imageSize)
//        lp.gravity = Gravity.CENTER_HORIZONTAL
//        holder.imageView.layoutParams = lp
        return holder
    }

    override fun getItemCount(): Int {
        return photoList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val imagePath: String = photoList[position]
        BitmapLoader(holder.imageView, resources).loadBitmap(position, imagePath, this)
    }
}


class ViewHolder(card: View) : RecyclerView.ViewHolder(card) {
    val imageView: ImageView = card.findViewById(R.id.imageView)
}