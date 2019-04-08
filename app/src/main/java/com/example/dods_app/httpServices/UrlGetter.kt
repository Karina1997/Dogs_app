package com.example.dods_app.httpServices

class UrlGetter {

    fun getBreadorSubBreadUrl(param : String?) : String{
        return param ?.let {
            return "https://dog.ceo/api/breed/$it/list"
        } ?: "https://dog.ceo/api/breeds/list/all"
    }
}