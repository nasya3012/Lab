package com.example.tau.lab.activities

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.tau.lab.AnimalType
import com.example.tau.lab.FragmentsCoordinator
import com.example.tau.lab.FragmentsCoordinatorImpl
import com.example.tau.lab.R
import com.example.tau.lab.fragments.AnimalDetailsFragment
import com.example.tau.lab.fragments.ListFragment
import com.example.tau.lab.fragments.ButtonsFragment
import com.example.tau.lab.model.Animal

class ButtonsActivity : AppCompatActivity(),
        ButtonsFragment.Listener,
        ListFragment.Listener {

    private var listFragment: ListFragment? = null
    private var fragmentsCoordinator: FragmentsCoordinator? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buttons)
        val fragment = ButtonsFragment.newInstance(getString(R.string.mammals), getString(R.string.birds))
        supportFragmentManager.beginTransaction()
                .add(R.id.buttonsFrame, fragment, ButtonsFragment.FRAGMENT_TAG)
                .commit()
        fragmentsCoordinator = FragmentsCoordinatorImpl(supportFragmentManager)
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setTitle(R.string.title_activity_buttons)
    }

    override fun showList(animalType: AnimalType) {
        if (listFragment != null) {
            listFragment!!.updateContent(animalType)
            val animalDetailsFragment = supportFragmentManager.findFragmentByTag(AnimalDetailsFragment.FRAGMENT_TAG)
            if (animalDetailsFragment != null) supportFragmentManager.popBackStack()
        } else {
            listFragment = ListFragment.newInstance(animalType)
            supportFragmentManager.beginTransaction()
                    .add(R.id.animalsFrame, listFragment!!, ListFragment.FRAGMENT_TAG)
                    .addToBackStack(ListFragment.FRAGMENT_TAG)
                    .commit()
        }
    }

    override fun animalClicked(animal: Animal) {
        fragmentsCoordinator?.showAnimal(animal)
    }

    companion object {
        fun newInstance(context: Context) {
            val intent = Intent(context, ButtonsActivity::class.java)
            context.startActivity(intent)
        }
    }
}

