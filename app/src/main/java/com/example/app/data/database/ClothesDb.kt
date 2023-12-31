package com.example.app.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [dbClothes::class], version = 2, exportSchema = false)
abstract class ClothesDb : RoomDatabase() {

    abstract fun clothesDao(): ClothesDao

    companion object {
        @Volatile
        private var Instance: ClothesDb? = null

        fun getDatabase(context: Context): ClothesDb {
            // if the Instance is not null, return it, otherwise create a new database instance.
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, ClothesDb::class.java, "clothes_database")
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it }
            }
        }
    }
}