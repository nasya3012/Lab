package com.example.tau.lab.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.TextView
import com.example.tau.lab.R

class TrafficActivity : AppCompatActivity(){

    private var text: TextView? = null
    private var root: View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_traffic)
        initUi()
    }

    private fun initUi() {

        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setTitle(R.string.traffic)

        root = findViewById(R.id.root)
        text = findViewById(R.id.text)

        findViewById<View>(R.id.buttonRed).setOnClickListener{showColor(ColorType.RED)}
        findViewById<View>(R.id.buttonYellow).setOnClickListener{showColor(ColorType.YELLOW)}
        findViewById<View>(R.id.buttonGreen).setOnClickListener{showColor(ColorType.GREEN)}
    }

    private fun showColor(type: ColorType) {
        text?.setText(type.textId)
        root?.setBackgroundColor(ContextCompat.getColor(this, type.colorId))
    }


    enum class ColorType(val textId: Int, val colorId: Int) {
        RED(R.string.red, R.color.redColor),
        YELLOW(R.string.yellow, R.color.yellowColor),
        GREEN(R.string.green, R.color.greenColor);
    }
    companion object {
        fun newInstance(context: Context) {
            val intent = Intent(context, TrafficActivity::class.java)
            context.startActivity(intent)
        }
    }
}