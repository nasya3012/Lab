package com.example.tau.lab.util

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.example.tau.lab.R
import com.example.tau.lab.model.Animal

object ImageUtils {

    fun processAnimalImage(animal: Animal, imageView: ImageView, withCrossFade: Boolean){
        Glide.with(imageView.context)
                .load(animal.animalPicture)
                .apply(createOptions())
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(imageView)
    }

    private fun createOptions(): RequestOptions {
        return RequestOptions()
                .error(R.drawable.donno)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .priority(Priority.NORMAL)
    }
}
