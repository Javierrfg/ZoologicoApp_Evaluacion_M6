package com.example.zoologicoapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.zoologicoapp.R

class AnimalListFragment : Fragment() {

    // Compartimos el mismo ViewModel de la aplicación
    private val viewModel: ZooViewModel by activityViewModels()
    private lateinit var adapter: AnimalAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_animal_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)

        // Inicializamos el Adaptador y definimos qué pasa al hacer clic en un animal
        adapter = AnimalAdapter { animalSeleccionado ->

            // Creamos un paquete (Bundle) para enviar el ID del animal
            val bundle = Bundle().apply {
                putInt("animalId", animalSeleccionado.id)
            }

            // Usamos la función de navegación ahora que ya está importada
            findNavController().navigate(R.id.action_animalListFragment_to_animalDetailFragment, bundle)
        }

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter

        // Observamos los datos y actualizamos la lista
        viewModel.animalesList.observe(viewLifecycleOwner) { lista ->
            lista?.let {
                adapter.updateData(it)
            }
        }
    }
}