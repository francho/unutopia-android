package cat.foixench.apps.lectorss;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import cat.foixench.apps.lectorss.db.RssContract;
import cat.foixench.apps.lectorss.db.RssContract.FeedsTable;
import cat.foixench.apps.lectorss.utils.LectoRSSInterface;
import cat.foixench.apps.lectorss.widget.FeedsAdapter;

public class ArticleListActivity extends ListActivity implements LectoRSSInterface {

	
	private FeedsAdapter adapter;
	Cursor cursor;
	
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
		adapter = new FeedsAdapter (this);
		
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
		adapter.swapCursor(this.getFeeds());
		
	}

	/**
	 * en este método liveramos el cursor, para que cuando la app pase a segundo plano, no consuma recursos extras.
	 * @see android.app.Activity#onStop()
	 */
	@Override
	protected void onStop() {
		super.onStop();
		adapter.swapCursor(null);
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
		
		Log.d ("ArticleListActivity", "id = " + id);
		
//		intent.putExtra (PARAM_TITLE, ((TextView) v.findViewById(R.id.title)).getText ());
//		intent.putExtra (PARAM_AUTHOR, ((TextView) v.findViewById (R.id.author)).getText ());
//		intent.putExtra (PARAM_DATE, ((TextView) v.findViewById (R.id.date)).getText ());
		
		intent.putExtra (PARAM_ID, "" +  id);
		
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
	
//	private Cursor getFeeds () {
//	
//		// recuperamos la base de datos mediante el helper de la bbdd.
//		RssDbHelper helper = new RssDbHelper(this);
//		SQLiteDatabase db = helper.getReadableDatabase();
//		
//		// parametros de la table
//		String table = FeedsTable.TABLE_NAME;
//		String [] columns = new String [] {FeedsTable._ID, FeedsTable.COLUMN_TITLE, FeedsTable.COLUMN_AUTHOR, FeedsTable.COLUMN_PUB_DATE};
//		String selection = null;
//		String [] selectionArgs = null;
//		String groupBy = null;
//		String having = null;
//		String orderBy = FeedsTable.COLUMN_PUB_DATE + " DESC";
//		
//		return db.query(table, columns, selection, selectionArgs, groupBy, having, orderBy);
//	}

	private Cursor getFeeds () {
		
		//LectoRSSContentProvider lectorssData = new LectoRSSContentProvider();
		
		Uri uri = RssContract.FeedsTable.getUri();
		String projection [] = new String [] {FeedsTable._ID, FeedsTable.COLUMN_TITLE, FeedsTable.COLUMN_AUTHOR, FeedsTable.COLUMN_PUB_DATE};
		String sortOrder = FeedsTable.COLUMN_PUB_DATE + " DESC";
				
		
		cursor = getContentResolver().query (uri, projection, null, null, sortOrder);
		
		return cursor;
	}
}
