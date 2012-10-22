PASO 2 - activity básicos
========================

Objetivo
--------

Crear los Activity iniciales para los layouts que tenemos creados.

Pasos
-----

- Crea una nueva activity llamada `SplashActivity.xml`
	- Haz que esta Activity cargue el layout `splash.xml`
	- Defínela en el `AndroidManifest.xml` para que sea la primera que se lanza al pulsar el icono de la aplicación.
	
- Crea una nueva activity llamada `ArticleListActivity`
	- De momento está activity debe mostrar un layout vacio
	- Acuerdate de incluirla (sin filtros) en el `AndroidManifest.xml`

- Crea una nueva activity llamada `AboutActivity.xml`
	- Esta activity debe mostrar el layout `about.xml`
	- Acuerdate de incluirla (sin filtros) en el `AndroidManifest.xml`

- Define una zona clickable en `SplashActivity` de tal forma que cuando el usuario pulse en ella salte a `ArticleListActivity`

- Modifica el menú de `ArticleListActivity` para que tenga una opción con el texto "Acerca de..." cuando se pulse se deberá mostrar `AboutActivity`

Para nota
---------

- Programa un timer `SplashActivity` para que si el usuario no ha pulsado en 30segundos salte a la siguiente activity de forma automática
- Define y aplica un tema específico para `AboutActivity` de forma que se muestre como ventana flotante
