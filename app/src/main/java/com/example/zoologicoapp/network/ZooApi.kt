package com.example.zoologicoapp.network

import com.example.zoologicoapp.model.AnimalEntity
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ZooApi {
    // Endpoint de Animales utilizando el método HTTP GET
    @GET("animales")
    suspend fun getAnimales(): Response<List<AnimalEntity>>

    // Endpoint del Detalle de un Animal [cite: 78, 79]
    @GET("animales/{id}")
    suspend fun getAnimalDetalle(@Path("id") id: Int): Response<AnimalEntity>
}