package cat.foixench.apps.callsregister.data;

import cat.foixench.apps.callsregister.data.CallsContract.IncommingTable;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CallsDBHelper extends SQLiteOpenHelper {
	
	private static final int DATABASE_VERSION = 1;

	public CallsDBHelper (Context context) {
		super (context, CallsContract.DB_NAME,null, DATABASE_VERSION);
		
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// creamos la tabla Incoming en la base de datos
		String query = "CREATE TABLE " + IncommingTable.TABLE_NAME + " (" 
					 + IncommingTable._ID +  " INTEGER PRIMARY KEY AUTOINCREMENT, "
					 + IncommingTable.COLUMN_PHONE + " TEXT, "
					 + IncommingTable.COLUMN_CALL_DATE + " DATE "
				     + ")";
		
		db.execSQL(query);
		

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// actualizaciones de base de datos.

	}

}
