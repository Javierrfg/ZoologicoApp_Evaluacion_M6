package com.example.zoologicoapp.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.zoologicoapp.R
import com.example.zoologicoapp.model.AnimalEntity

// El adaptador recibe una función (onClick) para saber qué hacer cuando el usuario toque un animal
class AnimalAdapter(private val onClick: (AnimalEntity) -> Unit) :
    RecyclerView.Adapter<AnimalAdapter.AnimalViewHolder>() {

    // Lista vacía al inicio
    private var animales = listOf<AnimalEntity>()

    // Función para actualizar la data y refrescar la pantalla
    fun updateData(nuevaLista: List<AnimalEntity>) {
        animales = nuevaLista
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimalViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_animal, parent, false)
        return AnimalViewHolder(view)
    }

    override fun onBindViewHolder(holder: AnimalViewHolder, position: Int) {
        val animal = animales[position]
        holder.bind(animal)
    }

    override fun getItemCount(): Int = animales.size

    // El ViewHolder es el que enlaza las vistas del XML con el código
    inner class AnimalViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imgAnimal: ImageView = itemView.findViewById(R.id.imgAnimal)
        private val tvNombre: TextView = itemView.findViewById(R.id.tvNombre)
        private val tvEspecie: TextView = itemView.findViewById(R.id.tvEspecie)

        fun bind(animal: AnimalEntity) {
            tvNombre.text = animal.nombre
            tvEspecie.text = animal.especie

            // Usamos Coil para cargar la imagen desde la URL
            imgAnimal.load(animal.imagen) {
                crossfade(true)
                placeholder(android.R.drawable.ic_menu_report_image) // Imagen temporal mientras carga
            }

            // Configuramos el clic en la tarjeta
            itemView.setOnClickListener {
                onClick(animal)
            }
        }
    }
}