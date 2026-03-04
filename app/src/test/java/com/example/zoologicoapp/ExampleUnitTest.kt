package com.example.zoologicoapp

import com.example.zoologicoapp.model.AnimalEntity
import org.junit.Test
import org.junit.Assert.*

class AnimalUnitTest {

    @Test
    fun test_creacion_animal_es_correcta() {
        // Creamos un animal de prueba
        val animalPrueba = AnimalEntity(
            id = 1,
            nombre = "Puma",
            especie = "Puma concolor",
            habitat = "Montaña",
            dieta = "Carnívoro",
            imagen = "url_falsa"
        )

        // Verificamos (Assert) que los datos se guardaron bien en el objeto
        assertEquals("Puma", animalPrueba.nombre)
        assertEquals("Montaña", animalPrueba.habitat)
        assertEquals(1, animalPrueba.id)
    }
}