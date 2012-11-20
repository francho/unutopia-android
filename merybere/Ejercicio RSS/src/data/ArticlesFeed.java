package data;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;

import data.ArticlesContract.Articles;


import nl.matshofman.saxrssreader.RssFeed;
import nl.matshofman.saxrssreader.RssItem;
import nl.matshofman.saxrssreader.RssReader;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class ArticlesFeed {

	private static final String TAG = "ArticlesFeed";

	// Tarea pesada para ejecutar en segundo plano que accede a los feeds y los carga en la BD
	public void loadNewArticles(SQLiteDatabase db) {
		Log.d(TAG, "load feed start");
		
		URL url;
		RssFeed feed;
		
		try {
			url = new URL(RSSInterface.URL_FEED);
			feed = RssReader.read(url);
			
			ArrayList<RssItem> rssItems = feed.getRssItems();
			for(RssItem rssItem : rssItems) {
			    Log.i("RSS Reader", rssItem.getTitle());
			    
			    if(notInDb(db, rssItem.getLink())) {
			    	addArticle(db, rssItem);
			    }
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		Log.d(TAG, "load feed stop");
	}
	
	// Método para comprobar si el artículo ya está en la base de datos
	private boolean notInDb(SQLiteDatabase db, String link) {

		String table = Articles.TABLE_NAME;
		String[] columns = null;
		String selection = Articles.LINK + " = '" + link + "'";
		String[] selectionArgs = null;
		String groupBy = null;
		String having = null;
		String orderBy = null;
		Cursor cursor = db.query(table, columns, selection, selectionArgs , groupBy, having, orderBy);
		
		return cursor.getCount() == 0;
	}

	public static void addArticle(SQLiteDatabase db, RssItem rssItem) {
		
		ContentValues values = new ContentValues();
		
		values.put(Articles.TITLE, rssItem.getTitle());
		
		// Convertir la fecha al formato deseado
		Long millis = parseDate(rssItem.getPubDate());
		values.put(Articles.PUB_DATE, millis);
		
		values.put(Articles.LINK, rssItem.getLink());
		values.put(Articles.DESCRIPTION, rssItem.getDescription());
		values.put(Articles.CONTENT, rssItem.getContent());
		
		// Insertar el artículo en la base de datos
		db.insert(Articles.TABLE_NAME, null, values);
		
	}

	private static Long parseDate(Date date) {
		return date.getTime();
	}
}
