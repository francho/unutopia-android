package org.cacahuete.app.feedreader;

import java.util.HashMap;

import org.cacahuete.app.feedreader.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;



/**
* Splash screen activity
*
*/
public class ArticleDetailActivity extends Activity {
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		Intent intent = this.getIntent ();
		
		HashMap<String, String> articulo = (HashMap<String, String>) intent.getSerializableExtra("articulo");
		System.out.println(articulo.toString());
		setContentView(R.layout.article_detail);
	
		TextView titular = (TextView) findViewById (R.id.titular);
		titular.setText (articulo.get("titular"));
		
		TextView texto = (TextView) findViewById (R.id.texto);
		texto.setText (articulo.get("texto"));
		
		
		
	
	}
	
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.article_detail, menu);
        return true;
    }
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle item selection
	    switch (item.getItemId()) {
	        case R.id.aboutscreen:
	            this.showAboutScreen();
	            return true;
	        
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
	
	private void showAboutScreen() {
		Context context = this;
		Intent intent = new Intent(context, AboutScreenActivity.class);
		startActivity(intent);
	}

}


