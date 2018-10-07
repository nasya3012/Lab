package com.example.tau.lab.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.widget.*
import com.example.tau.lab.R
import android.support.v7.app.AlertDialog
import android.util.Log
import android.view.View
import com.example.tau.lab.util.Constants
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.android.schedulers.AndroidSchedulers
import java.util.*
import java.util.concurrent.TimeUnit

class ConverterActivity : AppCompatActivity() {

    private var amountToConvert: EditText? = null
    private var currency: RadioGroup? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_converter)
        initUi()
        convert()
    }


    private fun initUi() {
        amountToConvert = findViewById(R.id.amountToConvert)
        currency = findViewById(R.id.currency)
        findViewById<View>(R.id.buttonConverter)?.setOnClickListener { convert() }
        if (amountToConvert != null) RxTextView.textChanges(amountToConvert!!)
                .debounce(Constants.CLICKS_DEBOUNCE_RATE_MS, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .map(CharSequence::toString)
                .subscribe({ value -> run {
                    if (value.length > 8) {
                        Toast.makeText(this, R.string.number_of_characters_entered, Toast.LENGTH_LONG).show()
                        amountToConvert?.setText(value.substring(0, 7))
                    }
                }})
                { throwable -> Log.e(LOG_TAG, "error in comment subscription: " + throwable.localizedMessage) }
    }


    private fun convert() {
        if (TextUtils.isEmpty(amountToConvert?.text)) {
            Toast.makeText(this, R.string.enter_convertible_unit, Toast.LENGTH_LONG).show()
        } else {
            AlertDialog.Builder(this).setMessage(getConvertedValue(amountToConvert?.text.toString().toFloat()))
                    .setPositiveButton(android.R.string.ok) { dialog, _ -> dialog.cancel() }
                    .show()
        }
    }

    private fun getConvertedValue(float: Float): String? =
            if (currency?.checkedRadioButtonId == R.id.radioButtonRubles) {
                formatValue(float * EXCHANGE_COURSE_RUB_USD, R.string.converted_value_rub_cop)
            } else {
                formatValue(float / EXCHANGE_COURSE_RUB_USD, R.string.converted_value_usd_cent)
            }

    private fun formatValue(convertedValue: Float, formatter: Int): String {
        val banknotes = Math.round(convertedValue - convertedValue % 1)
        val change = Math.round((convertedValue - banknotes) * 100)
        return String.format(Locale.getDefault(), getString(formatter), banknotes, change)
    }


    companion object {
        private const val EXCHANGE_COURSE_RUB_USD = 65f
        private const val LOG_TAG = "ConverterActivity"

        fun newInstance(context: Context) {
            val intent = Intent(context, ConverterActivity::class.java)
            context.startActivity(intent)
        }
    }
}