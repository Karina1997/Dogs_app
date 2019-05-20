package com.example.dods_app

import android.content.res.Resources
import android.util.DisplayMetrics
import android.widget.ImageView
import com.squareup.picasso.Picasso

class BitmapLoader(
    private val imageView: ImageView,
    private val resources: Resources
) {

    fun loadBitmap(position: Int, name: String, listener: ItemClickListener) {
        var imageSize = updateImageSize(resources.displayMetrics)
        Picasso.get().load(name).resize(0, imageSize).placeholder(R.drawable.wait_picture).into(imageView)
        listener.onItemClick(position)
    }

    private fun updateImageSize(dm: DisplayMetrics): Int {
        var h = dm.heightPixels
        var w = dm.widthPixels
        if (w > h) {
            val tmp = w
            w = h
            h = tmp
        }
        return (Math.min(h * 0.7f, w * 0.7f) + 0.5f).toInt()
    }
}