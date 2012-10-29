package com.merybere.apps.ejercicio_rss.apps;
// Centralización de los intents o mensajes de la aplicación

import android.content.Intent;

public class AppIntent extends Intent {

	// Estos textos son los que se tienen que corresponder con los definidos en el manifiesto
	private static final String ACTION_ARTICLE_LIST = "unutopia.intent.action.ARTICLE_LIST";
	
	// Función estática que monta el intent
	// Llamada a la actividad de listado de artículos
	
	public static Intent getArticleListIntent() {
		return new Intent(ACTION_ARTICLE_LIST);
	}
}
