package com.valles.rssreader.db;

import com.valles.rssreader.db.RssContract.FeedsTable;
import com.valles.rssreader.db.RssContract.SourceTable;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class RssDbHelper extends SQLiteOpenHelper {
	
	private static final int DB_VERSION = 2;
	
	private static final String SQLFeedTable = 
			"CREATE TABLE " + FeedsTable.TABLE_NAME + " ( " + FeedsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
			 + FeedsTable.TITLE + " TEXT, " + FeedsTable.PUB_DATE + " INTEGER, " 
			 + FeedsTable.IMAGE + " INTEGER, " + FeedsTable.DESCRIPTION + " TEXT, " 
			 + FeedsTable.CONTENT + " TEXT, " + FeedsTable.AUTOR + " TEXT, " 
			 + FeedsTable.CATEGORY + " TEXT, " + FeedsTable.URL + " TEXT, "
			 + FeedsTable.SOURCE + "TEXT)";
	
	private static final String SQLSourceTable = 
			"CREATE TABLE " + SourceTable.TABLE_NAME + " ( " + SourceTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
			 + SourceTable.NAME + " TEXT, " + SourceTable.IMAGE + " INTEGER, " + SourceTable.URL + " TEXT)";
			 
	public RssDbHelper(Context context) {
		super(context, RssContract.DB_NAME, null, DB_VERSION);
	}
	
	public void onCreate(SQLiteDatabase db) {
		CreateTables(db);
	}
	
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		//Implementar codigo para volcado de datos entre la antigua y la nueva V. de la DB.		
		db.execSQL("DROP TABLE IF EXISTS " + FeedsTable.TABLE_NAME );
		CreateTables(db);
	}
	
	public void CreateTables(SQLiteDatabase db){
		db.execSQL(SQLFeedTable);
		db.execSQL(SQLSourceTable);
		
	}

}
