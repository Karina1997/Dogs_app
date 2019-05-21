package com.example.dods_app.factories

import com.example.dods_app.ItemClickListener

interface ListContentFactory {
    fun getList(): List<String>
    fun getClickHandler(): ItemClickListener
    fun setList(list : List<String>)
}