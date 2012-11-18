package whoscallme.db;

import whoscallme.db.CallsConstant.CallsTable;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CallsHelper extends SQLiteOpenHelper {
	
	private static final int DATABASE_VERSION = 1;

	public CallsHelper(Context context) {
		super(context, CallsConstant.DB_NAME, null, DATABASE_VERSION );
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
