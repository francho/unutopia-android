package cat.foixench.apps.lectorss;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SimpleAdapter;

public class ArticleListActivity extends ListActivity {

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
			item.put("title", "Titulo " + i);
			item.put("author", "Autor " + i);
			item.put("date", "0" + i + "/10/2010");
			
			values.add (item);
		}
		
		// creamos el adapter, pasando los valores, el layout a usar y el mapeo de claves hash / etiquetas layout
		SimpleAdapter adapter = new SimpleAdapter(this, values, R.layout.articlelistitem, from, to);
		
		return adapter;
	}
}
