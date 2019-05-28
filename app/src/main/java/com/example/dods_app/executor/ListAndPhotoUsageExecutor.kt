package com.example.dods_app.executor

import com.example.dods_app.ImagePathLoader
import com.example.dods_app.adapters.CardsAdapter
import com.example.dods_app.httpServices.UrlGetter

class ListAndPhotoUsageExecutor(val cardsAdapter: CardsAdapter, val bread: String? = null) : ListUsageExecutor {
    override fun useList(list: List<String>) {
        if (bread != null)
            list.forEach {
                ImagePathLoader(cardsAdapter, it).load(
                    UrlGetter().getBreadOrSubBreadUrl(
                        "images",
                        bread,
                        it
                    )
                )
            }
        else
            list.forEach {
                ImagePathLoader(cardsAdapter, it).load(
                    UrlGetter().getBreadOrSubBreadUrl(
                        "images",
                        it
                    )
                )
            }
    }
}