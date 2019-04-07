package com.example.dods_app.factories

import com.example.dods_app.ItemClickListener
import com.example.dods_app.producers.ListProducer

interface ContentFactory {
    fun getListProducer(): ListProducer
    fun getClickHandler(): ItemClickListener
}