package com.example.tau.lab.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.tau.lab.R
import com.example.tau.lab.model.Animal

class AnimalFragment : Fragment() {

    private var animal: Animal? = null
    private var image: ImageView? = null
    private var text: TextView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {animal = it.getParcelable(ANIMAL)}
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_animal, container, false)
        initUi(root)
        setUpUi()
        return root
    }

    fun updateContent(animal: Animal) {
        this.animal = animal
        setUpUi()
    }

    private fun initUi(root: View) {
        image = root.findViewById(R.id.image)
        text = root.findViewById(R.id.text)
    }

    private fun setUpUi() {
        if (image != null) Glide.with(context).load(animal?.animalPicture).into(image)
        text?.text = animal?.animalName
    }


    companion object {
        const val FRAGMENT_TAG = "com.example.tau.lab.fragments.AnimalFragment"
        private const val ANIMAL = "animal"

        fun newInstance(animal: Animal): AnimalFragment {
            val fragment = AnimalFragment()
            val findings = Bundle()
            findings.putParcelable(ANIMAL, animal)
            fragment.arguments = findings
            return fragment
        }
    }
}