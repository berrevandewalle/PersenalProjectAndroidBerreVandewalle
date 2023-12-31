package com.example.app.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.app.data.BikeSampler

@Database(entities = [dbBike::class], version = 2, exportSchema = false)
abstract class BikeDb : RoomDatabase() {

    abstract fun bikeDao(): BikeDao

    companion object {
        @Volatile
        private var Instance: BikeDb? = null

        fun getDatabase(context: Context): BikeDb {
            // if the Instance is not null, return it, otherwise create a new database instance.
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, BikeDb::class.java, "bike_database")
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it }
            }
        }

        suspend fun buildDatabase(database: BikeDb) {
            val bikeDao = database.bikeDao()
            val bikes = BikeSampler.getAll()
            for (bike in bikes) {
                bikeDao.insert(dbBike(bike.id, bike.name, bike.price, bike.imgSrc))
            }
        }
    }
}