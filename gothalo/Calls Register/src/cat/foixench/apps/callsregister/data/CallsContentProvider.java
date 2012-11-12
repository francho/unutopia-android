package cat.foixench.apps.callsregister.data;

import cat.foixench.apps.callsregister.data.CallsContract.IncommingTable;
import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.text.TextUtils;

public class CallsContentProvider extends ContentProvider {

	private CallsDBHelper dbHelper;
	
	private static final UriMatcher sUriMatcher;

	private static final int TYPE_CALLS_COLLECTION = 0;
	private static final int TYPE_CALLS_ITEM = 1;
	
	static {
		sUriMatcher = new UriMatcher (UriMatcher.NO_MATCH);
		sUriMatcher.addURI(CallsContract.AUTHORITY, CallsContract.PATH, TYPE_CALLS_COLLECTION);
		sUriMatcher.addURI(CallsContract.AUTHORITY, CallsContract.PATH + "/#", TYPE_CALLS_ITEM);
	}
	
	@Override
	public boolean onCreate() {
		dbHelper = new CallsDBHelper (getContext ());
		return true;
	}

	@Override
	public String getType(Uri uri) {
		switch(sUriMatcher.match(uri)) {
			case TYPE_CALLS_COLLECTION :
				return CallsContract.MIME_CALLS_COLLECTION;
			case TYPE_CALLS_ITEM :
				return CallsContract.MIME_CALLS_ITEM;
			default:
				return null;
		}
	}
	
	@Override
	public Uri insert(Uri uri, ContentValues values) {
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		
		long newId = db.insert(IncommingTable.TABLE_NAME, null, values);
		
		return IncommingTable.getUri (newId);
		
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		
		Cursor results = null;
		boolean executeQuery = false;
		SQLiteDatabase db;
		
		switch (sUriMatcher.match (uri)) {
			case TYPE_CALLS_ITEM :
				String id = uri.getLastPathSegment();
				if (TextUtils.isEmpty (selection)) {
					selection = IncommingTable._ID + " = " + id;
				} else {
					selection = "(" + selection + ") AND " + IncommingTable._ID + " = " + id + ") ";
				}
				executeQuery = true;
				
				break;
			case TYPE_CALLS_COLLECTION : 
				executeQuery = true;
				break;
			default :
				executeQuery = false;
		
		}
		
		if (executeQuery) {
			// se ha pasado la verificacion de tipo de consulta
			db = dbHelper.getReadableDatabase();
			results = db.query (IncommingTable.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder); 
		}
		
		return results;
	}
	
	
	@Override
	public int delete(Uri arg0, String arg1, String[] arg2) {
		// TODO Auto-generated method stub
		return 0;
	}



	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		// TODO Auto-generated method stub
		return 0;
	}

}
