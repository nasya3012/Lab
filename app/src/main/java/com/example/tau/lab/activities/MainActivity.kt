package com.example.tau.lab.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.example.tau.lab.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<View>(R.id.openButtonsActivity).setOnClickListener{ButtonsActivity.newInstance(this)}
        findViewById<View>(R.id.openViewPagerActivity).setOnClickListener{ViewPagerActivity.newInstance(this)}
        findViewById<View>(R.id.openButtons).setOnClickListener{ButtonsActivity.newInstance(this)}
        findViewById<View>(R.id.openViewPager).setOnClickListener{ViewPagerActivity.newInstance(this)}
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setTitle(R.string.app_name)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.traffic -> {
            TrafficActivity.newInstance(this)
            true
        }
        R.id.converter -> {
            ConverterActivity.newInstance(this)
            true
        }
        R.id.browser -> {
            BrowserActivity.newInstance(this)
            true
        }
        R.id.spinner -> {
            SpinnerActivity.newInstance(this)
            true
        }
        R.id.life -> {
            LifeActivity.newInstance(this)
            true
        }
        else -> super.onOptionsItemSelected(item)
    }
}