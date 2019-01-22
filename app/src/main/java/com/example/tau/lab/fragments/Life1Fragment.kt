package com.example.tau.lab.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.tau.lab.*
import com.example.tau.lab.util.Logger
import android.app.Activity

class Life1Fragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Toast.makeText(getActivity(), "FirstFragment.onCreate()", Toast.LENGTH_LONG).show()
        Logger.d("Fragment 1", "onCreate")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_life_1, container, false)
        Toast.makeText(getActivity(), "FirstFragment.onCreateView()", Toast.LENGTH_LONG).show()
        Logger.d("Fragment 1", "onCreateView")
        return root
    }

    override fun onAttach(activity: Activity?) {
        super.onAttach(activity)
        Toast.makeText(getActivity(), "FirstFragment.onAttach()", Toast.LENGTH_LONG).show()
        Logger.d("Fragment 1", "onAttach")
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Toast.makeText(activity, "FirstFragment.onActivityCreated()", Toast.LENGTH_LONG).show()
        Logger.d("Fragment 1", "onActivityCreated")
    }

    override fun onStart() {
        super.onStart()
        Toast.makeText(activity, "FirstFragment.onStart()", Toast.LENGTH_LONG).show()
        Logger.d("Fragment 1", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Toast.makeText(activity, "FirstFragment.onResume()", Toast.LENGTH_LONG).show()
        Logger.d("Fragment 1", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Toast.makeText(activity, "FirstFragment.onPause()", Toast.LENGTH_LONG).show()
        Logger.d("Fragment 1", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Toast.makeText(activity, "FirstFragment.onStop()", Toast.LENGTH_LONG).show()
        Logger.d("Fragment 1", "onStop")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Toast.makeText(activity, "FirstFragment.onDestroyView()", Toast.LENGTH_LONG).show()
        Logger.d("Fragment 1", "onDestroyView")
    }

    override fun onDestroy() {
        super.onDestroy()
        Toast.makeText(activity, "FirstFragment.onDestroy()", Toast.LENGTH_LONG).show()
        Logger.d("Fragment 1", "onDestroy")
    }

    override fun onDetach() {
        super.onDetach()
        Toast.makeText(activity, "FirstFragment.onDetach()", Toast.LENGTH_LONG).show()
        Logger.d("Fragment 1", "onDetach")
    }


    companion object {
        const val FRAGMENT_TAG = "com.example.tau.lab.fragments.Life1Fragment"

        fun newInstance(): Life1Fragment {
            val fragment = Life1Fragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }
}
