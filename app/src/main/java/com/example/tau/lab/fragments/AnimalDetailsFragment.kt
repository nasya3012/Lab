package com.example.tau.lab.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.tau.lab.ImageUtils
import com.example.tau.lab.R
import com.example.tau.lab.model.Animal

class AnimalDetailsFragment : Fragment() {

    private var animal: Animal? = null
    private var animalPictureIV: ImageView? = null
    private var animalNameTV: TextView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {animal = it.getParcelable(ANIMAL)}
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_animal_details, container, false)
        initUi(root)
        setUpUi()
        return root
    }

    fun updateContent(animal: Animal) {
        this.animal = animal
        setUpUi()
    }

    private fun initUi(root: View) {
        animalPictureIV = root.findViewById(R.id.animalPicture)
        animalNameTV = root.findViewById(R.id.animalName)
    }

    private fun setUpUi() {
        if (animalPictureIV != null && context != null && animal != null)
            ImageUtils.processAnimalImage(animal!!, animalPictureIV!!, false)
        animalNameTV?.text = animal?.animalName
    }

    companion object {
        const val FRAGMENT_TAG = "com.example.tau.lab.fragments.AnimalDetailsFragment"
        private const val ANIMAL = "animal"

        fun newInstance(animal: Animal): AnimalDetailsFragment {
            val fragment = AnimalDetailsFragment()
            val findings = Bundle()
            findings.putParcelable(ANIMAL, animal)
            fragment.arguments = findings
            return fragment
        }
    }
}