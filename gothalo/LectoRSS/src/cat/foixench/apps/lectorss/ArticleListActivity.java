package cat.foixench.apps.lectorss;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cat.foixench.apps.lectorss.utils.LectoRSSInterface;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class ArticleListActivity extends ListActivity implements LectoRSSInterface{

	/**
	 *  Crea la activity, cargando el layout asociado articlelist.xml
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// indicamos el layout articlelist que usa esta activity
		setContentView(R.layout.articlelist);

		
		SimpleAdapter adapter = this.getAdapter ();
		// asociamos el adapter a la list view.
		setListAdapter(adapter);
		
	}
		
	
	
	
	/**
	 * gestiona el evento click en un elemento de la lista. en este caso
	 * muestra llama la activity ArticleDetailActivity
	 * @see android.app.ListActivity#onListItemClick(android.widget.ListView, android.view.View, int, long)
	 */
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
	
		// creamos un intent para abrir la activity deseada
		Intent intent = new Intent (this, ArticleDetailActivity.class);
		
		intent.putExtra (PARAM_TITLE, ((TextView) v.findViewById(R.id.title)).getText ());
		intent.putExtra (PARAM_AUTHOR, ((TextView) v.findViewById (R.id.author)).getText ());
		intent.putExtra (PARAM_DATE, ((TextView) v.findViewById (R.id.date)).getText ());
		
		startActivity (intent);
	}




	/**
	 * genera el menœ de 
	 * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.articlelist, menu);
		return true;
	}

	/**
	 * Gestiona las selecciones en los elementos de menu.
	 * @see android.app.Activity#onOptionsItemSelected(android.view.MenuItem)
	 * @param item nos devuelve el elemento de menœ menu seleccionado 
	 */

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		boolean result = false;
		
		switch (item.getItemId ()) {
			case R.id.menu_about :
				// mostramos la activity AboutActivity
				Intent intent = new Intent (this, AboutActivity.class);
				startActivity (intent);
				result = true;
				break;
				
			default :
				result = false;
		}
		return result;

	}
	
	/**
	 * funcion que genera el adapter para esta ListActivity
	 * @return el adapter a mostrar en la ListActivity
	 */
	private SimpleAdapter getAdapter () {

		
		// definimos los valores de la lista
		// from nos define las claves del hashmap que usaremos. To indica los id's donde los mostraremos.
		// se hace un match por posici—n.
		String [] from = new String [] {"title", "date", "author"};
		int [] to = new  int [] {R.id.title, R.id.date, R.id.author};
		
		List <HashMap <String,String>> values = new ArrayList <HashMap <String,String>> ();
		
		// rellenar la lista con datos ficticios
		for (int i = 1; i < 10; i++) {
			HashMap <String, String> item = new HashMap <String, String> ();
			if (i == 5) {
				item.put("title", "este es un t’tulo especialemnte largo hecho para ver como se ajusta en el layout");
			} else {
				item.put("title", "Titulo " + i);
			}
			item.put("author", "Autor " + i);
			item.put("date", "0" + i + "/10/2010");
			
			values.add (item);
		}
		
		// creamos el adapter, pasando los valores, el layout a usar y el mapeo de claves hash / etiquetas layout
		SimpleAdapter adapter = new SimpleAdapter(this, values, R.layout.articlelistitem, from, to);
		
		return adapter;
	}
}
