package com.example.tau.lab.fragments

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.example.tau.lab.R

class SpinnerFragment : Fragment() {

    private var animalsSpinner: Spinner? = null
    private var adapter: ArrayAdapter<String>? = null
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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_spinner, container, false)

        initUi(root)
        animalsSpinner?.adapter = adapter
        animalsSpinner?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, itemSelected: View, position: Int, selectedId: Long) {
                listener?.showToast("selected position=$position")
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                listener?.showToast("nothing selected")
            }
        }
        return root
    }

    private fun initUi(root: View?) {
        val animalsArray = resources.getStringArray(R.array.page_titles)
        adapter = ArrayAdapter(context, android.R.layout.simple_list_item_1, animalsArray)
        animalsSpinner = root?.findViewById(R.id.animalsSpinner)
    }

    fun getSelectedItem(): String {
        return "Ваш выбор: " + animalsSpinner?.selectedItem.toString() +", позиция " + animalsSpinner?.selectedItemPosition?.toInt()
    }


    interface Listener {
        fun showToast(msg: String?)
    }

    companion object {
        const val FRAGMENT_TAG = "SpinnerFragment"

        fun newInstance(): SpinnerFragment {
            val fragment = SpinnerFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }
}
