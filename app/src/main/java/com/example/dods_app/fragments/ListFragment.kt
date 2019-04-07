package com.example.dods_app.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dods_app.R
import com.example.dods_app.Router
import com.example.dods_app.adapters.ButtonsListAdapter
import com.example.dods_app.producers.ListProducer
import com.example.dods_app.producers.impl.BreadsListProducerImpl
import com.example.dods_app.producers.impl.SubBreadsListProducerImpl

class ListFragment : Fragment() {

    companion object {
        private const val CONTANT = "CONTANT"
        private const val BREAD = "BREAD"

        fun createListFragment(contant: Int, bread: String? = null): Fragment {
            val fragment = ListFragment()
            val args = Bundle()
            args.putInt(CONTANT, contant)
            args.putString(BREAD, bread)
            fragment.arguments = args
            return fragment
        }
    }

    private lateinit var router: Router
    private lateinit var listProducer: ListProducer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        router = Router(requireActivity(), R.id.fragment_container)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val layout = inflater.inflate(R.layout.fragment_main, container, false)
        val buttons: RecyclerView = layout.findViewById(R.id.buttons)

        val content = arguments?.getInt(CONTANT) ?: 0


        when (content) {
            0 -> createBreadsList(buttons)
            1 -> createSubBreadsList(buttons)
            else -> throw IllegalStateException()
        }

        return layout
    }

    private fun createBreadsList(buttons: RecyclerView) {
        listProducer = BreadsListProducerImpl()
        buttons.layoutManager = LinearLayoutManager(
            requireContext(),
            RecyclerView.VERTICAL,
            false
        )
        buttons.adapter = ButtonsListAdapter(createButtons(), ::onBreadButtonClick)
    }

    private fun createSubBreadsList(buttons: RecyclerView) {
        listProducer = SubBreadsListProducerImpl(arguments?.getString(BREAD) ?: "")
        buttons.layoutManager = LinearLayoutManager(
            requireContext(),
            RecyclerView.VERTICAL,
            false
        )
        buttons.adapter = ButtonsListAdapter(createButtons(), ::onSubBreadButtonClick)
    }

    private fun createButtons(): Array<String> {
        return listProducer.getList().toTypedArray()
    }

    private fun onBreadButtonClick(position: Int, bread: String?) = when (position) {
        in 0 until listProducer.getSize() ->
            router.navigateTo(true) { ListFragment.createListFragment(1, bread) }
        else -> throw IllegalStateException()
    }

    private fun onSubBreadButtonClick(position: Int, bread: String?) = when (position) {
        in 0 until listProducer.getSize() ->
            router.navigateTo(true) {PhotoFragment.createListFragment(bread)}
        else -> throw IllegalStateException()
    }
}