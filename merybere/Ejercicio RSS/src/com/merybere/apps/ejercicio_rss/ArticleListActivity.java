package com.merybere.apps.ejercicio_rss;

import java.util.HashMap;

import data.ArticleContract;
import data.DummyArticles;

import widget.ArticlesAdapter;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.ListView;
import app.AppIntent;

public class ArticleListActivity extends ListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		
		final ArticlesAdapter adapter = new ArticlesAdapter(this, new DummyArticles());
		
		// Layout de la activity
		setListAdapter(adapter);
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		
		// Cargar la activity del detalle de artículo
		final Adapter adapter = l.getAdapter();
		
		HashMap<String,Object> article = (HashMap) adapter.getItem(position);
		
		final String title = (String) article.get(ArticleContract.TITLE);
		
		Intent intent = AppIntent.getArticleIntent(title);
		
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
