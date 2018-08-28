package com.example.tau.lab.fragments

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.tau.lab.R

class ButtonsFragment : Fragment() {

    private var button1Text: String? = null
    private var button2Text: String? = null
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
        arguments?.let {
            button1Text = it.getString(BUTTON_1_TEXT)
            button2Text = it.getString(BUTTON_2_TEXT)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_buttons, container, false)
        val button1 = root.findViewById<TextView>(R.id.button1)
        button1?.text = button1Text
        button1?.setOnClickListener{listener?.showMammals()}
        val button2 = root.findViewById<TextView>(R.id.button2)
        button2?.text = button2Text
        button2?.setOnClickListener{listener?.showBirds()}
        return root
    }

    interface Listener{
        fun showMammals()
        fun showBirds()
    }

    companion object {
        const val FRAGMENT_TAG = "com.example.tau.lab.fragments.ButtonsFragment"
        private const val BUTTON_1_TEXT = "BUTTON_1_TEXT"
        private const val BUTTON_2_TEXT = "BUTTON_2_TEXT"

        fun newInstance(button1Text: String, button2Text: String): ButtonsFragment {
            val fragment = ButtonsFragment()
            val findings = Bundle()
            findings.putString(BUTTON_1_TEXT, button1Text)
            findings.putString(BUTTON_2_TEXT, button2Text)
            fragment.arguments = findings
            return fragment
        }
    }
}
