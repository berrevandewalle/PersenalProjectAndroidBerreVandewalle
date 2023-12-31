package com.example.app.data

import com.example.app.data.database.BikeDao
import com.example.app.data.database.BikeDb
import com.example.app.data.database.dbBike
import com.example.app.model.Bike
import kotlin.random.Random

object BikeSampler {
    private val sampleBikes = listOf(Bike(1, "Cervelo", 100.0, "https://www.lease-a-bike.be/cdn-cgi/image/format=auto,quality=70,width=750/storage/uploads/cervelo-s5-black.png"),
        Bike(2, "S-works", 200.0, "https://www.bikeshop.nl/sites/default/files/tarmac-sl7-sw-etap-carb-cmlnsilgrn_hero.jpg"),
        Bike(3, "Trek", 300.0, "https://www.lease-a-bike.nl/cdn-cgi/image/format=auto,quality=70,width=750/storage/uploads/97ea3f31-3681-4edb-b7b8-f05f0ae2aaec/modane-sl6.jpg"))


    val getAll: () -> MutableList<Bike> = {
        val list = mutableListOf<Bike>()
        for (bike in sampleBikes) {
            list.add(bike)
        }
        list
    }



}