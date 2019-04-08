package com.example.dods_app.factories.impl

import com.example.dods_app.ItemClickListener
import com.example.dods_app.Router
import com.example.dods_app.factories.ListContentFactory
import com.example.dods_app.fragments.ListFragment

class BreadListContentFactory(val router: Router, var breads: List<String>) : ListContentFactory {


    override fun getList(): List<String> {
        breads += "All"
        return breads
    }

    override fun getClickHandler(): ItemClickListener {
        return object : ItemClickListener {
            override fun onItemClick(position: Int) {
                when (position) {
                    in 0 until breads.size ->
                        router.navigateTo(true) {
                            ListFragment.createListFragment(
                                breads[position]
                            )
                        }
                    else -> throw IllegalStateException()
                }
            }

        }
    }
}