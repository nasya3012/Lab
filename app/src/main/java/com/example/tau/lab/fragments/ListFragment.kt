package com.example.tau.lab.fragments

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.tau.lab.*
import com.example.tau.lab.model.Animal

class ListFragment : Fragment() {

    private var adapter: Adapter? = null
    private var listView: RecyclerView? = null
    private var listener: Listener? = null
    private var animalType: AnimalType? = null


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
        arguments?.let {animalType = it.getSerializable(ListFragment.ANIMAL_TYPE) as AnimalType}
        val animalsList = when (animalType) {
            AnimalType.MAMMAL -> LabApp.mammals
            AnimalType.BIRD -> LabApp.birds
            null -> null
        }
        adapter = Adapter(animalsList, listener)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_list, container, false)
        listView = root.findViewById(R.id.animalsList)
        initList()
        return root
    }

    fun updateContent(animalType: AnimalType) {
        this.animalType = animalType
        val animalsList = when (animalType) {
            AnimalType.MAMMAL -> Animal.getMockMammals()
            AnimalType.BIRD -> Animal.getMockBirds()
        }
        adapter?.updateContent(animalsList)
    }

    private fun initList() {
        if (context == null) return
        listView?.layoutManager = CustomLinearLayoutManager(context!!)
        listView?.adapter = adapter
    }


    interface Listener: Adapter.Listener


    companion object {
        const val FRAGMENT_TAG = "com.example.tau.lab.fragments.ListFragment"
        private const val ANIMAL_TYPE = "animalType"

        fun newInstance(animalType: AnimalType): ListFragment {
            val fragment = ListFragment()
            val args = Bundle()
            args.putSerializable(ANIMAL_TYPE, animalType)
            fragment.arguments = args
            return fragment
        }
    }
}
