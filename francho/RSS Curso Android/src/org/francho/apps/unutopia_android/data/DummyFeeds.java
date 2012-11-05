package org.francho.apps.unutopia_android.data;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.text.format.Time;

public class DummyFeeds {
	public static void insertDummyFeeds(SQLiteDatabase db) {
		addFeed(db, "Art’culo 1", "2012-10-13T16:00:00.000Z");
		addFeed(db, "Otro art’culo", "2012-10-13T16:00:00.000Z");
		addFeed(db, "M‡s de lo mismo", "2012-10-17T15:00:00.000Z");
		addFeed(db, "Otro", "2012-10-13T10:30:00.000Z");
	}
	
	public static void addFeed(SQLiteDatabase db, String title, String date) {
		ContentValues values = new ContentValues();
		
		values.put(FeedContract.Articles.TITLE, title);
		
		Long millis = parseDate(date);
		values.put(FeedContract.Articles.PUB_DATE, millis);

		db.insert(FeedContract.Articles.TABLE_NAME, null, values);
	}

	private static Long parseDate(String date) {
		Time time = new  Time();
		time.parse3339(date);
		Long millis = time.normalize(false);
		return millis;
	}
}
