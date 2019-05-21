package com.example.dods_app

import android.content.res.Resources
import androidx.recyclerview.widget.RecyclerView
import com.example.dods_app.adapters.PhotoListAdapter

class ImagePathListUsageExecutor(val view: RecyclerView, val resources: Resources): ListUsageExecutor {
    override fun addAdapter(list: List<String>) {
        view.adapter = PhotoListAdapter(list, resources)
    }
}