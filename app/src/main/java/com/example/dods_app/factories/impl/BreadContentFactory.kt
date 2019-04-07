package com.example.dods_app.factories.impl

import com.example.dods_app.Router
import com.example.dods_app.factories.ContentFactory
import com.example.dods_app.fragments.ListFragment
import com.example.dods_app.ItemClickListener
import com.example.dods_app.producers.ListProducer
import com.example.dods_app.producers.impl.BreadsListProducerImpl

class BreadContentFactory(val router: Router) : ContentFactory {

    private val listProducer = BreadsListProducerImpl()


    override fun getListProducer(): ListProducer {
        return listProducer
    }

    override fun getClickHandler(): ItemClickListener {
        return object : ItemClickListener {
            override fun onItemClick(position: Int) {
                when(position) {
                    in 0 until listProducer.getSize() ->
                        router.navigateTo(true) {
                            ListFragment.createListFragment(
                                listProducer.getList()[position]
                            )
                        }
                    else -> throw IllegalStateException()
                }
            }

        }
    }
}