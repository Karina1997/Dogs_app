package com.example.dods_app.producers.impl

import com.example.dods_app.producers.ListProducer

class BreadsListProducerImpl : ListProducer {

    private var breadsList: List<String>

    init {
        breadsList = listOf("Abc", "Def")
    }

    override fun getSize(): Int {
        return breadsList.size
    }

    override fun getList(): List<String> {
        return breadsList
    }
}