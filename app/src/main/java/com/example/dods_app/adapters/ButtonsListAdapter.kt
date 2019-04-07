package com.example.dods_app.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.example.dods_app.R

class ButtonsListAdapter(private val names : Array<String>, private val onClick : (Int, String) -> Unit) : RecyclerView.Adapter<ButtonHolder>() {
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ButtonHolder {
        val rootView = LayoutInflater.from(viewGroup.context).inflate(R.layout.button_layout, viewGroup, false)
        val button : Button = rootView.findViewById(R.id.button)
        return ButtonHolder(rootView, button, onClick)
    }

    override fun getItemCount(): Int {
        return names.size
    }

    override fun onBindViewHolder(holder: ButtonHolder, pos: Int) {
        holder.button.text = names[pos]
    }

}

class ButtonHolder(view : View, val button : Button, val onClick : (Int, String) -> Unit) : RecyclerView.ViewHolder(view) {
    init {
        button.setOnClickListener {
            onClick(adapterPosition, button.text as String)
        }
    }
}