# IMDUMB - Technical Challenge 🎬

[![Platform](https://img.shields.io/badge/Platform-Android-green.svg)](https://developer.android.com)
[![Kotlin](https://img.shields.io/badge/Kotlin-2.0.21-purple.svg)](https://kotlinlang.org)
[![Hilt](https://img.shields.io/badge/Hilt-2.55-blue.svg)](https://dagger.dev/hilt/)

## 📝 Resumen del Proyecto
**IMDUMB** es una robusta aplicación Android desarrollada para el reto técnico de Mobile Developer. La aplicación consume la API de **TheMovieDB** para presentar un catálogo organizado por categorías y detalles profundos de películas.

El enfoque principal de este desarrollo ha sido la implementación de una arquitectura escalable, el uso de programación reactiva y la integración de servicios en la nube para configuraciones dinámicas.

---

## 🏗️ Arquitectura y Patrones
Se ha adoptado el patrón **MVP (Model-View-Presenter)** en combinación con los principios de **Clean Architecture**, asegurando una separación clara de responsabilidades y facilidad para la realización de pruebas unitarias.

### Capas del Proyecto:
1.  **Domain**: Contiene la lógica de negocio pura (Entidades, Casos de Uso e Interfaces de Repositorio). No tiene dependencias de librerías externas de Android.
2.  **Data**: Responsable de la obtención de datos. Implementa las interfaces de la capa de dominio.
    -   *Remote*: Implementación de Retrofit para APIs REST.
    -   *Local*: Persistencia simple con SharedPreferences para configuraciones rápidas.
    -   *Mappers*: Clases encargadas de transformar modelos de datos (DTOs) a modelos de dominio.
3.  **Presentation**: Implementación de la UI siguiendo el patrón MVP.
    -   *Presenters*: Orquestan el flujo de datos usando RxJava.
    -   *Views*: Activities y Fragments que implementan contratos de vista.

---

## 🛠️ Tech Stack y Dependencias
-   **Lenguaje**: Kotlin `2.0.21` (Moderno y Conciso).
-   **Inyección de Dependencias**: Hilt `2.55` (Inversión de Control).
-   **Networking**: Retrofit `2.11.0` + Gson (Consumo de servicios REST).
-   **Programación Reactiva**: RxJava 2 + RxKotlin (Manejo de flujos de datos asíncronos).
-   **Imagen**: Glide `4.16.0` (Carga optimizada de imágenes y perfiles).
-   **Firebase**:
    -   Remote Config: Manejo de Splash, Temas dinámicos y Feature Toggles.
-   **UI Components**:
    -   ConstraintLayout (Diseños planos y eficientes).
    -   Nested RecyclerViews (Listado de categorías con scroll interno).
    -   ViewPager2 (Carrusel de imágenes en detalle).
    -   BottomSheetDialogFragment (Interacción enriquecida de usuario).

---

## 🚀 Cómo ejecutar el proyecto

### 1. Clonar el repositorio
Abre una terminal y ejecuta el siguiente comando:
```bash
git clone https://github.com/rotarolasanchez/imdumb-app.git
```

### 2. Abrir en Android Studio
- Abre Android Studio (versión **Ladybug 2024.2.1** o superior recomendada).
- Selecciona **File > Open** y busca la carpeta del proyecto clonado.

### 3. Configuración y Sync
- El proyecto ya incluye las claves necesarias (`TMDB_API_KEY`, etc.) dentro del archivo `gradle.properties` para garantizar una **compilación inmediata**.
- Espera a que Android Studio finalice el **Gradle Sync**. Si no inicia automáticamente, presiona el icono de elefante en la barra de herramientas.

### 4. Selección de Variante y Ejecución
- Abre la pestaña **Build Variants** (ubicada en el lateral izquierdo inferior).
- Selecciona la variante que prefieras:
  - `devDebug`: Entorno de desarrollo (Recomendado para pruebas).
  - `prodRelease`: Versión final de producción.
- Conecta un dispositivo real o inicia un emulador (API 24+).
- Haz clic en **Run 'app'** (icono de play verde).

---

## 🌐 Configuración de Firebase
La aplicación se comunica con Firebase para cargar configuraciones en tiempo real:
-   **Feature Toggle**: `enable_recommendation` habilita/deshabilita el botón en el detalle.
-   **Dynamic Theme**: `app_theme` permite cambiar entre `light` y `dark` desde la consola.
-   **Personalización**: `welcome_text` y `home_title` permiten cambiar textos de la app sin actualizar el código.

---

## ✨ Principios SOLID Aplicados
-   **SRP**: Cada Mapper y Caso de Uso tiene una responsabilidad única y atómica.
-   **OCP**: La lógica de negocio está abierta a extensión mediante nuevos casos de uso pero cerrada a modificación.
-   **LSP**: Los repositorios se inyectan mediante interfaces, permitiendo sustituir la fuente de datos (Mock vs Real) sin afectar al sistema.
-   **ISP**: Los contratos MVP (`Contract.kt`) definen interfaces específicas para cada vista, evitando métodos innecesarios.
-   **DIP**: Se depende de abstracciones; Hilt maneja la creación y ciclo de vida de los objetos.

---

## 📸 Capturas de Pantalla

| Splash Screen | Home (Categorías) | Detalle de Película | Recomendación |
|:---:|:---:|:---:|:---:|
| <img src="screenshots/Splash.png" width="200" /> | <img src="screenshots/Home.png" width="200" /> | <img src="screenshots/Detail.png" width="200" /> | <img src="screenshots/BottomSheet.png" width="200" /> |

---

## 👨‍💻 Autor
**Ronald Eduardo Otarola Sanchez**
- GitHub: [@rotarolasanchez](https://github.com/rotarolasanchez)
