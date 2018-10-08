package com.example.tau.lab.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Toast
import com.example.tau.lab.R
import com.example.tau.lab.util.Constants
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.android.schedulers.AndroidSchedulers
import java.util.*
import java.util.concurrent.TimeUnit

class ConverterFragment : Fragment(){
    private var amountToConvert: EditText? = null
    private var currency: RadioGroup? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_converter, container, false)
        amountToConvert = root.findViewById(R.id.amountToConvert)
        currency = root.findViewById(R.id.currency)
        initUi()
        return root
    }

    fun convert() {
        if (TextUtils.isEmpty(amountToConvert?.text)) {
            Toast.makeText(context, R.string.enter_convertible_unit, Toast.LENGTH_LONG).show()
        } else {
            if (context != null)
                AlertDialog.Builder(context!!).setMessage(getConvertedValue(amountToConvert?.text.toString().toFloat()))
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

    private fun initUi(){

        if (amountToConvert != null) RxTextView.textChanges(amountToConvert!!)
                .debounce(Constants.CLICKS_DEBOUNCE_RATE_MS, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .map(CharSequence::toString)
                .subscribe({ value -> run {
                    if (value.length > 8) {
                        Toast.makeText(context, R.string.number_of_characters_entered, Toast.LENGTH_LONG).show()
                        amountToConvert?.setText(value.substring(0, 7))
                    }
                }})
                { throwable -> Log.e(ConverterFragment.FRAGMENT_TAG,"error in comment subscription: " + throwable.localizedMessage) }
    }


    companion object {
        const val FRAGMENT_TAG = "ConverterFragment"
        private const val EXCHANGE_COURSE_RUB_USD = 65f

        fun newInstance(): ConverterFragment {
            val fragment = ConverterFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }
}
