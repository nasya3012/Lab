package com.example.tau.lab

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.tau.lab.fragments.AnimalFragment
import com.example.tau.lab.fragments.AnimalsListFragment
import com.example.tau.lab.model.Animal

class MainActivity : AppCompatActivity(),
        ButtonsFragment.Listener,
        AnimalsListFragment.Listener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction()
                .add(R.id.buttonsFrame, ButtonsFragment.newInstance("Тост"), ButtonsFragment.FRAGMENT_TAG)
                .commit()
    }

    override fun showToast() {
        Toast.makeText(this,"click", Toast.LENGTH_SHORT).show()
    }

    override fun showAnimalsList() {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.animalsFrame, AnimalsListFragment(), AnimalsListFragment.FRAGMENT_TAG)
        transaction.addToBackStack(AnimalsListFragment.FRAGMENT_TAG)
        transaction.commit()
    }

    override fun animalClicked(animal: Animal) {
        val fragment = supportFragmentManager.findFragmentByTag(AnimalFragment.FRAGMENT_TAG) as AnimalFragment?
        if (fragment != null) {
            fragment.updateContent(animal)
        } else {
            supportFragmentManager.beginTransaction()
                    .add(R.id.individualAnimalFrame, AnimalFragment.newInstance(animal), AnimalFragment.FRAGMENT_TAG)
                    .addToBackStack(AnimalFragment.FRAGMENT_TAG)
                    .commit()
        }
    }
}

