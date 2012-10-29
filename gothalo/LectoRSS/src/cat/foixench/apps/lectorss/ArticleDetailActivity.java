/**
 * 
 */
package cat.foixench.apps.lectorss;

import cat.foixench.apps.lectorss.utils.LectoRSSInterface;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
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
	
	

}
