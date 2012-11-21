package com.merybere.apps.ejercicio_rss;

import data.ArticlesContract;
import data.ArticlesContract.Articles;
import data.ArticlesDbHelper;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.webkit.WebView;
import android.widget.TextView;
import app.AppIntent;

public class ArticleDetailActivity extends Activity {

	private TextView title_article_view;
	private TextView date_article_view;
	private WebView content_article_view;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.article_detail);
		
		// Identificar los campos que voy a mostrar
		title_article_view = (TextView) findViewById(R.id.article_detail_title);
		date_article_view = (TextView) findViewById(R.id.article_detail_pub_date);
		content_article_view = (WebView) findViewById(R.id.article_detail_content);
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		
		final long id = getIntent().getLongExtra(AppIntent.EXTRA_ID, -1);
		
		getArticle(id);
	}

	private void getArticle(long id) {

		// Cargar el helper de la BD
		ArticlesDbHelper helper = new ArticlesDbHelper(this);
		SQLiteDatabase db = helper.getReadableDatabase();
		
		String table = ArticlesContract.Articles.TABLE_NAME;
		//String[] columns = new String[] { Articles._ID, Articles.TITLE, Articles.PUB_DATE};
		String[] columns = null;
		// la parte del where; usamos el ?, en los datos, ya que como se suele coger de pantalla, se normalizará
		String selection = Articles._ID + "=?";
		String[] selectionArgs = new String[]{"" + id};
		String orderBy = null;
		String groupBy = null;
		String having = null;
		
		final Cursor cursor = db.query(table, columns, selection, selectionArgs, groupBy, having, orderBy);
		// Caso de que la query devolviera más de un resultado, movemos el cursor al primer elemento
		cursor.moveToFirst();
		
		// Coger la columna de título
		final String title_article = cursor.getString(cursor.getColumnIndex(Articles.TITLE));
		title_article_view.setText(title_article);
		
		// Coger la columna de fecha
		final Long date_article = cursor.getLong(cursor.getColumnIndex(Articles.PUB_DATE));
		date_article_view.setText(DateUtils.getRelativeTimeSpanString(date_article));
		
		final String content_article = (String) cursor.getString(cursor.getColumnIndex(Articles.CONTENT));
		String baseUrl = null;
		String mimeType = null;
		String encoding = "UTF-8";
		String historyUrl = "";
		content_article_view.loadDataWithBaseURL(baseUrl, content_article, mimeType, encoding, historyUrl);
		
		// Una vez que se ha acabado de utilizar el cursor, hay que cerrarlo para liberar
		cursor.close();
	}

}
