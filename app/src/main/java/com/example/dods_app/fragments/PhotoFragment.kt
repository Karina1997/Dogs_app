package com.example.dods_app.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dods_app.R
import com.example.dods_app.adapters.PhotoListAdapter
import com.example.dods_app.httpServices.AsyncResponse
import com.example.dods_app.httpServices.BreadsRequest
import com.example.dods_app.httpServices.UrlGetter
import com.squareup.okhttp.OkHttpClient

class PhotoFragment : Fragment(), AsyncResponse {

    lateinit var photos: RecyclerView


    override fun processFinished(output: List<String>) {
        photos.adapter = context?.let { PhotoListAdapter(output, resources) }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val layout = inflater.inflate(R.layout.image_list_fragment, container, false)
        BreadsRequest(OkHttpClient(), this)
            .execute(
                UrlGetter().getBreadOrSubBreadUrl(
                    "images",
                    arguments?.getString(BREAD),
                    arguments?.getString(SUBBREAD)
                )
            )
        photos = layout.findViewById(R.id.recyclerView)
        photos.layoutManager = LinearLayoutManager(
            requireContext(),
            RecyclerView.VERTICAL,
            false
        )
        return layout
    }

    companion object {
        private const val BREAD = "BREAD"
        private const val SUBBREAD = "SUBBREAD"

        fun createPhotoFragment(bread: String? = null, subBread: String? = null): Fragment {
            val fragment = PhotoFragment()
            val args = Bundle()
            args.putString(BREAD, bread)
            args.putString(SUBBREAD, subBread)
            fragment.arguments = args
            return fragment
        }
    }
}