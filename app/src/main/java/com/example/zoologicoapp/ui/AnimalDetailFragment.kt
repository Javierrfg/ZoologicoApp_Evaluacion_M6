package com.example.zoologicoapp.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import coil.load
import com.example.zoologicoapp.R

class AnimalDetailFragment : Fragment() {

    private val viewModel: ZooViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_animal_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val animalId = arguments?.getInt("animalId") ?: return

        // Referencias a los elementos básicos
        val imgAnimal = view.findViewById<ImageView>(R.id.imgAnimalDetalle)
        val tvNombre = view.findViewById<TextView>(R.id.tvNombreDetalle)
        val tvEstado = view.findViewById<TextView>(R.id.tvEstadoConservacion)
        val tvDesc = view.findViewById<TextView>(R.id.tvDescripcion)
        val btnCorreo = view.findViewById<Button>(R.id.btnCorreo)

        // Nuevas referencias para los detalles extra
        val tvEsperanza = view.findViewById<TextView>(R.id.tvEsperanzaVida)
        val tvDimensiones = view.findViewById<TextView>(R.id.tvDimensiones)
        val tvDatosCuriosos = view.findViewById<TextView>(R.id.tvDatosCuriosos)
        val tvComidas = view.findViewById<TextView>(R.id.tvComidas)
        val tvPredadores = view.findViewById<TextView>(R.id.tvPredadores)

        viewModel.getAnimalDetalle(animalId).observe(viewLifecycleOwner) { animal ->
            animal?.let {
                // Textos principales
                tvNombre.text = it.nombre
                tvEstado.text = it.estadoDeConservacion?.uppercase() ?: "ESTADO DESCONOCIDO"
                tvDesc.text = it.descripcion ?: "Descripción no disponible."

                // Detalles extra
                tvEsperanza.text = "⏳ Esperanza de vida: ${it.esperanzaDeVida ?: "N/A"}"
                tvDimensiones.text = "⚖️ Peso: ${it.pesoPromedio ?: "N/A"} | 📏 Altura: ${it.alturaPromedio ?: "N/A"}"

                // Formateamos las listas para que aparezcan con viñetas
                tvDatosCuriosos.text = it.datosCuriosos?.joinToString(separator = "\n\n") { dato -> "• $dato" } ?: "Sin datos curiosos"
                tvComidas.text = it.comidasFavoritas?.joinToString(separator = "\n") { comida -> "• $comida" } ?: "Dieta no especificada"
                tvPredadores.text = it.predadoresNaturales?.joinToString(separator = "\n") { predador -> "• $predador" } ?: "Sin predadores registrados"

                imgAnimal.load(it.imagen) {
                    crossfade(true)
                }

                btnCorreo.setOnClickListener { _ ->
                    enviarCorreo(it.nombre)
                }
            }
        }
    }

    private fun enviarCorreo(nombreAnimal: String) {
        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:")
            putExtra(Intent.EXTRA_EMAIL, arrayOf("info@tuzoologico.com"))
            putExtra(Intent.EXTRA_SUBJECT, "Información sobre: $nombreAnimal")

            val mensaje = "Solicito más información sobre el o los Zoológicos dentro de Chile que tienen un $nombreAnimal. Me gustaría realizar una reserva para visitarlo junto a mi familia."
            putExtra(Intent.EXTRA_TEXT, mensaje)
        }
        startActivity(intent)
    }
}