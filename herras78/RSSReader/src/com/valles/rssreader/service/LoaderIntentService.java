package com.valles.rssreader.service;

import java.net.URL;
import java.util.ArrayList;

import com.valles.rssreader.db.RssContract.FeedsTable;
import com.valles.rssreader.db.RssDbHelper;

import nl.matshofman.saxrssreader.RssFeed;
import nl.matshofman.saxrssreader.RssItem;
import nl.matshofman.saxrssreader.RssReader;

import android.app.IntentService;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class LoaderIntentService extends IntentService {
	
	public static final String START_LOAD = "com.valles.rssreader.START";
	public static final String SET_PROGRESS = "com.valles.rssreader.PROGRESS";
	public static final String END_LOAD = "com.valles.rssreader.END";
	String TAG = "RSSREADER";

	public LoaderIntentService() {
		super("LoaderIntentService");
		Log.d(TAG,"Contructor LoaderIntentService");
	}

	protected void onHandleIntent(Intent intent) {
		Log.d(TAG,"onHandleIntent");
		final RssDbHelper helper = new RssDbHelper(this);
		final URL url;
		int progress = intent.getIntExtra("progress", 0);
		
		try {
			Log.d(TAG,"Primera linea de Try");
			url = new URL("http://francho.org/feed");
			RssFeed feed = RssReader.read(url);
			ArrayList<RssItem> rssItems = feed.getRssItems();
			Log.d(TAG,"Realizada lectura de la URL");
			Intent BroadCastIntent = new Intent();
			BroadCastIntent.setAction(START_LOAD);
			BroadCastIntent.putExtra("set_max", rssItems.size());
			BroadCastIntent.putExtra("progress", progress);
			sendBroadcast(BroadCastIntent);
			Log.d(TAG,"START_LOAD enviado");
			final SQLiteDatabase db = helper.getWritableDatabase();
			Log.d(TAG,"Abriendo db para escritura");
			for(RssItem rssItem : rssItems) {
				ContentValues values = new ContentValues();
		        values.put(FeedsTable.TITLE, rssItem.getTitle());
		        values.put(FeedsTable.PUB_DATE, rssItem.getPubDate().toGMTString());
		        values.put(FeedsTable.DESCRIPTION, rssItem.getDescription());
		        values.put(FeedsTable.CONTENT, rssItem.getContent());
		        values.put(FeedsTable.AUTOR, "Francho Joven");
		        values.put(FeedsTable.CATEGORY, "Programacion");
		        values.put(FeedsTable.URL, rssItem.getLink());
		        
		        db.insert(FeedsTable.TABLE_NAME, null, values );
		        progress++;

		        BroadCastIntent.setAction(SET_PROGRESS);
				BroadCastIntent.putExtra("progress", progress);
				sendBroadcast(BroadCastIntent);
			}
			
			BroadCastIntent.setAction(END_LOAD);
			sendBroadcast(BroadCastIntent);
			
			db.close();
			
		} catch (Exception e) {
			Log.d(TAG,e + "");
		}
	}
}
