package com.webo.FeedReader.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DB extends SQLiteOpenHelper {

	private static final int DATABASE_VERSION = 1;

	public DB(Context context) {
		super(context, "article_list", null, DATABASE_VERSION );
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE " + "article_list" + "("
					+ "id" + " INTEGER PRIMARY KEY AUTOINCREMENT,"
					+ "title" + " STRING,"
					+ "author" + " STRING,"
					+ "date" + " STRING"
					+")"
				);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

}
