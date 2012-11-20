package org.cacahuete.app.feedreader.db;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

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

	public void reloadArticles(ContentResolver rss) {
		Log.d(TAG, "reload start");
		
		
		String sortOrder = null;
		String[] selectionArgs = null;
		String selection = null;
		String[] projection = new String[] { 
				FeedsTable.URL
				};
		Uri uri=FeedsTable.getUri();
		
		Cursor cursor=rss.query(uri, projection, selection, selectionArgs, sortOrder);
		Log.d(TAG,"cursor tiene "+cursor.getColumnCount());
		Uri uri_insert_articles=ArticlesTable.getUri();
		
		try {
			while (cursor.moveToNext()) {
				URL url=new URL(cursor.getString(cursor.getColumnIndex("url")));
				Log.d(TAG,"nueva url es "+url.toString());
				try {
					RssFeed feed = RssReader.read(url);
					//Log.d(TAG,"FEED ES "+feed.toString());
				
					ArrayList<RssItem> rssItems = feed.getRssItems();
					Log.d(TAG,"feed tiene "+rssItems.size()+" elementos");				
					for(RssItem rssItem : rssItems) {
						ContentValues values = new ContentValues();
						values.put(ArticlesTable.TITLE,rssItem.getTitle());
						values.put(ArticlesTable.CONTENT,rssItem.getContent());
						values.put(ArticlesTable.LINK,rssItem.getLink());
						values.put(ArticlesTable.DESCRIPTION,rssItem.getDescription());
						//values.put(ArticlesTable.PUBDATE,rssItem.getPubDate().toString());
						rss.insert(uri_insert_articles, values);
//						Log.d("RSS Reader ", rssItem.getTitle());
//						Log.d("RSS Reader ", rssItem.getContent());
//						Log.d("RSS Reader ", rssItem.getDescription());
//						Log.d("RSS Reader ", rssItem.getLink());
//						Log.d("RSS Reader ", rssItem.getFeed().toString());
					}//for
				} catch (Exception e) {
					Log.d(TAG,"Problemas con internet");
					
				}
				
			}//while
			Log.d(TAG, "reload stop at "+System.currentTimeMillis()*1000+" segs");
		} catch (Exception e) {
			Log.d(TAG,"Problemas con la bd");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		cursor.close();
		
		
		Log.d(TAG, "hardwordk stop");
	}
	
	

}
