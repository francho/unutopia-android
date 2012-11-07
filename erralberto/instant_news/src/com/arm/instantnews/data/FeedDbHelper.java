package com.arm.instantnews.data;


import com.arm.instantnews.data.FeedContract.Articles;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class FeedDbHelper extends SQLiteOpenHelper {
	
	private static final int DATABASE_VERSION = 1;

	public FeedDbHelper(Context context) {
		super(context, FeedContract.DB_NAME, null, DATABASE_VERSION );
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		createTableArticles(db);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		createTableArticles(db);
	}
	
	private void createTableArticles(SQLiteDatabase db) {
		db.execSQL("DROP TABLE IF EXISTS " + Articles.TABLE_NAME);
		db.execSQL("CREATE TABLE " + Articles.TABLE_NAME + "("
					+ Articles._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
					+ Articles.TITLE + " STRING,"
					+ Articles.PUB_DATE + " LONG,"
					+ Articles.LINK + " STRING,"
					+ Articles.DESCRIPTION + " TEXT,"
					+ Articles.CONTENT + " TEXT"
					+")"
				);
		
		DummyFeeds.insertDummyFeeds(db);
	}

	

}

