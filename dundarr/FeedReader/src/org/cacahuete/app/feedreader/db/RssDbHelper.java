package org.cacahuete.app.feedreader.db;



import org.cacahuete.app.feedreader.db.RssContract.FeedTable;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.format.Time;

public class RssDbHelper extends SQLiteOpenHelper {
	
	private static final int DATABASE_VERSION = 1;

	public RssDbHelper(Context context) {
		super(context, RssContract.DB_NAME, null, DATABASE_VERSION );
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE " + FeedTable.TABLE_NAME + "("
					+ FeedTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
					+ FeedTable.TITLE + " STRING,"
					+ FeedTable.LINK + " STRING,"
					+ FeedTable.PUBDATE + " DATE,"
					+ FeedTable.DESCRIPTION + " STRING,"
					+ FeedTable.CONTENT + " STRING"
					+")"
				);
		this.rellenarDb(db);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}
	
	public void rellenarDb(SQLiteDatabase db) {
		String query="";
		
		Time fecha = new Time ();
		fecha.setToNow();
		// insertamos los valores iniciales de la base de datos, para tener
		String [] queryArgs = new String [] {"Primer articulo"
				 						   , "http://link-bueno.com"
				 						   , Long.valueOf (fecha.toMillis (true)).toString ()
				 						   , "articulillo de los buenos."
				 						   ,"Esto es el texto del articulo"
											};
		
		query = "INSERT INTO " + FeedTable.TABLE_NAME 
				 + " (" 
				 + FeedTable.TITLE + ", " 
				  + FeedTable.LINK + ", "
				 + FeedTable.PUBDATE + ", "
				 + FeedTable.DESCRIPTION + ", "
				 + FeedTable.CONTENT
				 + ") "
				 + "VALUES (?, ?, ?, ?, ?)";
		
		db.execSQL(query, queryArgs);
		
		
		queryArgs = new String [] {"Segundo articulo"
				   , "http://link-bueno2.com"
				   , Long.valueOf (fecha.toMillis (true)).toString ()
				   , "articulillo de los buenos2."
				   ,"Esto es el texto del articulo2"
					};

		query = "INSERT INTO " + FeedTable.TABLE_NAME 
						+ " (" 
						+ FeedTable.TITLE + ", " 
						+ FeedTable.LINK + ", "
						+ FeedTable.PUBDATE + ", "
						+ FeedTable.DESCRIPTION + ", "
						+ FeedTable.CONTENT
						+ ") "
						+ "VALUES (?, ?, ?, ?, ?)";

		db.execSQL(query, queryArgs);
		
	}
		
	public void vaciarDb(SQLiteDatabase db) {
		String query="DROP TABLE "+FeedTable.TABLE_NAME;
		
		db.execSQL(query);
		
	}
		
	
	

}
