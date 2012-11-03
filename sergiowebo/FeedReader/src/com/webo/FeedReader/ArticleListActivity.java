package com.webo.FeedReader;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;


public class ArticleListActivity extends Activity {
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	    setContentView(R.layout.article_list);
	         
	    ListView lv = (ListView)findViewById(R.id.listView1);
	         
	    ArrayList<ArticleItem> itemsList = PruebaDatos();
	         
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
