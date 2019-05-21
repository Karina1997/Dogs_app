package com.example.dods_app

import androidx.recyclerview.widget.RecyclerView
import com.example.dods_app.adapters.ButtonHolder
import com.example.dods_app.adapters.ButtonsListAdapter
import com.example.dods_app.factories.ListContentFactory

class BreadListUsageExecutor(private val view: RecyclerView, private val contentFactory: ListContentFactory) : ListUsageExecutor {

    override fun addAdapter(list: List<String>) {
        view.adapter = createAdapter(list)
    }

    fun createAdapter(output: List<String>): RecyclerView.Adapter<ButtonHolder> {
        contentFactory.setList(output)
        return ButtonsListAdapter(contentFactory.getClickHandler(), contentFactory.getList())
    }
}