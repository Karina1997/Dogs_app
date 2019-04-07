package com.example.dods_app.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment

class PhotoFragment : Fragment() {
    companion object {
        private const val BREAD = "BREAD"
        private const val SUBBREAD = "SUBBREAD"


        fun createListFragment(bread: String? = null, subBread: String? = null): Fragment {
            val fragment = ListFragment()
            val args = Bundle()
            args.putString(BREAD, bread)
            args.putString(SUBBREAD, subBread)
            fragment.arguments = args
            return fragment
        }
    }
}