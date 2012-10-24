package com.merybere.apps.ejercicio_rss;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

public class ArticleListActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.article_list);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		
		getMenuInflater().inflate(R.menu.article_list, menu);
		return super.onCreateOptionsMenu(menu);
	}

}
