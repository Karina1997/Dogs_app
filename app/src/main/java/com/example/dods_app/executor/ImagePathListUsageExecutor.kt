package com.example.dods_app.executor

import android.content.res.Resources
import androidx.recyclerview.widget.RecyclerView
import com.example.dods_app.adapters.PhotoListAdapter

class ImagePathListUsageExecutor(val view: RecyclerView, val resources: Resources):
    ListUsageExecutor {
    override fun useList(list: List<String>) {
        view.adapter = PhotoListAdapter(list, resources)
    }
}