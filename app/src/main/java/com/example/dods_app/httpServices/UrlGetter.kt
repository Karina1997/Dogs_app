package com.example.dods_app.httpServices

class UrlGetter {

    fun getBreadOrSubBreadUrl(type: String, param: String? = null, param2: String? = null): String {
        val URL = StringBuilder("https://dog.ceo/api/breed/")
        param?.apply { URL.append(this) } ?: if (type == "list")
            return "https://dog.ceo/api/breeds/list/all"
        else
            return "https://dog.ceo/api/breeds/image/random"
        param2?.apply { if (this != "All") URL.append("/$this") }
        return URL.append("/$type").toString()
    }
}