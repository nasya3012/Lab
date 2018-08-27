package com.example.tau.lab

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.tau.lab.model.Animal
import java.util.ArrayList

class Adapter(
        private var animals: ArrayList<Animal>?,
        private val listener: Listener?
) : RecyclerView.Adapter<Adapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
            ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item_animal, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val animal = animals!![position]
        holder.root.setOnClickListener { listener?.animalClicked(animal) }
        if (holder.image != null) Glide.with(holder.context).load(animal.animalPicture).into(holder.image)
        holder.text?.text = animal.animalName
    }

    override fun getItemCount(): Int = animals?.size?: 0

    interface Listener {
        fun animalClicked(animal: Animal)
    }

    class ViewHolder(val root: View) : RecyclerView.ViewHolder(root) {
        val context: Context
            get() = root.context
        val image: ImageView? = root.findViewById(R.id.image)
        val text: TextView? = root.findViewById(R.id.text)
    }
}
