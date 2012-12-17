package cat.foixench.apps.lectorss.db;

import cat.foixench.apps.lectorss.db.RssContract.FeedsTable;
import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.text.TextUtils;

public class LectoRSSContentProvider extends ContentProvider {

	private RssDbHelper rssDbHelper;
	
	private static final int TYPE_FEEDS_COLLECTION = 1;
	private static final int TYPE_FEEDS_ITEM = 2;
	
	private static final UriMatcher sUriMatcher;
	
	static {
		sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
		
		sUriMatcher.addURI(RssContract.AUTHORITY, RssContract.FEEDS_PATH, TYPE_FEEDS_COLLECTION);
		sUriMatcher.addURI(RssContract.AUTHORITY, RssContract.FEEDS_PATH + "/#", TYPE_FEEDS_ITEM);
	}
	
	@Override
	public boolean onCreate() {
		this.rssDbHelper = new RssDbHelper (getContext ());
		return true;
	}
	

	@Override
	public String getType(Uri uri) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		// TODO Auto-generated method stub
		return 0;
	}



	@Override
	public Uri insert(Uri uri, ContentValues values) {
		
		SQLiteDatabase db = rssDbHelper.getWritableDatabase();
		
		long newId = db.insert (FeedsTable.TABLE_NAME, null, values);
		
		return (FeedsTable.getUri (newId));
		
		

	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		
		Cursor cursor = null;
		
		// recuperamos la base de datos
		SQLiteDatabase db = rssDbHelper.getReadableDatabase();
		
		// verificamos el tipo de consulta
		switch (sUriMatcher.match (uri)) {
			case  TYPE_FEEDS_COLLECTION :
				// lanzamos la consulta
				cursor = db.query(FeedsTable.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
		
				break;
			case TYPE_FEEDS_ITEM :
				String id = uri.getLastPathSegment ();
				
				// a–adimos el id a la seleccion de la consulta
				if (TextUtils.isEmpty (selection)){
					selection = FeedsTable._ID + " = " + id;
				} else {
					selection = " (" + selection + ") AND " + FeedsTable._ID + " = " + id; 
				}
				cursor = db.query (FeedsTable.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
				
				break;
				
			default :
				cursor = null;
				break;
		}
		
		return cursor;

				
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		// TODO Auto-generated method stub
		return 0;
	}

}
