package com.example.tau.lab

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class ButtonsFragment : Fragment() {

    private var buttonText: String? = null
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
        arguments?.let { buttonText = it.getString(BUTTON_TEXT) }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_buttons, container, false)
        val showToast = root.findViewById<TextView>(R.id.showToast)
        showToast?.text = buttonText
        showToast?.setOnClickListener{listener?.showToast()}
        root.findViewById<View>(R.id.showAnimalsList)?.setOnClickListener{listener?.showAnimalsList()}
        return root
    }

    interface Listener{
        fun showToast()
        fun showAnimalsList()
    }

    companion object {
        const val FRAGMENT_TAG = "com.example.tau.lab.ButtonsFragment"
        private const val BUTTON_TEXT = "BUTTON_TEXT"

        fun newInstance(buttonText: String): ButtonsFragment{
            val fragment = ButtonsFragment()
            val findings = Bundle()
            findings.putString(BUTTON_TEXT, buttonText)
            fragment.arguments = findings
            return fragment
        }
    }
}
