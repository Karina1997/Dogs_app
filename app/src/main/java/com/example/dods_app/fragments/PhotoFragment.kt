package com.example.dods_app.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dods_app.executor.ImagePathListUsageExecutor
import com.example.dods_app.ListLoader
import com.example.dods_app.R
import com.example.dods_app.httpServices.UrlGetter

class PhotoFragment : Fragment() {

    lateinit var photos: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val layout = inflater.inflate(R.layout.image_list_fragment, container, false)
        val bread =  arguments?.getString(BREAD)
        val subBread =  arguments?.getString(SUBBREAD)
        val fileName = bread + subBread + "images"

        photos = layout.findViewById(R.id.recyclerView)
        photos.layoutManager = LinearLayoutManager(
            requireContext(),
            RecyclerView.VERTICAL,
            false
        )

        context?.let {
            ListLoader(
                it,
                fileName ,
                ImagePathListUsageExecutor(photos, resources),
            bread != null || subBread != null).loadListFromStorage(
                UrlGetter().getBreadOrSubBreadUrl("images", bread, subBread)
            )
        }
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