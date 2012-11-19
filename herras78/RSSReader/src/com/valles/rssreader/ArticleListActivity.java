package com.valles.rssreader;

import com.valles.rssreader.db.RssContract.FeedsTable;
import com.valles.rssreader.db.RssDbHelper;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class ArticleListActivity extends Activity {
	 	
	private SimpleCursorAdapter adapter;
	private RssDbHelper helper = new RssDbHelper(this);
	private TextView TxtFuentes;
	private Cursor c;
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.article_list);

        PrepareAdapter();

		ListView FeedList = (ListView)findViewById(R.id.feedlist);
		FeedList.setAdapter(adapter);
		
        final TextView TxtTitulo = (TextView) findViewById(R.id.lbltitulo);
        final Typeface font1 = Typeface.createFromAsset(getAssets(),"Last Ninja.ttf");
        TxtFuentes = (TextView) findViewById(R.id.contfuentes);
        TxtTitulo.setTypeface(font1);

        final ImageView acercade = (ImageView) findViewById(R.id.acercade);
        acercade.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {           	
            	ToAbout();            	
            }
        });
        
        final ImageView reload = (ImageView) findViewById(R.id.reload);
        acercade.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {           	
            	ToProgress();            	
            }
        });
        
        FeedList.setOnItemClickListener(new OnItemClickListener() {
        	public void onItemClick(AdapterView<?> a, View v, int position, long id) {       		       		
        		c.moveToPosition(position);      		
        		Intent intent = new Intent(ArticleListActivity.this, ItemReader.class);
        		Bundle bundle = new Bundle();
        		bundle.putInt("ID",c.getInt(0));     		
        		intent.putExtras(bundle);
        		startActivity(intent);
        	}
        });
    }
	
	protected void onStart() {
		super.onStart();
		adapter.changeCursor(getFeeds());
		c = adapter.getCursor();
		TxtFuentes.setText("Noticias:" + c.getCount());
	}

	protected void onStop() {
		adapter.changeCursor(null);
    	super.onStop();
	}
	
	public void ToAbout(){
		Intent intent = new Intent(ArticleListActivity.this, AboutActivity.class);     
        startActivity(intent); 
	}
	
	public void ToProgress(){
		Intent intent = new Intent(ArticleListActivity.this, ProgressActivity.class);     
        startActivity(intent); 
	}
	
	public void PrepareAdapter(){	
		Context context = this;
		int layout = R.layout.feed_item;
		Cursor cursor = null;
		String[] from = new String[] {FeedsTable.TITLE,FeedsTable.PUB_DATE};
		int[] to = new int[]{R.id.tit_item,R.id.date_item};
		
		adapter = new SimpleCursorAdapter(context, layout, cursor, from, to);
	}
	
	private Cursor getFeeds() {
		SQLiteDatabase db = helper.getReadableDatabase();
		
		String table = FeedsTable.TABLE_NAME;
		String[] columns = new String[] {FeedsTable._ID,FeedsTable.TITLE,FeedsTable.PUB_DATE,FeedsTable.DESCRIPTION};
		String selection = null;
		String[] selectionArgs = null;
		String groupBy = null;
		String having = null;
		String orderBy = FeedsTable.PUB_DATE + " DESC";
		return db.query(table, columns, selection, selectionArgs, groupBy, having, orderBy);
		}

	public boolean onCreateOptionsMenu(Menu menu) {  
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.layout.menu_article_list, menu);
	    return true;
	}
	
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	        case R.id.menu_about:
	        	ToAbout();
	            return true;
	        case R.id.menu_buscar:
	        	ToProgress(); 
	            return true;
	        case R.id.menu_mas:            
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
}
	

