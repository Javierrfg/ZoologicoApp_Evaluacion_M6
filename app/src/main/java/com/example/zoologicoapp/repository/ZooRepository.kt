package com.example.zoologicoapp.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.zoologicoapp.local.AnimalDao
import com.example.zoologicoapp.model.AnimalEntity
import com.example.zoologicoapp.network.ZooApi

class ZooRepository(private val zooApi: ZooApi, private val animalDao: AnimalDao) {

    // Devuelve la lista completa de animales desde la base de datos local
    fun getAllAnimales(): LiveData<List<AnimalEntity>> = animalDao.getAllAnimales()

    // Devuelve un solo animal específico desde la base de datos local
    fun getAnimalById(id: Int): LiveData<AnimalEntity> = animalDao.getAnimalById(id)

    // Va a internet, descarga la lista y la guarda en la base de datos
    suspend fun fetchAnimales() {
        try {
            val response = zooApi.getAnimales()
            if (response.isSuccessful) {
                response.body()?.let { lista ->
                    animalDao.insertAllAnimales(lista)
                }
            } else {
                Log.e("ZooRepository", "Error en la respuesta: ${response.errorBody()}")
            }
        } catch (e: Exception) {
            Log.e("ZooRepository", "Error de conexión: ${e.message}")
        }
    }

    // Va a internet, descarga el detalle de UN animal y lo actualiza en la base de datos
    suspend fun fetchAnimalDetalle(id: Int) {
        try {
            val response = zooApi.getAnimalDetalle(id)
            if (response.isSuccessful) {
                response.body()?.let { animalDetalle ->
                    animalDao.insertAnimal(animalDetalle)
                }
            } else {
                Log.e("ZooRepository", "Error en la respuesta del detalle")
            }
        } catch (e: Exception) {
            Log.e("ZooRepository", "Error de conexión en detalle: ${e.message}")
        }
    }
}