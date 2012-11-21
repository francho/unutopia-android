package org.cacahuete.app.feedreader;

import org.cacahuete.app.feedreader.R;
import org.cacahuete.app.feedreader.db.RssContract.ArticlesTable;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.TextView;



/**
* Splash screen activity
*
*/
public class ArticleDetailActivity extends Activity {
	 
	private String TAG="ARTICLE DETAIL ACTIVITY";
	 
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		Intent intent = this.getIntent ();
		
		Long id_articulo =(Long) intent.getSerializableExtra("id_articulo");
		Uri uri = ArticlesTable.getUri(id_articulo);
		System.out.println(id_articulo.toString());
		
		Cursor cursor=null;
		try {
			ContentResolver mContentResolver=getContentResolver();
			String[] projection=null;
			String selection=null;
			String[] selectionArgs=null;
			String sortOrder=null;
			
			cursor = mContentResolver.query(uri, projection, selection, selectionArgs, sortOrder);
		}
		catch(Exception e) {
			Log.d(TAG,"FALLO EN BD");
			e.printStackTrace();
		}
		
		cursor.moveToFirst();
		
		//Log.d(TAG,"Title es "+cursor.getString(cursor.getColumnIndex(ArticlesTable.TITLE)));
		//Log.d(TAG,"Content es "+cursor.getString(cursor.getColumnIndex(ArticlesTable.CONTENT)));
		
		setContentView(R.layout.article_detail);
	
		TextView titular = (TextView) findViewById (R.id.titular);
		titular.setText (cursor.getString(cursor.getColumnIndex(ArticlesTable.TITLE)));
		
		WebView texto = (WebView) findViewById (R.id.texto);
		texto.loadData(cursor.getString(cursor.getColumnIndex(ArticlesTable.DESCRIPTION)),"text/html","UTF-8");
		//texto.loadData(cursor.getString(cursor.getColumnIndex(ArticlesTable.DESCRIPTION)),"text/html","iso-8859-1");
		//texto.loadData(cursor.getString(cursor.getColumnIndex(ArticlesTable.DESCRIPTION)),"text/html",null);
		cursor.close();
		
		
	
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


