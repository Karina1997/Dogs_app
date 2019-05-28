package com.example.dods_app.factories.impl

import com.example.dods_app.ItemClickListener
import com.example.dods_app.Router
import com.example.dods_app.factories.ListContentFactory
import com.example.dods_app.fragments.CardsFragment

class BreadListContentFactory(val router: Router, var breads: List<String>) : ListContentFactory {
    override fun addList(list: List<String>) {
       breads = breads.plus(list)
    }


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
                            CardsFragment.createCardsFragment(
                                breads[position]
                            )
                        }
                    else -> throw IllegalStateException()
                }
            }

        }
    }
}