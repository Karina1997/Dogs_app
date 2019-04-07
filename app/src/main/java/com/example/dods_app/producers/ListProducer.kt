package com.example.dods_app.producers

interface ListProducer {
    fun getList(): List<String>
    fun getSize(): Int
}