package com.example.dods_app.httpServices

interface AsyncResponse {
    fun processFinished(output: List<String>)
}