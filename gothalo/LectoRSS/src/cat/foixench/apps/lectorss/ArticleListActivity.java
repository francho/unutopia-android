package cat.foixench.apps.lectorss;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class ArticleListActivity extends Activity {

	/**
	 *  Crea la activity, cargando el layout asociado articlelist.xml
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		// indicamos el layout articlelist que usa esta activity
		setContentView(R.layout.articlelist);
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
}
