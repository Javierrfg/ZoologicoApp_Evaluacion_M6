package com.example.zoologicoapp.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.zoologicoapp.model.AnimalEntity

@Dao
interface AnimalDao {
    // Guarda la lista de animales. Si ya existen, los reemplaza (REPLACE).
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllAnimales(animales: List<AnimalEntity>)

    // Guarda el detalle de un solo animal
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAnimal(animal: AnimalEntity)

    // Obtiene todos los animales para la pantalla principal
    @Query("SELECT * FROM animal_table ORDER BY id ASC")
    fun getAllAnimales(): LiveData<List<AnimalEntity>>

    // Obtiene los datos de un animal específico usando su ID
    @Query("SELECT * FROM animal_table WHERE id = :id")
    fun getAnimalById(id: Int): LiveData<AnimalEntity>
}