package com.example.tau.lab

import android.content.Context
import android.support.v4.app.FragmentManager
import com.example.tau.lab.fragments.AnimalDetailsFragment
import com.example.tau.lab.fragments.ButtonsFragment
import com.example.tau.lab.fragments.ListFragment
import com.example.tau.lab.model.Animal

class FragmentsCoordinatorImpl(
        private val fragmentManager: FragmentManager

): FragmentsCoordinator {

    override fun showButtons(context: Context) {
        val fragment = ButtonsFragment.newInstance(context.getString(R.string.mammals), context.getString(R.string.birds))
        fragmentManager.beginTransaction()
                .add(R.id.buttonsFrame, fragment, ButtonsFragment.FRAGMENT_TAG)
                .commit()
    }

    override fun showList(animalType: AnimalType) {
        var listFragment: ListFragment? = null

        if (listFragment != null) {
            listFragment.updateContent(animalType)
            val animalDetailsFragment = fragmentManager.findFragmentByTag(AnimalDetailsFragment.FRAGMENT_TAG)
            if (animalDetailsFragment != null) fragmentManager.popBackStack()
        } else {
            listFragment = ListFragment.newInstance(animalType)
            fragmentManager.beginTransaction()
                    .add(R.id.animalsFrame, listFragment, ListFragment.FRAGMENT_TAG)
                    .addToBackStack(ListFragment.FRAGMENT_TAG)
                    .commit()
        }
    }

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