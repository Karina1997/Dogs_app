package com.example.dods_app.httpServices

import android.content.res.Resources
import android.util.DisplayMetrics
import android.widget.ImageView
import com.example.dods_app.R
import com.squareup.picasso.Picasso


class BitmapLoader(
    private val imageView: ImageView,
    private val resources: Resources
) {


    fun loadBitmap(name: String) {
        val imageSize = updateImageSize(resources.displayMetrics)
        Picasso.get().load(name).resize(0, imageSize).placeholder(R.drawable.wait_picture).into(imageView)
    }

    private fun updateImageSize(dm: DisplayMetrics): Int {
        val w = dm.widthPixels
        return (w * 0.7f + 0.5f).toInt()
    }
}