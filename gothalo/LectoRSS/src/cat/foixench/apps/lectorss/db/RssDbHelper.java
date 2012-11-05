package cat.foixench.apps.lectorss.db;

import cat.foixench.apps.lectorss.db.RssContract.FeedsTable;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.format.Time;
import android.util.Log;

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
				        + "," + FeedsTable.COLUMN_AUTHOR + " TEXT "
				        + "," + FeedsTable.COLUMN_LINK + " TEXT "
				        + "," + FeedsTable.COLUMN_PUB_DATE + " DATE "
				        + "," + FeedsTable.COLUMN_DESCRIPTION + " TEXT "
				        + ")";
		
		db.execSQL(strQuery);
		
		
		
		Time ahora = new Time ();
		ahora.setToNow();
		
		// insertamos los valores iniciales de la base de datos, para tener
		String [] queryArgs = new String [] {"Titulo 01"
				 						   , "Perico de los palotes"
				 						   , "http://www.gothalo.net"
				 						   , Long.valueOf (ahora.toMillis (true)).toString ()
				 						   , "contenido del post. aqui se supone que deberia ir un titulo muy laaaaaaaaaaaaaaaargo, pero no tengo muchas ganas de hacer cositas."};
		
		strQuery = "INSERT INTO " + FeedsTable.TABLE_NAME 
				 + " (" 
				 + FeedsTable.COLUMN_TITLE + ", " 
				 + FeedsTable.COLUMN_AUTHOR + ", "
				 + FeedsTable.COLUMN_LINK + ", "
				 + FeedsTable.COLUMN_PUB_DATE + ", "
				 + FeedsTable.COLUMN_DESCRIPTION
				 + ") "
				 + "VALUES (?, ?, ?, ?, ?)";
		
		db.execSQL(strQuery, queryArgs);
		
		// fijamos la fecha del articulo en 01/11/2012 15:30:00)
		ahora.set (0, 30, 15, 1, 10, 2012);
		
		
		Log.d ("DATABASE", ahora.toString ());
		
		queryArgs = new String [] {"Titulo 02"
								 , "John Doe"
								 , "http://www.foo.net"
								 , Long.valueOf (ahora.toMillis (true)).toString ()
								 , "otro post. este para ver como van algunas de las cositas que hemos hecho."};
		
		strQuery = "INSERT INTO " + FeedsTable.TABLE_NAME 
				 + " (" 
				 + FeedsTable.COLUMN_TITLE + ", " 
				 + FeedsTable.COLUMN_AUTHOR + ", "
				 + FeedsTable.COLUMN_LINK + ", "
				 + FeedsTable.COLUMN_PUB_DATE + ", "
				 + FeedsTable.COLUMN_DESCRIPTION
				 + ") "
				 + "VALUES (?, ?, ?, ?, ?)";
		
		db.execSQL(strQuery, queryArgs);
		
		//	db.close ();

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		

	}

}
