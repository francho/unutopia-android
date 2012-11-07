package carloscsanchezperez.gmail.sixpackon;


import carloscsanchezperez.gmail.sixpackon.MembersContract.FeedsTable;
//import carloscsanchezperez.gmail.sixpackon.DummyFeeds;
//-------------------------------------------------------------------------

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {
	
	private static final int DATABASE_VERSION = 2;

	public DbHelper(Context context) {
		super(context, MembersContract.DB_NAME, null, DATABASE_VERSION );
	}
	   // title + link + pubDate + description + content

	@Override
	public void onCreate(SQLiteDatabase db) {
		createTableFeeds(db);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		createTableFeeds(db);
	}
	
	private void createTableFeeds(SQLiteDatabase db) {
		db.execSQL("DROP TABLE IF EXISTS " + FeedsTable.TABLE_NAME);
		db.execSQL("CREATE TABLE " + FeedsTable.TABLE_NAME + "("
					+ FeedsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
					+ FeedsTable.TITLE + " STRING,"
					+ FeedsTable.PUBDATE + " LONG,"
					+ FeedsTable.LINK + " STRING,"
					+ FeedsTable.DESCRIPTION + " TEXT,"
					+ FeedsTable.CONTENT + " TEXT"
					+")"
				);
		
		DummyFeeds.insertDummyFeeds(db);
	}
	
}
