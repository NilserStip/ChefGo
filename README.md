Nombre de la Aplicación Android
# ChefGo

// Contexto

Aplicación Android de prueba para listar receta de comidas aplicando flavors, pruebas unitarias, corrutinas, firebase y buenas practicas de desarrollo.

// Important Notes
- Compilar el ambiente de producción
- Único Mockup disponible para pruebas "https://apimocha.com/chefgo/"
- Único EndPoint disponible para pruebas "recipes/list"

// Retrofit
- Consumo del servicio para obtener el listado de recetas con los siguientes Parámetros (nombre: decripción):
  id: Identificador
  logo: Imagen
  name: Nombre,
  description: Descripción,
  ingredients: Ingredientes,
  preparation: Preparación,
  people: Cantidad de personas,
  active: Visible o No Visible,
  location: Coordenadas

// Flavors
- dev: Compila un ambiente de desarrollo (BASE_URL simulado pero no funcional)
- uat: Compila un ambiente de pruebas (BASE_URL simulado pero no funcional)
- prd: Compila un ambiente de producción (BASE_URL simulado y funcional)

// Arquitectura (Clean Arquitecture)
- data: Maneja las clases que obtiene la información desde una api (puede manejar datos internos, por ejm. SharedPreference, SQLite, Paper, entre otros)
- domain: Maneja la lógica del negocio por medio de casos de uso (Interactua con la IU) 
- presentation: Maneja la vista o IU de la aplicación (Activity, Fragment, Dialog, entre otros)

// Patrones de Diseño (MVVM)
- ViewModel: Se encarga de escuchar los eventos de la IU y se comunica con la capa de dominio

// Pruebas Unitarias
- Mockito: Realicé pruebas unitarias con el caso de uso "GetRecipesUseCaseTest" pero tuve problemas para aplicarlas en la vista modelo "RecipeViewModelTest"

// Firebase
- Analytics: Registro de eventos personalizados
- Crashlytics: Reporte de errores en el consumo de servicio

// Use of the following libraries
- androidx.navigation: para la Navegación de fragmentos dentro de la aplicación
- kotlinx-coroutines-android: para Implementar corrutinas en el proyecto
- lifecycle-viewmodel-ktx: para Implementar el modelo de vista
- com.facebook.shimmer: Vista visual de carga para el listado de recetas
- androidx.swiperefreshlayout: Componente para actualizar la lista de recetas
- com.github.bumptech.glide: Muestra las imágenes en el componente ImageView
- play-services-maps: para Implementar la vista del mapa en el fragmento
- com.intuit.sdp: Define dimensiones para usar en los componentes y mantener el tamaño en diferentes medidas de pantallas 
- com.intuit.ssp: Define dimensiones para usar en los textos y mantener el tamaño en diferentes medidas de pantallas
- com.squareup.retrofit2: para el consumo de Servicios dentro de la aplicación
- com.squareup.okhttp3: Visualizar los logs en el IDE en tiempo real de los servicios consumidos
- com.facebook.stetho: Visualizar los logs en el Navegador en tiempo real de los servicios consumidos
- firebase-analytics-ktx: Implementación de Firebase Analytics para reportar eventos
- firebase-crashlytics-ktx: Implementación de Firebase Crashlytics para reporte de errores en la aplicación
- org.mockito: Implementación de pruebas unitarios con Mockito
- com.google.dagger: Implementación de Dagger Hilt para inyección de dependencias en el proyecto




