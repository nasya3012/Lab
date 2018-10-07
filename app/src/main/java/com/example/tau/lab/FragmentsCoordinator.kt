package com.example.tau.lab

import android.content.Context
import com.example.tau.lab.model.Animal

interface FragmentsCoordinator {
    fun showButtons(context: Context)
    fun showList(animalType: AnimalType)
    fun showAnimal(animal: Animal)
}