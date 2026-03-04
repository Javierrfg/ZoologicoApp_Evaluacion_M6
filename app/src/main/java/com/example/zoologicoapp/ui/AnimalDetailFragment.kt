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

    // Compartimos el mismo ViewModel de la lista
    private val viewModel: ZooViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_animal_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Recibimos el ID del animal que el usuario tocó en la lista
        val animalId = arguments?.getInt("animalId") ?: return

        // Buscamos los elementos visuales en el diseño
        val imgAnimal = view.findViewById<ImageView>(R.id.imgAnimalDetalle)
        val tvNombre = view.findViewById<TextView>(R.id.tvNombreDetalle)
        val tvEstado = view.findViewById<TextView>(R.id.tvEstadoConservacion)
        val tvDesc = view.findViewById<TextView>(R.id.tvDescripcion)
        val btnCorreo = view.findViewById<Button>(R.id.btnCorreo)

        // Le pedimos al ViewModel los detalles de ESTE animal específico
        viewModel.getAnimalDetalle(animalId).observe(viewLifecycleOwner) { animal ->
            animal?.let {
                // Llenamos los textos en la pantalla
                tvNombre.text = it.nombre
                tvEstado.text = it.estadoDeConservacion ?: "Consultando estado..."
                tvDesc.text = it.descripcion ?: "Descargando descripción..."

                // Cargamos la imagen con Coil
                imgAnimal.load(it.imagen) {
                    crossfade(true)
                }

                // Configuramos el botón del correo (Intent Implícito)
                btnCorreo.setOnClickListener { _ ->
                    enviarCorreo(it.nombre)
                }
            }
        }
    }

    // Función que abre la app de correos del teléfono con los datos prellenados
    private fun enviarCorreo(nombreAnimal: String) {
        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:") // Solo aplicaciones de correo responderán a esto
            putExtra(Intent.EXTRA_EMAIL, arrayOf("info@tuzoologico.com")) // Destinatario requerido [cite: 116]
            putExtra(Intent.EXTRA_SUBJECT, "Información sobre: $nombreAnimal") // Asunto requerido [cite: 117]

            // Cuerpo del mensaje exacto que pide el requerimiento [cite: 118, 119]
            val mensaje = "Solicito más información sobre el o los Zoológicos dentro de Chile que tienen un $nombreAnimal. Me gustaría realizar una reserva para visitarlo junto a mi familia."
            putExtra(Intent.EXTRA_TEXT, mensaje)
        }
        startActivity(intent)
    }
}