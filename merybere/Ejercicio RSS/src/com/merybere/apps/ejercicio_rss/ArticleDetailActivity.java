package com.merybere.apps.ejercicio_rss;

import data.ArticlesContract;
import data.ArticlesContract.Articles;
import data.ArticlesDbHelper;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.widget.TextView;
import app.AppIntent;

public class ArticleDetailActivity extends Activity {

	private TextView title_article_view;
	private TextView date_article_view;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.article_detail);
		
		// Pasar el título al layout
		title_article_view = (TextView) findViewById(R.id.article_detail_title);
		date_article_view = (TextView) findViewById(R.id.article_detail_pub_date);
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		
		final long id = getIntent().getLongExtra(AppIntent.EXTRA_ID, -1);
		
		getArticle(id);
	}

	private void getArticle(long id) {

		ArticlesDbHelper helper = new ArticlesDbHelper(this);
		SQLiteDatabase db = helper.getReadableDatabase();
		
		String table = ArticlesContract.Articles.TABLE_NAME;
		String[] columns = new String[] { Articles._ID, Articles.TITLE, Articles.PUB_DATE};
		// la parte del where; usamos el ?, en los datos, ya que como se suele coger de pantalla, se normalizará
		String selection = Articles._ID + "=?";
		String[] selectionArgs = new String[]{"" + id};
		String orderBy = null;
		String groupBy = null;
		String having = null;
		
		final Cursor cursor = db.query(table, columns, selection, selectionArgs, groupBy, having, orderBy);
		// Caso de que la query devolviera más de un resultado, movemos el cursor al primer elemento
		cursor.moveToFirst();
		
		final String title_article = cursor.getString(cursor.getColumnIndex(Articles.TITLE));
		title_article_view.setText(title_article);
		
		final Long date_article = cursor.getLong(cursor.getColumnIndex(Articles.PUB_DATE));
		date_article_view.setText(DateUtils.getRelativeTimeSpanString(date_article));
		
		cursor.close();
	}

}
