package com.example.tau.lab

import android.app.Application
import com.example.tau.lab.model.Animal

class LabApp: Application() {

    companion object {
        val mammals = Animal.getMockMammals()
        val birds = Animal.getMockBirds()
    }
}