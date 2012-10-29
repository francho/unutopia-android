PASO 4 - base de datos
======================

Objetivo
--------

Crear la base de datos donde almacenaremos el RSS descargado

Pasos
-----

- Crea el `RssContract` que describa la estructura de la base de datos. De momento crea sólo la tabla de `Feed` con los siguientes campos:
		+ title
		+ link
		+ pubDate
		+ description
		+ content

- Crea el `RssDbHelper` que extienda de `SQLiteOpenHelper` y que se encargue de crear la BBDD.
	- Añadele un método que la rellene con datos ficticios

- Modifica `ArticleListActivity` para que use un `SimpleCursorAdapter` y muestre el título y la fecha de los artículos almacenados en la BD.

