package com.example.tau.lab.fragments

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.app.FragmentManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.EditText
import com.example.tau.lab.R
import com.example.tau.lab.model.Animal
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit

class CommentDialogFragment : DialogFragment() {

    private var comment: EditText? = null
    private var animal: Animal? = null
    private var listener: Listener? = null
    private val commentAnimal = animal?.animalcomment

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.window?.requestFeature(Window.FEATURE_NO_TITLE)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setCanceledOnTouchOutside(false)

//        не дает воспользоваться системной кнопкой назад(false), по умолчанию true
        isCancelable = true

        return dialog
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = when {
            parentFragment is Listener -> parentFragment as Listener
            context is CommentDialogFragment.Listener -> context
            else -> throw RuntimeException(context.toString() + " or " + parentFragment.toString() + " must implement Listener")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {animal = it.getParcelable(ANIMAL)}
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.dialog_comment, container, false)

        comment = root.findViewById(R.id.comment)
        comment?.setText(commentAnimal)
        RxTextView.textChanges(comment!!)
                .debounce(CLICKS_DEBOUNCE_RATE_MS, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .map(CharSequence::toString)
                .subscribe({ value -> saveComment() })
                { throwable -> Log.e(LOG_TAG, "error in comment subscription: " + throwable.localizedMessage) }

//        dismiss возврат, применим только к диалогам
//        root.findViewById<View>(R.id.buttonNo).setOnClickListener{dismiss()}

        return root
    }

    private fun saveComment() {
        val commentOld = commentAnimal
        animal?.animalcomment = comment?.text.toString()
        Log.v(LOG_TAG, "животное=" + commentAnimal + ", комментарий: было=" + commentOld
                + ", стало=" + commentAnimal)
        listener?.updateContent()
    }

    override fun onResume() {
        super.onResume()
        if (dialog.window == null) return
        val params = dialog.window!!.attributes
//        params.width = ViewGroup.LayoutParams.MATCH_PARENT
//        params.height = ViewGroup.LayoutParams.MATCH_PARENT
        dialog.window!!.attributes = params as android.view.WindowManager.LayoutParams
    }


    interface Listener {
        fun updateContent()
    }


    companion object {
        const val FRAGMENT_TAG = "com.example.tau.lab.fragments.CommentDialogFragment"
        private const val LOG_TAG = "CommentDialogFragment"
        private const val ANIMAL = "animal"
        private const val CLICKS_DEBOUNCE_RATE_MS = 300L

        fun showDialog(fragmentManager: FragmentManager, animal: Animal, listener: Listener) {
            val transaction = fragmentManager.beginTransaction()
            val dialog = fragmentManager.findFragmentByTag(LOG_TAG)
            if (dialog != null) transaction.remove(dialog)
            transaction.addToBackStack(null)
            val newFragment = newInstance(animal)
            newFragment.show(transaction, LOG_TAG)
        }

        private fun newInstance(animal: Animal): DialogFragment {
            val fragment = CommentDialogFragment()
            val args = Bundle()
            args.putParcelable(ANIMAL, animal)
            fragment.arguments = args
            return fragment
        }
    }
}
