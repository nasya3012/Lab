package com.example.tau.lab.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import com.example.tau.lab.PagerAdapter
import com.example.tau.lab.R
import com.example.tau.lab.fragments.AnimalDetailsFragment
import com.example.tau.lab.fragments.ListFragment
import com.example.tau.lab.model.Animal

class ViewPagerActivity : AppCompatActivity(),
        ListFragment.Listener{

    private var adapter: PagerAdapter? = null
    private var viewPager: ViewPager? = null
    private var tabLayout: TabLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_pager)
        adapter = PagerAdapter(supportFragmentManager, resources)
        viewPager = findViewById(R.id.functionsPager)
        tabLayout = findViewById(R.id.tabs)
        tabLayout?.setupWithViewPager(viewPager)
        viewPager?.adapter = adapter
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

    companion object {
        fun newInstance(context: Context) {
            val intent = Intent(context, ViewPagerActivity::class.java)
            context.startActivity(intent)
        }
    }
}