package com.example.tau.lab.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import com.example.tau.lab.R

class SpinnerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spinner)
        val animalsArray = resources.getStringArray(R.array.page_titles)
        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, animalsArray)

        val animalsSpinner = findViewById<Spinner>(R.id.animalsSpinner)
        animalsSpinner.adapter = adapter
        animalsSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, itemSelected: View, position: Int, selectedId: Long) {
                Toast.makeText(this@SpinnerActivity, "selected position=$position", Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                Toast.makeText(this@SpinnerActivity, "nothing selected", Toast.LENGTH_SHORT).show()
            }
        }
        findViewById<View>(R.id.button).setOnClickListener{
            val spinner = findViewById<View>(R.id.animalsSpinner) as Spinner
            Toast.makeText(applicationContext,"Ваш выбор: " + spinner.selectedItem.toString() +", позиция " + spinner.selectedItemPosition.toInt() , Toast.LENGTH_SHORT).show()
        }
    }

    companion object {
        fun newInstance(context: Context) {
            val intent = Intent(context, SpinnerActivity::class.java)
            context.startActivity(intent)
        }
    }
}

