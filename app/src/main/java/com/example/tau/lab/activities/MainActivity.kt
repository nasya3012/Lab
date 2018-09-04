package com.example.tau.lab.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
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
}