PASO 6 - Fragments
==================

Objetivo
--------

Adaptar las Activity de nuestra App para que usen fragments

Pasos
-----

- Crea un fragment para el listado de artículos (ArticleListFragment)
- Crea otro fragment para el detalle de artículo (ArticleDetailFragment)
- Crea el interface sobre el que se comunicarán los fragments entre si indicará el método onArticleSelected(...)
- Adapta la activity principal (ArticleListActivity) para que si está en modo apaisado muestre el fragment de listado a la izquierda y el detalle a la derecha. Si está en vertical sólo se mostrará el listado.
- Implementa el interface de comunicación:
	- si está en horizontal el fragment de la derecha se actualizará con los datos del artículo seleccionado
	- si está en vertical se mostrará la activity de detalle (que usará ArticleDetailFragment)
- Intenta mantener la compatibilidad con versiones Android 2.x
- No te olvides de convertir las Activity en FragmentActivity

Para nota
---------

- Intenta incluir un ActionBar con compatibilidad (usa SherlockActionBar o implementa el tuyo)
- Trata de guardar los estados (artículos seleccionados y cargados) por si la activity se destruye y vuelve a crearse, por ejemplo con un cambio de orientación de pantalla. De tal forma que se vuelva a crear con los valores que tenía.