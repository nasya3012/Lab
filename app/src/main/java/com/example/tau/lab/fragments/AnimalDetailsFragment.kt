package com.example.tau.lab.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.tau.lab.util.ImageUtils
import com.example.tau.lab.R
import com.example.tau.lab.model.Animal

class AnimalDetailsFragment : Fragment(),
        CommentDialogFragment.Listener{


    private var animal: Animal? = null
    private var animalPictureIV: ImageView? = null
    private var animalNameTV: TextView? = null
    private var animalComment: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {animal = it.getParcelable(ANIMAL)}
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_animal_details, container, false)
        root.findViewById<View>(R.id.animalPicture).setOnClickListener {
            if (animal != null) CommentDialogFragment.showDialog(childFragmentManager, animal!!, this)
        }
        initUi(root)
        setUpUi()
        return root
    }

    override fun updateContent() {
        animalComment?.setText(animal?.animalcomment)
    }

    fun updateContent(animal: Animal) {
        this.animal = animal
        setUpUi()
    }

    private fun initUi(root: View) {
        animalPictureIV = root.findViewById(R.id.animalPicture)
        animalNameTV = root.findViewById(R.id.animalName)
        animalComment = root.findViewById(R.id.comment)
    }

    private fun setUpUi() {
        if (animalPictureIV != null && context != null && animal != null)
            ImageUtils.processAnimalImage(animal!!, animalPictureIV!!, false)
        animalNameTV?.text = animal?.animalName
        animalComment?.setText(animal?.animalcomment)
    }

    companion object {
        const val FRAGMENT_TAG = "com.example.tau.lab.fragments.AnimalDetailsFragment"
        private const val ANIMAL = "animal"
        private const val LOG_TAG = "AnimalDetailsFragment"

        fun newInstance(animal: Animal): AnimalDetailsFragment {
            val fragment = AnimalDetailsFragment()
            val args = Bundle()
            args.putParcelable(ANIMAL, animal)
            fragment.arguments = args
            return fragment
        }
    }
}