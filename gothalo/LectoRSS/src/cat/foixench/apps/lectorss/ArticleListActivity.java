package cat.foixench.apps.lectorss;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cat.foixench.apps.lectorss.db.RssDbHelper;
import cat.foixench.apps.lectorss.db.RssContract.FeedsTable;
import cat.foixench.apps.lectorss.utils.LectoRSSInterface;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class ArticleListActivity extends ListActivity implements LectoRSSInterface{

	
	private SimpleCursorAdapter adapter;
	
	/**
	 *  Crea la activity, cargando el layout asociado articlelist.xml
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// indicamos el layout articlelist que usa esta activity
		setContentView(R.layout.articlelist);

		// // generamos un adapter desde codigo
		// SimpleAdapter adapter = this.getAdapter ();
		
		// definimos un adapter para la lista,de modo que se acceda a una bbdd
		adapter = this.getDBAdapter();
		
		// asociamos el adapter a la list view.
		setListAdapter(adapter);
		
	}
		
	
	
	
	/** 
	 * en este metodo inicializamos abrimos el cursor a la base de datos.
	 * @see android.app.Activity#onStart()
	 */
	@Override
	protected void onStart() {
		super.onStart();
		
		adapter.changeCursor(this.getFeeds());
		
	}




	/**
	 * en este método liveramos el cursor, para que cuando la app pase a segundo plano, no consuma recursos extras.
	 * @see android.app.Activity#onStop()
	 */
	@Override
	protected void onStop() {
		super.onStop();
		
		adapter.changeCursor(null);
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
	 * genera el menú de 
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
	 * @param item nos devuelve el elemento de menú menu seleccionado 
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
		// se hace un match por posición.
		String [] from = new String [] {"title", "date", "author"};
		int [] to = new  int [] {R.id.title, R.id.date, R.id.author};
		
		List <HashMap <String,String>> values = new ArrayList <HashMap <String,String>> ();
		
		// rellenar la lista con datos ficticios
		for (int i = 1; i < 10; i++) {
			HashMap <String, String> item = new HashMap <String, String> ();
			if (i == 5) {
				item.put("title", "este es un título especialemnte largo hecho para ver como se ajusta en el layout");
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
	
	private SimpleCursorAdapter getDBAdapter () {
		
		String [] from = new String [] {FeedsTable.COLUMN_TITLE, FeedsTable.COLUMN_DESCRIPTION, FeedsTable.COLUMN_PUB_DATE};
		int [] to = new  int [] {R.id.title, R.id.author, R.id.date};
		
		SimpleCursorAdapter adapter = new SimpleCursorAdapter (this, R.layout.articlelistitem, null, from, to, SimpleCursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
	
		return adapter;
	}
	
	private Cursor getFeeds () {
	
		// recuperamos la base de datos mediante el helper de la bbdd.
		RssDbHelper helper = new RssDbHelper(this);
		SQLiteDatabase db = helper.getReadableDatabase();
		
		
		// parametros de la table
		String table = FeedsTable.TABLE_NAME;
		String [] columns = new String [] {FeedsTable._ID, FeedsTable.COLUMN_TITLE, FeedsTable.COLUMN_DESCRIPTION, FeedsTable.COLUMN_PUB_DATE};
		String selection = null;
		String [] selectionArgs = null;
		String groupBy = null;
		String having = null;
		String orderBy = FeedsTable.COLUMN_PUB_DATE + " DESC";
		
		return db.query(table, columns, selection, selectionArgs, groupBy, having, orderBy);
	}
}
