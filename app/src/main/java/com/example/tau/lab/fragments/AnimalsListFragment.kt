package com.example.tau.lab.fragments

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.tau.lab.Adapter
import com.example.tau.lab.CustomLinearLayoutManager
import com.example.tau.lab.R
import com.example.tau.lab.model.Animal

class AnimalsListFragment : Fragment() {

    private var adapter: Adapter? = null
    private var listView: RecyclerView? = null
    private var listener: Listener? = null


    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is Listener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement Listener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = Adapter(Animal.getMockMammals(), listener)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_animals_list, container, false)
        listView = root.findViewById(R.id.animalsList)
        initList()
        return root
    }

    private fun initList() {
        if (context == null) return
        listView?.layoutManager = CustomLinearLayoutManager(context!!)
        listView?.adapter = adapter
    }


    interface Listener: Adapter.Listener


    companion object {
        const val FRAGMENT_TAG = "com.example.tau.lab.fragments.AnimalsListFragment"
    }
}
