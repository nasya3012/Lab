package com.example.tau.lab.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.*
import com.example.tau.lab.R
import com.example.tau.lab.fragments.ConverterFragment

class ConverterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_converter)

        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setTitle(R.string.converter)

        supportFragmentManager.beginTransaction()
                .add(R.id.fragmentConverter, ConverterFragment.newInstance(), ConverterFragment.FRAGMENT_TAG)
                .commit()
        findViewById<Button>(R.id.buttonConverter).setOnClickListener {
            val fragment = supportFragmentManager.findFragmentByTag(ConverterFragment.FRAGMENT_TAG) as ConverterFragment?
            fragment?.convert()
        }
    }

    companion object {
        private const val LOG_TAG = "ConverterActivity"

        fun newInstance(context: Context) {
            val intent = Intent(context, ConverterActivity::class.java)
            context.startActivity(intent)
        }
    }
}