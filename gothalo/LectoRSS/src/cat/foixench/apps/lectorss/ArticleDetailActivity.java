/**
 * 
 */
package cat.foixench.apps.lectorss;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.TextView;
import cat.foixench.apps.lectorss.db.RssContract;
import cat.foixench.apps.lectorss.db.RssContract.FeedsTable;
import cat.foixench.apps.lectorss.utils.LectoRSSInterface;
import cat.foixench.apps.lectorss.utils.Utils;

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

		String strTitle = "";
		String strDate = "";
		String strAuthor = "";
		String strDescription = "";
		
		super.onCreate(savedInstanceState);
		setContentView (R.layout.articledetail);
		
		// recuperamos los parametros via Intent
		Intent intent = this.getIntent ();
		
		String strId = intent.getStringExtra (PARAM_ID);
		
		// Recuperamos la informaci—n del item a partir del id recibido como parametro.
		
		Uri uri = RssContract.FeedsTable.getUri();
		String projection [] = new String [] {FeedsTable._ID, FeedsTable.COLUMN_TITLE, FeedsTable.COLUMN_AUTHOR, FeedsTable.COLUMN_PUB_DATE, FeedsTable.COLUMN_DESCRIPTION, FeedsTable.COLUMN_LINK};
		String selection = FeedsTable._ID + " = ? ";
		String selectionArgs [] = new String [] {strId};
		
		Cursor cursor = getContentResolver().query (uri, projection, selection, selectionArgs, null);
		
		
		
		if (cursor.moveToFirst()) {
			strTitle = cursor.getString (cursor.getColumnIndex (FeedsTable.COLUMN_TITLE));
			strDate = Utils.millisToDate (this, cursor.getLong (cursor.getColumnIndex (FeedsTable.COLUMN_PUB_DATE)));
			strAuthor = cursor.getString (cursor.getColumnIndex (FeedsTable.COLUMN_AUTHOR));
			strDescription = cursor.getString (cursor.getColumnIndex (FeedsTable.COLUMN_DESCRIPTION));
		}
		
		
		
//		String strTitle = intent.getStringExtra(PARAM_TITLE);
//		String strDate = intent.getStringExtra (PARAM_DATE);
//		String strAuthor = intent.getStringExtra (PARAM_AUTHOR);
//		
		TextView tvDetailTitle = (TextView) findViewById (R.id.detail_title);
		tvDetailTitle.setText (strTitle);
		
		TextView tvDate = (TextView) findViewById (R.id.date);
		tvDate.setText (strDate);
		
		TextView tvAuthor = (TextView) findViewById (R.id.author);
		tvAuthor.setText (strAuthor);
		
		WebView tvDescription = (WebView) findViewById (R.id.article_content);
		// damos un poco de contenido al texto, para que mantenga la apariencia de la aplicacion
		String strDescHead, strDescFoot, strBackground, strColor;
		strBackground = "#" + Integer.toHexString (getResources ().getColor (R.color.app_background)).substring (2);
		strColor = "#"  + Integer.toHexString (getResources ().getColor (R.color.default_color)).substring (2);
		strDescHead = "<body style='background-color:" + strBackground + "; color:" + strColor + "'>";
		strDescFoot = "</body>";
		
		Log.d ("ArticleDetailActivity", strDescHead + strDescription + strDescFoot);
		
		tvDescription.loadDataWithBaseURL (null, strDescHead + strDescription + strDescFoot, "text/html", "utf-8", null);
		
		cursor.close();
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
