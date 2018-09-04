package com.example.tau.lab.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Animal(
        var animalName: String?,
        var animalPicture: String?,
        var animalcomment: String?
): Parcelable{
    companion object{
        fun getMockBirds (): ArrayList<Animal> {
            return arrayListOf(
                    Animal("Попугай","https://s1.1zoom.ru/b5050/333/267878-werecat_1024x600.jpg",""),
                    Animal("Сова", "http://eventprime.ru/uploads/images/00/84/11/2012/09/19/a9e77d.jpg",""),
                    Animal("Ворона", "http://ladyhow.ru/wp-content/uploads/4-28-3.jpg",""),
                    Animal("Синица", "http://s00.yaplakal.com/pics/pics_original/0/9/1/1165190.jpg","")
            )
        }

        fun getMockMammals(): ArrayList<Animal> {
            return arrayListOf(
                    Animal("Кенгуру", "https://b1.filmpro.ru/c/124643.jpg",""),
                    Animal("Ёж", "http://nibler.ru/uploads/users/2013-07-09/fotopodborka-zabavnaya-krasivye-fotografii-neobychnye-fotografii_699180234.jpg",""),
                    Animal("Слон", "https://mtdata.ru/u23/photo5E37/20632067406-0/original.jpg",""),
                    Animal("Сурикат", "http://100mir.ru/d/surikat.jpg",""),
                    Animal("Енот", "https://mardentro.ru/wp-content/uploads/2016/08/dieta.jpg","")
            )
        }
    }
}