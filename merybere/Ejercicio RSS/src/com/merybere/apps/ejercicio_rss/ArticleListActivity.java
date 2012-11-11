package com.merybere.apps.ejercicio_rss;

import widget.ArticlesAdapter;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import app.AppIntent;

public class ArticleListActivity extends ListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		
		final ArticlesAdapter adapter = new ArticlesAdapter(this);
		
		// Layout de la activity
		setListAdapter(adapter);
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		
		Intent intent = AppIntent.getArticleDetailIntent(id);
		
		startActivity(intent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.article_list, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Acciones para los ítems del menú
		switch (item.getItemId()) {
			case R.id.menu_about:
				// Mensajero (se crea el mensaje que se va a pasar)
				final Intent intent = AppIntent.getAboutIntent();
				
				startActivity(intent);
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}
	
}
