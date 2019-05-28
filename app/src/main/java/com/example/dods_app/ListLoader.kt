package com.example.dods_app

import android.content.Context
import com.example.dods_app.executor.ListUsageExecutor
import com.example.dods_app.httpServices.AsyncResponse
import com.example.dods_app.httpServices.BreadsRequest
import com.google.gson.GsonBuilder
import com.squareup.okhttp.OkHttpClient
import java.io.File

class ListLoader(
    val context: Context, private val filename: String,
    private val listUsageExecutor: ListUsageExecutor,
    private val ifUseCache: Boolean
) : AsyncResponse {

    override fun processFinished(output: List<String>) {
        val file = File(context.cacheDir, filename)
        file.writeText(GsonBuilder().create().toJson(output.toTypedArray()))
        listUsageExecutor.useList(output)
    }

    fun loadListFromStorage(URL: String) {
        val file = File(context.cacheDir, filename)
        if (!file.exists() || !ifUseCache) {
            BreadsRequest(OkHttpClient(), this)
                .execute(URL)
        } else {
            listUsageExecutor.useList(getList())
        }
    }

    private fun getList(): List<String> {
        val file = File(context.cacheDir, filename)
        if (file.exists()) {
            return GsonBuilder().create()
                .fromJson(file.inputStream().readBytes().toString(Charsets.UTF_8), Array<String>::class.java).toList()
        }
        return emptyList()
    }
}