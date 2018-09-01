package com.example.tau.lab

import android.support.v4.app.FragmentManager
import com.example.tau.lab.fragments.AnimalDetailsFragment
import com.example.tau.lab.model.Animal

class FragmentsCoordinatorImpl(
        private val fragmentManager: FragmentManager

): FragmentsCoordinator {

    override fun showAnimal(animal: Animal) {
        val fragment = fragmentManager.findFragmentByTag(AnimalDetailsFragment.FRAGMENT_TAG) as AnimalDetailsFragment?
        if (fragment != null) {
            fragment.updateContent(animal)
        } else {
            fragmentManager.beginTransaction()
                    .add(R.id.animalDetailsFrame, AnimalDetailsFragment.newInstance(animal), AnimalDetailsFragment.FRAGMENT_TAG)
                    .addToBackStack(AnimalDetailsFragment.FRAGMENT_TAG)
                    .commit()
        }
    }
}