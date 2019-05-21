package com.example.dods_app.factories.impl

import com.example.dods_app.ItemClickListener
import com.example.dods_app.Router
import com.example.dods_app.factories.ListContentFactory
import com.example.dods_app.fragments.PhotoFragment

class SubBreadListContentFactory(val router: Router, val bread: String, var subBreads: List<String>) :
    ListContentFactory {
    override fun setList(list: List<String>) {
        subBreads = list
    }

    override fun getList(): List<String> {
        subBreads += "All"
        return subBreads
    }

    override fun getClickHandler(): ItemClickListener {
        return object : ItemClickListener {
            override fun onItemClick(position: Int) {
                when (position) {
                    in 0 until subBreads.size ->
                        router.navigateTo(true) { PhotoFragment.createPhotoFragment(bread, subBreads[position]) }
                    else -> throw IllegalStateException()
                }
            }
        }
    }

}