package org.cacahuete.app.registrollamadas.db;

import org.cacahuete.app.registrollamadas.db.CallslogContract.CallsTable;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CallslogHelper extends SQLiteOpenHelper {
	
	private static final int DATABASE_VERSION = 1;

	public CallslogHelper(Context context) {
		super(context, CallslogContract.DB_NAME, null, DATABASE_VERSION );
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE " + CallsTable.TABLE_NAME + "("
					+ CallsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
					+ CallsTable.TIME + " LONG,"
					+ CallsTable.NUMBER + " STRING"
					+ ")"
				);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		//
		
	}

}
