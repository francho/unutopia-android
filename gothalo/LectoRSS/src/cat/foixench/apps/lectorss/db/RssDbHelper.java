package cat.foixench.apps.lectorss.db;

import cat.foixench.apps.lectorss.db.RssContract.FeedsTable;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class RssDbHelper extends SQLiteOpenHelper {
	
	/**
	 * Versi—n de la base de datos. en los upgrades se comparar‡ la actual con la version instalada para ver
	 * si hay que hacer algun cambio en la estructura de datos.
	 */
	private static final int DATABASE_VERSION = 1;

	public RssDbHelper (Context context) {
		super (context, RssContract.DB_NAME, null, DATABASE_VERSION);
		
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		// en el momento de la creaci—n de la base de datos, generamos una nueva base de datos de SQLite.
		String strQuery = "CREATE TABLE " + FeedsTable.TABLE_NAME + " "
				        + "(" + FeedsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT "
				        + "," + FeedsTable.COLUMN_TITLE + " TEXT " 
				        + "," + FeedsTable.COLUMN_DESCRIPTION + " TEXT "
				        + "," + FeedsTable.COLUMN_LINK + " TEXT "
				        + "," + FeedsTable.COLUMN_PUB_DATE + " DATE "
				        + "," + FeedsTable.COLUMN_CONTENT + " TEXT "
				        + ")";
		
		db.execSQL(strQuery);
		
		
		
		// insertamos los valores iniciales de la base de datos, para tener
		String [] queryArgs = new String [] {"Titulo 01", "Una descripci—n del trasto.", "http://www.gothalo.net", "2012-11-01 00:00:00.000", "contenido"};
		strQuery = "INSERT INTO " + FeedsTable.TABLE_NAME 
				 + " (" 
				 + FeedsTable.COLUMN_TITLE + ", " 
				 + FeedsTable.COLUMN_DESCRIPTION + ", "
				 + FeedsTable.COLUMN_LINK + ", "
				 + FeedsTable.COLUMN_PUB_DATE + ", "
				 + FeedsTable.COLUMN_CONTENT 
				 + ") "
				 + "VALUES (?, ?, ?, ?, ?)";
		
		db.execSQL(strQuery, queryArgs);
		
		//	db.close ();

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		

	}

}
