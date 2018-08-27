package com.example.tau.lab

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
/**
 * при смене города и обновлении адаптера ProductsPageAdapter когда пользователь находится в ProductsFragment 100% воспроизводится краш
 * https://stackoverflow.com/questions/31759171/recyclerview-and-java-lang-indexoutofboundsexception-inconsistency-detected-in
 */
class CustomLinearLayoutManager(context: Context) : LinearLayoutManager(context) {

    private val logTag = "LinearLayoutManager"

    override fun onLayoutChildren(recycler: RecyclerView.Recycler?, state: RecyclerView.State) {
        try {
            super.onLayoutChildren(recycler, state)
        } catch (e: IndexOutOfBoundsException) {
            Log.w(logTag, "meet a IOOBE in RecyclerView")
        }
    }
}
