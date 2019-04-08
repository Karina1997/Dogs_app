package com.example.dods_app.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dods_app.ItemClickListener
import com.example.dods_app.R
import com.example.dods_app.Router
import com.example.dods_app.adapters.ButtonsListAdapter
import com.example.dods_app.factories.ListContentFactory
import com.example.dods_app.factories.impl.BreadListContentFactory
import com.example.dods_app.factories.impl.SubBreadListContentFactory
import com.example.dods_app.httpServices.AsyncResponse
import com.example.dods_app.httpServices.BreadsRequest
import com.example.dods_app.httpServices.UrlGetter
import com.squareup.okhttp.OkHttpClient

class ListFragment : Fragment(), AsyncResponse {

    companion object {
        private const val BREAD = "BREAD"

        fun createListFragment(bread: String? = null): Fragment {
            val fragment = ListFragment()
            val args = Bundle()
            args.putString(BREAD, bread)
            fragment.arguments = args
            return fragment
        }
    }

    private lateinit var router: Router
    private lateinit var adapter: ButtonsListAdapter
    private lateinit var buttons: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        router = Router(requireActivity(), R.id.fragment_container)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val layout = inflater.inflate(R.layout.fragment_main, container, false)
        buttons = layout.findViewById(R.id.buttons)
        buttons.layoutManager = LinearLayoutManager(
            requireContext(),
            RecyclerView.VERTICAL,
            false
        )

        adapter = ButtonsListAdapter(object : ItemClickListener{
            override fun onItemClick(position: Int) {}
        }, listOf())
        buttons.adapter = adapter

        BreadsRequest(OkHttpClient(), this)
            .execute(UrlGetter().getBreadorSubBreadUrl(arguments?.getString(BREAD)))
        return layout
    }

    override fun processFinished(output: List<String>) {
        val contentFactory = getContentFactory(arguments, output)
        adapter = ButtonsListAdapter(contentFactory.getClickHandler(), contentFactory.getList())
        buttons.adapter = adapter
    }

    private fun getContentFactory(args: Bundle?, list: List<String>): ListContentFactory {
        return args?.getString(BREAD) ?.let {
            return SubBreadListContentFactory(router, it, list)
        } ?: BreadListContentFactory(router, list)
    }
}