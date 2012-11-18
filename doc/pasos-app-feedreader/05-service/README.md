PASO 5 - Service
================

Objetivo
--------

Crear un Service que descargue los datos del feed elegido y los almacene en nuestra base de datos

Pasos
-----

- Descargar y añadir a nuestro proyecto la librería de parseo de RSS (https://github.com/matshofman/Android-RSS-Reader-Library)

- Crear una clase llamada SyncService que extienda de IntentService y se encargue de rellenar la BBDD con los artículos descargados (se deberá evitar que el artículo aparezca duplicado)

- Añadir el servicio al AndroidManifest con un filtro de intent para que pueda ser llamado de forma sencilla

- Invocar al servicio cada vez que se arranque la app (desde la pantalla de splash)

- Añadir una opción al menú que permita forzar la recarga

Para nota
---------

- Mostrar una barra de progreso mientras se estan descargando y parseando los artículos

- Implementar un mecanismo de control que evite que los artículos se descarguen continuamente y hayan pasado por lo menos 5 minutos desde la última descarga

- Mostar una notificación en la barra de estado del sistema indicando el número de artículos descargados