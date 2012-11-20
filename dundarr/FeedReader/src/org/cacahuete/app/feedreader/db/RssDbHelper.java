package org.cacahuete.app.feedreader.db;


import org.cacahuete.app.feedreader.db.RssContract.ArticlesTable;
import org.cacahuete.app.feedreader.db.RssContract.FeedsTable;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.format.Time;
import android.util.Log;

public class RssDbHelper extends SQLiteOpenHelper {
	
	private static final int DATABASE_VERSION = 1;
	private String TAG="DBHELPER";

	public RssDbHelper(Context context) {
		super(context, RssContract.DB_NAME, null, DATABASE_VERSION );
		Log.d(TAG,"Constructor de RssDbHelper");
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		Log.d(TAG,"Creando database");
		this.vaciarDb(db);
		db.execSQL("CREATE TABLE " + ArticlesTable.TABLE_NAME + "("
					+ ArticlesTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
					+ ArticlesTable.TITLE + " STRING,"
					+ ArticlesTable.LINK + " STRING,"
					//+ ArticlesTable.PUBDATE + " DATE,"
					+ ArticlesTable.DESCRIPTION + " STRING,"
					+ ArticlesTable.CONTENT + " STRING"
					+")"
				);
		//this.rellenarDb(db);
		db.execSQL("CREATE TABLE " + FeedsTable.TABLE_NAME + "("
				+ FeedsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ FeedsTable.URL + " STRING,"
				+ FeedsTable.LASTCHECKDATE + " STRING,"
				+ FeedsTable.NAME + " STRING,"
				+ FeedsTable.DESCRIPTION + " STRING"
				
				+")"
			);
	this.rellenarDb2(db);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		Log.d(TAG,"Upgrade de RssDbHelper");
		this.onCreate(db);

	}
	
//	public void rellenarDb(SQLiteDatabase db) {
//		String query="";
//		
//		Time fecha = new Time ();
//		fecha.setToNow();
//		// insertamos los valores iniciales de la base de datos, para tener
//		String [] queryArgs = new String [] {"Primer articulo"
//				 						   , "http://link-bueno.com"
//				 						   , Long.valueOf (fecha.toMillis (true)).toString ()
//				 						   , "articulillo de los buenos."
//				 						   ,"Esto es el texto del articulo"
//											};
//		
//		query = "INSERT INTO " + ArticlesTable.TABLE_NAME 
//				 + " (" 
//				 + ArticlesTable.TITLE + ", " 
//				  + ArticlesTable.LINK + ", "
//				 + ArticlesTable.PUBDATE + ", "
//				 + ArticlesTable.DESCRIPTION + ", "
//				 + ArticlesTable.CONTENT
//				 + ") "
//				 + "VALUES (?, ?, ?, ?, ?)";
//		
//		db.execSQL(query, queryArgs);
//		
//		
//		queryArgs = new String [] {"Segundo articulo"
//				   , "http://link-bueno2.com"
//				   , Long.valueOf (fecha.toMillis (true)).toString ()
//				   , "articulillo de los buenos2."
//				   ,"Esto es el texto del articulo2"
//					};
//
//		query = "INSERT INTO " + ArticlesTable.TABLE_NAME 
//						+ " (" 
//						+ ArticlesTable.TITLE + ", " 
//						+ ArticlesTable.LINK + ", "
//						+ ArticlesTable.PUBDATE + ", "
//						+ ArticlesTable.DESCRIPTION + ", "
//						+ ArticlesTable.CONTENT
//						+ ") "
//						+ "VALUES (?, ?, ?, ?, ?)";
//
//		db.execSQL(query, queryArgs);
//		
//	}
//	
	
	public void rellenarDb2(SQLiteDatabase db) {
		String query="";
		
		Time fecha = new Time ();
		fecha.setToNow();
		
		
			
		
		// insertamos los valores iniciales de la base de datos, para tener
		String [] queryArgs = new String [] {"http://www.recetasdemama.es/feed/"
				 						   , Long.valueOf (fecha.toMillis (true)).toString ()
				 						   , "Recetas de Mama."
				 						  , "Noticias de cocina."
											};
		
		query = "INSERT INTO " + FeedsTable.TABLE_NAME 
				 + " (" 
				 + FeedsTable.URL + ", " 
				  + FeedsTable.LASTCHECKDATE + ", "
				 + FeedsTable.NAME + ", "
				 + FeedsTable.DESCRIPTION 
				  + ") "
				 + "VALUES (?, ?, ?, ?)";
		
		db.execSQL(query, queryArgs);
		
		
		String [] queryArgs2 = new String [] {"http://www.gameover.es/feed/"
				   , Long.valueOf (fecha.toMillis (true)).toString ()
				   , "GameOver"
				  , "Noticias de videojuegos"
					};

		query = "INSERT INTO " + FeedsTable.TABLE_NAME 
				 + " (" 
				 + FeedsTable.URL + ", " 
				  + FeedsTable.LASTCHECKDATE + ", "
				 + FeedsTable.NAME + ", "
				 + FeedsTable.DESCRIPTION 
				  + ") "
				 + "VALUES (?, ?, ?, ?)";

		db.execSQL(query, queryArgs2);
		
	}
	public void vaciarDb(SQLiteDatabase db) {
		String query="DROP TABLE IF EXISTS "+ArticlesTable.TABLE_NAME;
		db.execSQL(query);
		String query2="DROP TABLE IF EXISTS "+FeedsTable.TABLE_NAME;
		db.execSQL(query2);
	}
		
	
	

}
