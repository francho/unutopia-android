package org.cacahuete.app.feedreader;

import java.util.ArrayList;
import java.util.HashMap;


import org.cacahuete.app.feedreader.R;

import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import android.app.ListActivity;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.ContentResolver;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;


import org.cacahuete.app.feedreader.db.RssContract.ArticlesTable;
import android.database.Cursor;




/**
* Splash screen activity
*
*/
public class ArticleListActivity extends ListActivity {
	
	
    private SimpleCursorAdapter adapter;
    private String TAG="ARTICLE LIST ACTIVITY";
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		
		Uri uri = ArticlesTable.getUri();
		try {
			ContentResolver mContentResolver=getContentResolver();
			String[] projection=null;
			String selection=null;
			String[] selectionArgs=null;
			String sortOrder=null;
			Cursor cursor = mContentResolver.query(uri, projection, selection, selectionArgs, sortOrder);
		
		
		
		int layout = android.R.layout.simple_list_item_2;
		
		String[] from = new String[] { ArticlesTable.TITLE, ArticlesTable.LINK };
		int[] to = new int[]{android.R.id.text1, android.R.id.text2 };
		adapter = new SimpleCursorAdapter(this, layout, cursor, from, to, 0);
	
		setListAdapter(adapter);
		
		}
		catch(Exception e) {
			Log.d(TAG,"FALLO EN BD");
			e.printStackTrace();
		}
		
		
		
	}

	
	


    @Override
	protected void onStop() {
		adapter.changeCursor(null);
		
    		super.onStop();
	}
	
	
	
	
	/**
	 * @see android.app.ListActivity#onListItemClick(android.widget.ListView, android.view.View, int, long)
	 */
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		// creamos un intent para abrir la activity deseada
		Intent intent = new Intent (this, ArticleDetailActivity.class);
		System.out.println(l.getItemAtPosition(position).getClass());
		Cursor q=(Cursor) l.getItemAtPosition(position);
		String title=q.getString(q.getColumnIndex(ArticlesTable.TITLE));
		String link=q.getString(q.getColumnIndex(ArticlesTable.LINK));
		
		HashMap<String,String> params=new HashMap();
		params.put("title",title);
		params.put("link",link);
		
		System.out.println(params.toString());
		
		intent.putExtra ("articulo", params);
				
		startActivity (intent);
	}
	
		
		
	
	
	
	
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.article_list, menu);
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





