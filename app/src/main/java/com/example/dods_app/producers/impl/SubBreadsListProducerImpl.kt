package com.example.dods_app.producers.impl

import com.example.dods_app.producers.ListProducer

class SubBreadsListProducerImpl(private val bread: String) : ListProducer {

    private var subBreadsList: List<String>

    init {
        subBreadsList = listOf("pompom", bread)
    }

    override fun getSize(): Int {
        return subBreadsList.size
    }

    override fun getList(): List<String> {
        return subBreadsList
    }
}