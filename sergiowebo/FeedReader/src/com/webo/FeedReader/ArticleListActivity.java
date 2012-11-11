package com.webo.FeedReader;

import java.util.ArrayList;

import com.webo.FeedReader.data.DB;

import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;


public class ArticleListActivity extends ListActivity {
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	    setContentView(R.layout.article_list);
	         
	    ListView lv = (ListView)findViewById(R.id.listView1);
	     
	    // Rellenando datos con una bbdd
	    final DB helper = new DB(this);
        final SQLiteDatabase db = helper.getWritableDatabase();
        
        ContentValues values = new ContentValues();
        values.put("title", "titulo 1");
        values.put("author", "author 1");
        values.put("date", "27/11/2012");
        
        long id = db.insert("article_list", null, values );
		db.close();
	    
		// Rellenando datos desde BBDD
	    ArrayList<ArticleItem> itemsList = PruebaDatosDB();
	    
	    // Rellenando datos con una clase de prueba
	    //ArrayList<ArticleItem> itemsList = PruebaDatos();
	         
	    ArticleListAdapter adapter = new ArticleListAdapter(this, itemsList);
	         
	    lv.setAdapter(adapter);
	}	
	
	public ArrayList<ArticleItem> PruebaDatos() {
		ArrayList<ArticleItem> listItems = new ArrayList<ArticleItem>();
		listItems.add(new ArticleItem(1, "Titulo 1", "Autor 1", "26/06/2012"));
		listItems.add(new ArticleItem(2, "Titulo 2", "Autor 2", "26/06/2012"));
		listItems.add(new ArticleItem(3, "Titulo 3", "Autor 3", "26/06/2012"));
		return listItems;
	}
	
	private ArrayList<ArticleItem> PruebaDatosDB() {
		ArrayList<ArticleItem> listItems = new ArrayList<ArticleItem>();
		
		DB helper = new DB(this);
		SQLiteDatabase db = helper.getReadableDatabase();
		
		String table = "article_list";
		String[] columns = new String[] { "id", "title", "author", "date" };
		String selection = null;
		String[] selectionArgs = null;
		String orderBy = "date" + " DESC";
		String groupBy = null;
		String having = null;
		Cursor cursor = db.query(table, columns, selection, selectionArgs, groupBy, having, orderBy);
		
		if (cursor.moveToFirst()) {
			do {
				listItems.add(new ArticleItem(new Long(cursor.getInt(0)).longValue(),cursor.getString(1),cursor.getString(2), cursor.getString(3)));
			} while (cursor.moveToNext());
		}
	 
		if (cursor != null && !cursor.isClosed()) {
			cursor.close();
		}
		db.close();
		
		return listItems;
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
		case R.id.menu_about:
			Context context = this;
			Intent intent = new Intent(context, AboutActivity.class);
			startActivity(intent);
			return true;
		}
		return false;
	}
}
