package com.example.dods_app

import com.example.dods_app.adapters.Card
import com.example.dods_app.adapters.CardsAdapter
import com.example.dods_app.httpServices.AsyncResponse
import com.example.dods_app.httpServices.BreadsRequest
import com.squareup.okhttp.OkHttpClient

class ImagePathLoader(val cardsAdapter: CardsAdapter, val name: String) : AsyncResponse {
    override fun processFinished(output: List<String>) {
        cardsAdapter.addCards(listOf(Card(name, output[0])))
    }

    fun load(URL: String){
        BreadsRequest(OkHttpClient(), this)
            .execute(URL)
    }
}