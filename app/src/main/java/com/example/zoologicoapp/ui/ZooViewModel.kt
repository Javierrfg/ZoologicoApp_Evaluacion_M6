package com.example.zoologicoapp.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.zoologicoapp.local.ZooDatabase
import com.example.zoologicoapp.model.AnimalEntity
import com.example.zoologicoapp.network.RetrofitClient
import com.example.zoologicoapp.repository.ZooRepository
import kotlinx.coroutines.launch

class ZooViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: ZooRepository

    // Esta es la variable que nuestra pantalla va a "mirar" para saber qué animales mostrar
    val animalesList: LiveData<List<AnimalEntity>>

    init {
        // Inicializamos las conexiones
        val dao = ZooDatabase.getDatabase(application).animalDao()
        val api = RetrofitClient.getRetrofit()
        repository = ZooRepository(api, dao)

        // Conectamos la lista a la base de datos
        animalesList = repository.getAllAnimales()

        // Apenas se abra la app, mandamos a descargar los datos en segundo plano
        cargarAnimalesDeInternet()
    }

    private fun cargarAnimalesDeInternet() {
        // viewModelScope nos permite usar Coroutines para no congelar la pantalla
        viewModelScope.launch {
            repository.fetchAnimales()
        }
    }

    // Esta función la usaremos cuando el usuario toque un animal para ver sus detalles
    fun getAnimalDetalle(id: Int): LiveData<AnimalEntity> {
        viewModelScope.launch {
            repository.fetchAnimalDetalle(id) // Descargamos la info extra (peso, altura, etc.)
        }
        return repository.getAnimalById(id) // Devolvemos lo que se guardó
    }
}