package com.merybere.app.callregistry.db;


import com.merybere.app.callregistry.db.MembersContract.CallsRecordTable;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDbHelper extends SQLiteOpenHelper {

	
	private static final int DATABASE_VERSION = 1;

	// Constructor público que recibe como parámetro el contexto
	// Con esto ya pude acceder a las carpetas
	public MyDbHelper(Context context) {
		// El tercer parámetro es null, un gestor para los pointers
		// El útimo parámetro es la versión. Por defecto, una constante
		super(context, MembersContract.DB_NAME, null, DATABASE_VERSION);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		// En este método se recibe la base de datos
		db.execSQL("CREATE TABLE " + CallsRecordTable.TABLE_NAME + "("
					+ CallsRecordTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
					+ CallsRecordTable.DATETIME + " LONG,"
					+ CallsRecordTable.PHONENUMBER + " STRING"
					+ ")"
					);
		

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Este método sirve para actualizar base de datos

	}

}
