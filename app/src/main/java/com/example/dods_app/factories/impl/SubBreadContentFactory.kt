package com.example.dods_app.factories.impl

import com.example.dods_app.Router
import com.example.dods_app.factories.ContentFactory
import com.example.dods_app.fragments.PhotoFragment
import com.example.dods_app.ItemClickListener
import com.example.dods_app.producers.ListProducer
import com.example.dods_app.producers.impl.SubBreadsListProducerImpl

class SubBreadContentFactory(val router: Router, val bread: String): ContentFactory {
    private val listProducer = SubBreadsListProducerImpl(bread)

    override fun getListProducer(): ListProducer {
        return listProducer
    }

    override fun getClickHandler(): ItemClickListener {
        return object : ItemClickListener {
            override fun onItemClick(position: Int) {
                when(position) {
                    in 0 until listProducer.getSize() ->
                        router.navigateTo(true) { PhotoFragment.createPhotoFragment(bread, listProducer.getList()[position]) }
                    else -> throw IllegalStateException()
                }
            }
        }
    }

}