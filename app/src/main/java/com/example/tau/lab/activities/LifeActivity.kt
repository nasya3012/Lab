package com.example.tau.lab.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.example.tau.lab.R
import com.example.tau.lab.fragments.Life1Fragment
import com.example.tau.lab.fragments.Life2Fragment
import com.example.tau.lab.util.Logger

class LifeActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_life)

        Toast.makeText(this, "onCreate()", Toast.LENGTH_SHORT).show()
        Logger.i(LOG_TAG, "onCreate")

        findViewById<Button>(R.id.buttonExit).setOnClickListener {finish()}
        findViewById<View>(R.id.fragment1).setOnClickListener {
            val fragment = Life1Fragment.newInstance()
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragmentFrame, fragment, Life1Fragment.FRAGMENT_TAG).commit() }
        findViewById<View>(R.id.fragment2).setOnClickListener {
            val fragment = Life2Fragment.newInstance()
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragmentFrame, fragment, Life2Fragment.FRAGMENT_TAG).commit() }
    }

    override fun onStart() {
        super.onStart()
        Toast.makeText(getApplicationContext(), "onStart", Toast.LENGTH_SHORT).show()
        Logger.i(LOG_TAG, "onStart()")
    }

    override fun onResume() {
        super.onResume()
        Toast.makeText(getApplicationContext(), "onResume", Toast.LENGTH_SHORT).show()
        Logger.i(LOG_TAG, "onResume()")
    }

    override fun onPause() {
        super.onPause()
        Toast.makeText(getApplicationContext(), "onPause", Toast.LENGTH_SHORT).show()
        Logger.i(LOG_TAG, "onPause()")
    }

    override fun onStop() {
        super.onStop()
        Toast.makeText(getApplicationContext(), "onStop", Toast.LENGTH_SHORT).show()
        Logger.i(LOG_TAG, "onStop()")
    }

    override fun onRestart() {
        super.onRestart()
        Toast.makeText(getApplicationContext(), "onRestart", Toast.LENGTH_SHORT).show()
        Logger.i(LOG_TAG, "onRestart()")
    }

    override fun onDestroy() {
        super.onDestroy()
        Toast.makeText(getApplicationContext(), "onDestroy", Toast.LENGTH_SHORT).show()
        Logger.i(LOG_TAG, "onDestroy()")
    }

    companion object {
        private const val LOG_TAG = "LifeActivity"

        fun newInstance(context: Context) {
            val intent = Intent(context, LifeActivity::class.java)
            context.startActivity(intent)
        }
    }
}
