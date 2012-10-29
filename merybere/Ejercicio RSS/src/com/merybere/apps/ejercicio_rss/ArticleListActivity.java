package com.merybere.apps.ejercicio_rss;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class ArticleListActivity extends ListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		
		// Layout de la activity
		setContentView(R.layout.article_list);
		
		// Creación de una colección de artículos ficticia con un ArrayList de HashMap
	    ArrayList<String[]> article_list = new ArrayList<String[]>();
	   
	    // Definición del contenido de la lista de artículos
	    
	    // Correspondencia del HashMap con los ids de los objetos que se muestran:
	    // - elementos del Array de HashMaps de eventos (from)
	    // - elementos del diseño en XML de cada una de las filas (to)
	    String[] from = new String[] {"Title", "Date"};
	    int[] to = new int[]{R.id.article_row_title, R.id.article_row_date};
		
	    String[] article1 = {"Primer artículo de prueba","10/10/2012"};
	    article_list.add(article1);
	    
	    String[] article2 = {"Olga Román publica nuevo disco","05/10/2012"};
	    article_list.add(article2);
	  
	    String[] article3 = {"Teatro Che y Moche vuelve con Oua Umplute","02/10/2012"};
	    article_list.add(article3);
	    
	    // Transformamos los elementos String[] en HashMap para incluirlos en el array
	    // que se utilizará para rellenar la lista
	    ArrayList<HashMap<String, String>> articles = new ArrayList<HashMap<String, String>>();
	    for(String[] art:article_list){
	        HashMap<String,String> article_data = new HashMap<String, String>();
	 
	        // Aquí es dónde utilizamos las referencias creadas inicialmente
	        //en el elemento "from"
	        article_data.put("Title", art[0]);
	        article_data.put("Date", art[1]);

	        articles.add(article_data);
	    }
	    
	    // Una vez tenemos toda la información necesaria para rellenar la lista
	    // creamos un elemento que nos facilitará la tarea:
	    // SimpleAdapter(Actividad, Array de HashMap con elementos, Fichero XML del diseño de fila
	    //               Cadenas del HashMap, Ids del Fichero XML del diseño de cada fila)
	    SimpleAdapter listAdapter = new SimpleAdapter(this, articles, R.layout.article_row, from, to);
	    setListAdapter(listAdapter);
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		
		// Intent para cargar la activity del detalle de artículo
		Intent intent = new Intent(this, ArticleDetailActivity.class);
		
		intent.putExtra("title", ((TextView) v.findViewById(R.id.article_row_title)).getText());
		
		startActivity(intent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.article_list, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Acciones para los ítems del menú
		switch (item.getItemId()) {
			case R.id.menu_about:
				Context context = ArticleListActivity.this;
				// Mensajero (se crea el mensaje que se va a pasar)
				Intent intent = new Intent(context, AboutActivity.class);
				
				startActivity(intent);
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}
	
}
