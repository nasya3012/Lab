package com.example.tau.lab

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Animal(
        var animalName: String,
        var animalPicture: String
): Parcelable{
    companion object{
        fun getMock(): ArrayList<Animal> {
            return arrayListOf(
                    Animal("Кенгуру","http://tuzikgreet.ru/i/files/news/_nw/51/s17217258.jpg"),
                    Animal("Ёж", "http://nibler.ru/uploads/users/2013-07-09/fotopodborka-zabavnaya-krasivye-fotografii-neobychnye-fotografii_699180234.jpg"),
                    Animal("Слон", "https://mtdata.ru/u23/photo5E37/20632067406-0/original.jpg"),
                    Animal("Сурикат", "https://i.ytimg.com/vi/P0iAG_TqPNI/maxresdefault.jpg"),
                    Animal("Енот", "https://mardentro.ru/wp-content/uploads/2016/08/dieta.jpg")
            )
        }
    }
}