package com.example.tau.lab.fragments

import android.app.Activity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.tau.lab.*
import com.example.tau.lab.util.Logger

class Life2Fragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Toast.makeText(getActivity(), "SecondFragment.onCreate()", Toast.LENGTH_LONG).show()
        Logger.d("Fragment 2", "onCreate")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_life_2, container, false)
        Toast.makeText(getActivity(), "SecondFragment.onCreateView()", Toast.LENGTH_LONG).show()
        Logger.d("Fragment 2", "onCreateView")
        return root
    }


    override fun onAttach(activity: Activity?) {
        super.onAttach(activity)
        Toast.makeText(getActivity(), "SecondFragment.onAttach()", Toast.LENGTH_LONG).show()
        Logger.d("Fragment 2", "onAttach")
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Toast.makeText(activity, "SecondFragment.onActivityCreated()", Toast.LENGTH_LONG).show()
        Logger.d("Fragment 2", "onActivityCreated")
    }

    override fun onStart() {
        super.onStart()
        Toast.makeText(activity, "SecondFragment.onStart()", Toast.LENGTH_LONG).show()
        Logger.d("Fragment 2", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Toast.makeText(activity, "SecondFragment.onResume()", Toast.LENGTH_LONG).show()
        Logger.d("Fragment 2", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Toast.makeText(activity, "SecondFragment.onPause()", Toast.LENGTH_LONG).show()
        Logger.d("Fragment 2", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Toast.makeText(activity, "SecondFragment.onStop()", Toast.LENGTH_LONG).show()
        Logger.d("Fragment 2", "onStop")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Toast.makeText(activity, "SecondFragment.onDestroyView()", Toast.LENGTH_LONG).show()
        Logger.d("Fragment 2", "onDestroyView")
    }

    override fun onDestroy() {
        super.onDestroy()
        Toast.makeText(activity, "SecondFragment.onDestroy()", Toast.LENGTH_LONG).show()
        Logger.d("Fragment 2", "onDestroy")
    }

    override fun onDetach() {
        super.onDetach()
        Toast.makeText(activity, "SecondFragment.onDetach()", Toast.LENGTH_LONG).show()
        Logger.d("Fragment 2", "onDetach")
    }

    companion object {
        const val FRAGMENT_TAG = "com.example.tau.lab.fragments.Life2Fragment"

        fun newInstance(): Life2Fragment {
            val fragment = Life2Fragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }
}
