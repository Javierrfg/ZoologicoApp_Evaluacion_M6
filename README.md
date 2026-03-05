# Zoológico App

Aplicación Android desarrollada para consultar información detallada sobre los animales que habitan en zoológicos. Este proyecto tiene un enfoque educativo, permitiendo a los usuarios aprender sobre la biodiversidad, características, hábitos y el estado de conservación de diversas especies.

## Características Principales

* **Listado de Animales:** Visualización rápida de las especies disponibles consumiendo una API REST.
* **Detalle Completo:** Pantalla de información extendida que incluye esperanza de vida, dimensiones, datos curiosos, dieta y predadores naturales.
* **Modo Offline:** Gracias a la persistencia de datos local, la aplicación guarda la información descargada para poder ser consultada en cualquier momento sin necesidad de internet.
* **Contacto Directo:** Botón integrado con Intent implícito para abrir la aplicación de correo del dispositivo y solicitar información o reservas prellenando los datos del animal seleccionado.

## 🛠️ Tecnologías y Arquitectura

Este proyecto fue construido utilizando los estándares modernos de desarrollo en Android:

* **Lenguaje:** Kotlin
* **Arquitectura:** MVVM (Model-View-ViewModel) para una separación clara de responsabilidades.
* **Persistencia Local:** Room (SQLite) implementado mediante KSP.
* **Llamadas de Red:** Retrofit2 y Gson para el consumo de la API REST.
* **Asincronía:** Kotlin Coroutines (y `viewModelScope`) para el manejo de tareas en segundo plano sin bloquear la interfaz de usuario.
* **Navegación:** Navigation Component para el enrutamiento entre fragmentos.
* **Carga de Imágenes:** Coil para una visualización rápida y eficiente.
* **Diseño:** Interfaz construida con XML utilizando Material Design (MaterialCardView, MaterialButton) respetando una paleta de colores cohesiva.
