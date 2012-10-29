/**
 * 
 */
package cat.foixench.apps.lectorss;

import cat.foixench.apps.lectorss.utils.LectoRSSInterface;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

/**
 * clase que implementa la construcci—n de la activity detalle de articulo
 * @author llorenc
 *
 */
public class ArticleDetailActivity extends Activity implements LectoRSSInterface {

	/**
	 * evento creaci—n del articulo. cargamos el layout y mostramos la informaci—n que nos llega como
	 * parametros del intent
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView (R.layout.articledetail);
		
		// recuperamos los parametros via Intent
		Intent intent = this.getIntent ();
		String strTitle = intent.getStringExtra(PARAM_TITLE);
		String strDate = intent.getStringExtra (PARAM_DATE);
		String strAuthor = intent.getStringExtra (PARAM_AUTHOR);
		
		TextView tvDetailTitle = (TextView) findViewById (R.id.detail_title);
		tvDetailTitle.setText (strTitle);
		
		TextView tvDate = (TextView) findViewById (R.id.date);
		tvDate.setText (strDate);
		
		TextView tvAuthor = (TextView) findViewById (R.id.author);
		tvAuthor.setText (strAuthor);
		
	}

	/** 
	 * genera el menœ de la aplicaci—n en esta Activity
	 * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.articlelist, menu);
		return true;
	}

	/**
	 * gestiona el click en los elementos del menœ.
	 * @see android.app.Activity#onOptionsItemSelected(android.view.MenuItem)
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
