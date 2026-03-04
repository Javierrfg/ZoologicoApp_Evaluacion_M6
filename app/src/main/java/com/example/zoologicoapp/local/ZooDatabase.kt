package com.example.zoologicoapp.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.zoologicoapp.model.AnimalEntity
import com.example.zoologicoapp.model.Converters

// Indicamos qué tabla usamos (AnimalEntity) y la versión de la base de datos
@Database(entities = [AnimalEntity::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class) // Esto permite guardar las listas de datos curiosos
abstract class ZooDatabase : RoomDatabase() {

    abstract fun animalDao(): AnimalDao

    companion object {
        @Volatile
        private var INSTANCE: ZooDatabase? = null

        fun getDatabase(context: Context): ZooDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ZooDatabase::class.java,
                    "zoo_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}