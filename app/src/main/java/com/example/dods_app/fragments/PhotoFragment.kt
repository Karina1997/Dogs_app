package com.example.dods_app.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.dods_app.R

class PhotoFragment : Fragment() {
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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val layout = inflater.inflate(R.layout.image_fragment, container, false)

        return layout
    }
}