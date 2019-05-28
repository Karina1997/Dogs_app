package com.example.dods_app.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dods_app.ListLoader
import com.example.dods_app.R
import com.example.dods_app.Router
import com.example.dods_app.adapters.CardsAdapter
import com.example.dods_app.executor.ListAndPhotoUsageExecutor
import com.example.dods_app.factories.ListContentFactory
import com.example.dods_app.factories.impl.BreadListContentFactory
import com.example.dods_app.factories.impl.SubBreadListContentFactory
import com.example.dods_app.httpServices.UrlGetter

class CardsFragment : Fragment() {
    companion object {
        private const val BREAD = "BREAD"

        fun createCardsFragment(bread: String? = null): Fragment {
            val fragment = CardsFragment()
            val args = Bundle()
            args.putString(BREAD, bread)
            fragment.arguments = args
            return fragment
        }
    }

    private lateinit var router: Router
    private lateinit var cards: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        router = Router(requireActivity(), R.id.fragment_container)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val layout = inflater.inflate(R.layout.fragment_main, container, false)
        cards = layout.findViewById(R.id.buttons)

        cards.layoutManager = LinearLayoutManager(
            requireContext(),
            RecyclerView.VERTICAL,
            false
        )

        val adapter = CardsAdapter(getContentFactory(arguments))
        cards.adapter = adapter

        val bread = arguments?.getString(BREAD)
        context?.let {
            ListLoader(
                it,
                bread + "all",
                ListAndPhotoUsageExecutor(adapter, bread)
                , true
            ).loadListFromStorage(UrlGetter().getBreadOrSubBreadUrl("list", param = bread))
        }
        return layout
    }

    private fun getContentFactory(args: Bundle?): ListContentFactory {
        return args?.getString(BREAD)?.let {
            return SubBreadListContentFactory(router, it, emptyList())
        } ?: BreadListContentFactory(router, emptyList())
    }
}