package org.cacahuete.app.feedreader.db;


import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


import nl.matshofman.saxrssreader.RssFeed;
import nl.matshofman.saxrssreader.RssItem;
import nl.matshofman.saxrssreader.RssReader;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;


import org.cacahuete.app.feedreader.db.RssContract.ArticlesTable;
import org.cacahuete.app.feedreader.db.RssContract.FeedsTable;


public class RssReload {

	
	private static final String TAG = "RSS reload";
	private ArrayList<URL> feed_list;
	
	public void loadFeeds(ContentResolver rss) {
		String sortOrder = null;
		String[] selectionArgs = null;
		String selection = null;
		String[] projection = new String[] { 
				FeedsTable.URL
				};
		Uri uri=FeedsTable.getUri();
		
		Cursor cursor=rss.query(uri, projection, selection, selectionArgs, sortOrder);
		Log.d(TAG,"cursor tiene "+cursor.getColumnCount());
		
		this.feed_list=new ArrayList<URL>();
		try {
			while (cursor.moveToNext()) {
				URL url=new URL(cursor.getString(cursor.getColumnIndex(FeedsTable.URL)));
				this.feed_list.add(url);
				Log.d(TAG,"nueva url es "+url.toString());
			}
		} catch (Exception e) {
				Log.d(TAG,"Problemas con la bd");
				cursor.close();
				e.printStackTrace();
		}//try
		cursor.close();
	}
	
	public Integer getNumberOfFeeds() {
		Integer num=this.feed_list.size();
		return num;
		
	}
	public ArrayList<URL> getFeedList() {
		
		return this.feed_list;
		
	}
	
	public void borrarArticulos(ContentResolver rss) {   
		//vaciamos tabla de articulos
		rss.delete(ArticlesTable.getUri(), null, null);
	}

	public ArrayList<RssItem> reloadArticlesForFeed(URL url) {
		RssFeed feed=null;
		ArrayList<RssItem> rssItems=null;
	
		try {
			feed = RssReader.read(url);
			 rssItems = feed.getRssItems();
			}
		catch (Exception e) {}
		
		return rssItems;
}
	
	public void insertArticle(ContentResolver rss,RssItem rssItem) {
		Uri uri_insert_articles=ArticlesTable.getUri();
		
		ContentValues values = new ContentValues();
		values.put(ArticlesTable.TS_DATE,Long.valueOf(rssItem.getPubDate().getTime()));
		Date fecha=new Date(rssItem.getPubDate().getTime());
		//String fecha_formateada =new SimpleDateFormat("dd.MM.yyyy HH:mm").format(fecha);
		String fecha_formateada =new SimpleDateFormat("dd.MM.yyyy").format(fecha);
		values.put(ArticlesTable.PUBDATE,fecha_formateada);
		//Log.d(TAG,values.toString());
		
//		try {
//			values.put(ArticlesTable.TITLE,URLEncoder.encode(rssItem.getTitle(), "UTF-8"));
//		} catch (UnsupportedEncodingException e) {
//			values.put(ArticlesTable.TITLE,rssItem.getTitle());
//		}
//		try {
//			values.put(ArticlesTable.CONTENT,URLEncoder.encode(rssItem.getContent(), "UTF-8"));
//		} catch (UnsupportedEncodingException e) {
//			values.put(ArticlesTable.CONTENT,rssItem.getContent());
//		}
//		try {
//			values.put(ArticlesTable.LINK,URLEncoder.encode(rssItem.getLink(), "UTF-8"));
//		} catch (UnsupportedEncodingException e) {
//			values.put(ArticlesTable.LINK,rssItem.getLink());
//		}
//		try {
//			values.put(ArticlesTable.DESCRIPTION,URLEncoder.encode(rssItem.getDescription(), "UTF-8"));
//		} catch (UnsupportedEncodingException e) {
//			values.put(ArticlesTable.DESCRIPTION,rssItem.getDescription());
//		}
		
		try {
			values.put(ArticlesTable.TITLE,URLDecoder.decode(rssItem.getTitle(), "UTF-8"));
		} catch (Exception e) {
			values.put(ArticlesTable.TITLE,rssItem.getTitle());
		}
		try {
			values.put(ArticlesTable.CONTENT,URLDecoder.decode(rssItem.getContent(), "UTF-8"));
		} catch (Exception e) {
			values.put(ArticlesTable.CONTENT,rssItem.getContent());
		}
		try {
			values.put(ArticlesTable.LINK,URLDecoder.decode(rssItem.getLink(), "UTF-8"));
		} catch (Exception e) {
			values.put(ArticlesTable.LINK,rssItem.getLink());
		}
		try {
			values.put(ArticlesTable.DESCRIPTION,URLDecoder.decode(rssItem.getDescription(), "UTF-8"));
		} catch (Exception e) {
			values.put(ArticlesTable.DESCRIPTION,rssItem.getDescription());
		}
		
		
		//Log.d(TAG,"Vamos a mostrar values e insertar");
		
		rss.insert(uri_insert_articles, values);
		
	}
	
//	public void reloadArticles(ContentResolver rss) {
//		Log.d(TAG, "RssReload start");
//		Uri uri_insert_articles=ArticlesTable.getUri();
//		
//		
//		//vaciamos tabla de articulos
//		rss.delete(ArticlesTable.getUri(), null, null);
//		
//		Log.d(TAG,"Terminada recogida de feeds, comenzamos con descarga de articles");
//		try {
//			for(URL url : this.feed_list) {
//				RssFeed feed = RssReader.read(url);
//				//Log.d(TAG,"FEED ES "+feed.toString());
//			
//				ArrayList<RssItem> rssItems = feed.getRssItems();
//				Log.d(TAG,"feed tiene "+rssItems.size()+" elementos");				
//				for(RssItem rssItem : rssItems) {
//					Log.d(TAG,"Comienza el for");
//					ContentValues values = new ContentValues();
//					values.put(ArticlesTable.TS_DATE,Long.valueOf(rssItem.getPubDate().getTime()));
//					Date fecha=new Date(rssItem.getPubDate().getTime());
//					//String fecha_formateada =new SimpleDateFormat("dd.MM.yyyy HH:mm").format(fecha);
//					String fecha_formateada =new SimpleDateFormat("dd.MM.yyyy").format(fecha);
//					values.put(ArticlesTable.PUBDATE,fecha_formateada);
//					//Log.d(TAG,values.toString());
//					
//					values.put(ArticlesTable.TITLE,rssItem.getTitle());
//					values.put(ArticlesTable.CONTENT,rssItem.getContent());
//					values.put(ArticlesTable.LINK,rssItem.getLink());
//					values.put(ArticlesTable.DESCRIPTION,rssItem.getDescription());
//					
//					
//					//Log.d(TAG,"Vamos a mostrar values e insertar");
//					
//					rss.insert(uri_insert_articles, values);
////					Log.d("RSS Reader ", rssItem.getTitle());
////					Log.d("RSS Reader ", rssItem.getContent());
////					Log.d("RSS Reader ", rssItem.getDescription());
////					Log.d("RSS Reader ", rssItem.getLink());
////					Log.d("RSS Reader ", rssItem.getFeed().toString());
//				}//for
//			}
//			} catch (Exception e) {
//					Log.d(TAG,"Problemas con internet");
//					
//				}
//				
//	
//			//Log.d(TAG, "reload stop at "+System.currentTimeMillis()*1000+" segs");
//
//		
//		
//		
//		
//		Log.d(TAG, "RssReload stop");
//	}
	
	

}
