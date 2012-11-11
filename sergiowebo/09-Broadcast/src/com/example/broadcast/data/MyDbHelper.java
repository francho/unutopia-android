package com.example.broadcast.data;

import com.example.broadcast.data.CallsContract.UsersTable;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDbHelper extends SQLiteOpenHelper {
	
	private static final int DATABASE_VERSION = 1;

	public MyDbHelper(Context context) {
		super(context, CallsContract.DB_NAME, null, DATABASE_VERSION );
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE " + UsersTable.TABLE_NAME + "("
					+ UsersTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
					+ UsersTable.NUMBER + " STRING,"
					//+ UsersTable.EMAIL + " STRING"
					+")"
				);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

}
