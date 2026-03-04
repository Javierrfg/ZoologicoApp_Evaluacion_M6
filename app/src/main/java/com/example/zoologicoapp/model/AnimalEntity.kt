package com.example.zoologicoapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "animal_table")
data class AnimalEntity(
    @PrimaryKey
    @SerializedName("id") val id: Int,
    @SerializedName("nombre") val nombre: String,
    @SerializedName("especie") val especie: String,
    @SerializedName("habitat") val habitat: String,
    @SerializedName("dieta") val dieta: String,
    @SerializedName("imagen") val imagen: String,

    // Datos adicionales del detalle (pueden ser nulos al principio)
    @SerializedName("descripcion") val descripcion: String? = null,
    @SerializedName("estadoDeConservacion") val estadoDeConservacion: String? = null,
    @SerializedName("esperanzaDeVida") val esperanzaDeVida: String? = null,
    @SerializedName("pesoPromedio") val pesoPromedio: String? = null,
    @SerializedName("alturaPromedio") val alturaPromedio: String? = null,

    // Las listas requieren un convertidor para guardarse en Room
    @SerializedName("datosCuriosos") val datosCuriosos: List<String>? = null,
    @SerializedName("comidasFavoritas") val comidasFavoritas: List<String>? = null,
    @SerializedName("predadoresNaturales") val predadoresNaturales: List<String>? = null
)
