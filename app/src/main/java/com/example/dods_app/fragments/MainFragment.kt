package com.example.dods_app.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dods_app.ItemClickListener
import com.example.dods_app.R
import com.example.dods_app.Router
import com.example.dods_app.adapters.ButtonsListAdapter

class MainFragment : Fragment() {

    private lateinit var router: Router

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        router = Router(requireActivity(), R.id.fragment_container)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val layout = inflater.inflate(R.layout.fragment_main, container, false)
        val buttons: RecyclerView = layout.findViewById(R.id.buttons)

        buttons.layoutManager = GridLayoutManager(
            inflater.context,
            2
        )
        buttons.adapter = ButtonsListAdapter(object : ItemClickListener {
            override fun onItemClick(position: Int) {
                when (position) {
                    0 -> router.navigateTo { CardsFragment.createCardsFragment()}
                    1 -> router.navigateTo(true, ::PhotoFragment)
                    else -> throw IllegalStateException()

                }
            }

        }, createButtons())

        return layout
    }

    private fun createButtons(): List<String> {
        return listOf(
            "Photos by bread",
            "Random photo"
        )
    }
}