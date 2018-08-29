package com.example.tau.lab

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.tau.lab.fragments.AnimalDetailsFragment
import com.example.tau.lab.fragments.ListFragment
import com.example.tau.lab.fragments.ButtonsFragment
import com.example.tau.lab.model.Animal

class MainActivity : AppCompatActivity(),
        ButtonsFragment.Listener,
        ListFragment.Listener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val fragment = ButtonsFragment.newInstance(getString(R.string.mammals), getString(R.string.birds))
        supportFragmentManager.beginTransaction()
                .add(R.id.buttonsFrame, fragment, ButtonsFragment.FRAGMENT_TAG)
                .commit()
    }

    override fun showList(animalType: AnimalType) {
        val listFragment = supportFragmentManager.findFragmentByTag(ListFragment.FRAGMENT_TAG) as ListFragment?
        if (listFragment != null) {
            listFragment.updateContent(animalType)
            val animalDetailsFragment = supportFragmentManager.findFragmentByTag(AnimalDetailsFragment.FRAGMENT_TAG)
            if (animalDetailsFragment != null) supportFragmentManager.popBackStack()
        } else {
            supportFragmentManager.beginTransaction()
                    .add(R.id.animalsFrame, ListFragment.newInstance(animalType), ListFragment.FRAGMENT_TAG)
                    .addToBackStack(ListFragment.FRAGMENT_TAG)
                    .commit()
        }
    }

    override fun animalClicked(animal: Animal) {
        val fragment = supportFragmentManager.findFragmentByTag(AnimalDetailsFragment.FRAGMENT_TAG) as AnimalDetailsFragment?
        if (fragment != null) {
            fragment.updateContent(animal)
        } else {
            supportFragmentManager.beginTransaction()
                    .add(R.id.animalDetailsFrame, AnimalDetailsFragment.newInstance(animal), AnimalDetailsFragment.FRAGMENT_TAG)
                    .addToBackStack(AnimalDetailsFragment.FRAGMENT_TAG)
                    .commit()
        }
    }
}

