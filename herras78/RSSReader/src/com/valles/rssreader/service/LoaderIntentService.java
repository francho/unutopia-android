package com.valles.rssreader.service;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;

import com.valles.rssreader.db.RssContract.FeedsTable;
import com.valles.rssreader.db.RssDbHelper;

import nl.matshofman.saxrssreader.RssFeed;
import nl.matshofman.saxrssreader.RssItem;
import nl.matshofman.saxrssreader.RssReader;

import android.app.IntentService;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class LoaderIntentService extends IntentService {
	
	public  RssDbHelper helper = new RssDbHelper(this);
	public static final String START_LOAD = "com.valles.rssreader.START";
	public static final String SET_PROGRESS = "com.valles.rssreader.PROGRESS";
	public static final String END_LOAD = "com.valles.rssreader.END";
	static String TAG = "LoaderIntentService";

	public LoaderIntentService() {
		super(TAG);;
	}

	protected void onHandleIntent(Intent intent) {
		
		
		final URL url;
		int progress = intent.getIntExtra("progress", 0);
		int rprogress = 0;
		
		try {	
			url = new URL("http://www.meneame.net/rss2.php?meta=0");
			RssFeed feed = RssReader.read(url);
			ArrayList<RssItem> rssItems = feed.getRssItems();
		
			SendBCI(START_LOAD,rssItems.size(), 0);
			
			for(RssItem rssItem : rssItems) {
				if(CheckItem(rssItem.getPubDate().toGMTString())){
					
					ContentValues values = new ContentValues();
			        values.put(FeedsTable.TITLE, rssItem.getTitle());
			        values.put(FeedsTable.PUB_DATE, rssItem.getPubDate().toGMTString());
			        values.put(FeedsTable.DESCRIPTION, rssItem.getDescription());
			        values.put(FeedsTable.CONTENT, rssItem.getContent());
			        values.put(FeedsTable.AUTOR, "Francho Joven");
			        values.put(FeedsTable.CATEGORY, "Programacion");
			        values.put(FeedsTable.URL, rssItem.getLink());
			        
			        final SQLiteDatabase WDB = helper.getWritableDatabase();
			        WDB.insert(FeedsTable.TABLE_NAME, null, values );
			        WDB.close();
			        rprogress++;
				}
		        progress++;		        
		        SendBCI(SET_PROGRESS, 0, progress);
			}
			SendBCI(END_LOAD, 0, rprogress);
			
		} catch (Exception e) {
			Log.d(TAG,e + "");
		}
	}
	
	public void onCreate() {
		super.onCreate();
	}

	public void onDestroy() {
		super.onDestroy();
	}
	
	public void SendBCI(String MSG,int set_max, int progress){
		
		Intent BroadCastIntent = new Intent();
		BroadCastIntent.setAction(MSG);
		BroadCastIntent.putExtra("set_max", set_max);
		BroadCastIntent.putExtra("progress", progress);
		sendBroadcast(BroadCastIntent);
	}
	
	public boolean CheckItem(String pub_date){
		final SQLiteDatabase RDB = helper.getReadableDatabase();
		Cursor cursor = null;
		String sql = "SELECT " + FeedsTable.PUB_DATE + " FROM "+ FeedsTable.TABLE_NAME + " WHERE " + FeedsTable.PUB_DATE + "=" + "'" + pub_date + "'";
		cursor = RDB.rawQuery(sql, null);
		Log.e(TAG,""+cursor.getCount());
		Log.e(TAG,pub_date);
		
		if(cursor.moveToFirst()){
			RDB.close();
			return false;}
		else{
			RDB.close();
			return true;}
	}

}
