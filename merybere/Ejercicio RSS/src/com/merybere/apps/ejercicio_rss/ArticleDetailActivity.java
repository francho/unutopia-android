package com.merybere.apps.ejercicio_rss;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ArticleDetailActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		
		setContentView (R.layout.article_detail);

		// Obtener el título del artículo pasado como parámetro
		Intent intent = this.getIntent ();
		String title = intent.getStringExtra("title");
		
		// Pasar el título al layout
		TextView title_article = (TextView) findViewById(R.id.article_detail_title);
		title_article.setText(title);
	}

}
