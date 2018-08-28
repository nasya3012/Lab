package com.example.tau.lab

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.tau.lab.fragments.AnimalDetailsFragment
import com.example.tau.lab.fragments.MammalsFragment
import com.example.tau.lab.fragments.ButtonsFragment
import com.example.tau.lab.model.Animal

class MainActivity : AppCompatActivity(),
        ButtonsFragment.Listener,
        MammalsFragment.Listener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val fragment = ButtonsFragment.newInstance(getString(R.string.mammals), getString(R.string.birds))
        supportFragmentManager.beginTransaction()
                .add(R.id.buttonsFrame, fragment, ButtonsFragment.FRAGMENT_TAG)
                .commit()
    }

    override fun showMammals() {
        val mammalsFragment = supportFragmentManager.findFragmentByTag(MammalsFragment.FRAGMENT_TAG)
        if (mammalsFragment != null) return
        val animalDetailsFragment = supportFragmentManager.findFragmentByTag(AnimalDetailsFragment.FRAGMENT_TAG)
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.animalsFrame, MammalsFragment(), MammalsFragment.FRAGMENT_TAG)
        if (animalDetailsFragment != null) transaction.remove(animalDetailsFragment)
        transaction.addToBackStack(MammalsFragment.FRAGMENT_TAG)
        transaction.commit()
    }

    override fun showBirds() {
        Toast.makeText(this,"showBirds clicked", Toast.LENGTH_SHORT).show()
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

