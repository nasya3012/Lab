package com.example.tau.lab.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.Toast
import com.example.tau.lab.R
import com.example.tau.lab.fragments.SpinnerFragment

class SpinnerActivity : AppCompatActivity(),
        SpinnerFragment.Listener{


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spinner)

        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setTitle(R.string.spinner)

        supportFragmentManager.beginTransaction()
                .add(R.id.spinnerFrame, SpinnerFragment.newInstance(), SpinnerFragment.FRAGMENT_TAG)
                .commit()
        findViewById<Button>(R.id.button).setOnClickListener {
            val fragment = supportFragmentManager.findFragmentByTag(SpinnerFragment.FRAGMENT_TAG) as SpinnerFragment?
            showToast(fragment?.getSelectedItem())
        }
    }

    override fun showToast(msg: String?) = Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()


    companion object {
        fun newInstance(context: Context) {
            val intent = Intent(context, SpinnerActivity::class.java)
            context.startActivity(intent)
        }
    }
}

