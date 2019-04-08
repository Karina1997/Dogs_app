package com.example.dods_app.httpServices

import android.os.AsyncTask
import com.google.gson.GsonBuilder
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.squareup.okhttp.OkHttpClient
import com.squareup.okhttp.Request
import java.io.IOException

class BreadsRequest(val client: OkHttpClient, private val delegate: AsyncResponse?) : AsyncTask<String, Void, List<String>>()  {


    override fun doInBackground(vararg params: String?): List<String> {
        try {
            val request = Request.Builder()
                .url(params[0])
                .build()

            val response = client.newCall(request).execute()
            if (response.isSuccessful) {
                val gson = GsonBuilder().create()
                val jsonObj : JsonElement = gson.fromJson(response.body().string(), JsonObject::class.java).get("message")
                if (jsonObj.isJsonObject) return jsonObj.asJsonObject.keySet().toList()
                return gson.fromJson(jsonObj, Array<String>::class.java).toList()
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return listOf()
    }

    override fun onPostExecute(res: List<String>) {
        super.onPostExecute(res)
        delegate?.processFinished(res)
        }
    }