package whoscallme.db;

import whoscallme.db.CallsConstant.CallsTable;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.text.TextUtils;

public class CallsContentProvider extends ContentProvider {

	private CallsHelper mDbHelper;

	private static final UriMatcher sUriMatcher;

	private static final int TYPE_CALLS_COLLECTION = 1;
	private static final int TYPE_CALLS_ITEM = 2;
	
	static {
		sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
		
		sUriMatcher.addURI(CallsConstant.AUTHORITY, "calls", TYPE_CALLS_COLLECTION);
		sUriMatcher.addURI(CallsConstant.AUTHORITY, "calls/#", TYPE_CALLS_ITEM);
	}
	
	@Override
	public boolean onCreate() {
		mDbHelper = new CallsHelper(getContext());
		return true;
	}
	
	@Override
	public String getType(Uri uri) {
		switch(sUriMatcher.match(uri)) {
		case TYPE_CALLS_COLLECTION:
			return "android.cursor.dir/vnd.carlossanchezperez,wordpress.com.mycontentprovider.calls";
		case TYPE_CALLS_ITEM:
			return "android.cursor.item/vnd.carlossanchezperez,wordpress.com.mycontentprovider.calls";
		default:
			return null;
		}
	}
	
	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		int uriType = sUriMatcher.match(uri);
		if( uriType != TYPE_CALLS_COLLECTION) {
			return null;
		}
		
		final SQLiteDatabase db = mDbHelper.getWritableDatabase();
		final long id = db.insert(CallsTable.TABLE_NAME, null, values);
		
		final Uri newUri = CallsTable.getUri(id);
		
		getContext().getContentResolver().notifyChange(uri, null);
		
		return newUri;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		
		final SQLiteDatabase db = mDbHelper.getReadableDatabase();
		
		switch(sUriMatcher.match(uri)) {
		case TYPE_CALLS_ITEM:
			String id = uri.getLastPathSegment();
			if(selection==null) { selection = ""; } 
			selection += (!TextUtils.isEmpty(selection)) ? " AND" : "";
			selection += CallsTable._ID + "==" + id;
		case TYPE_CALLS_COLLECTION:
			String table = CallsTable.TABLE_NAME;
			String groupBy = null;
			String having = null;
			if(sortOrder == null) {
				sortOrder = CallsTable.TIME + " DESC";	
			}
			Cursor cursor = db.query(table, projection, selection, selectionArgs, groupBy, having, sortOrder);
			cursor.setNotificationUri(getContext().getContentResolver(), uri);
			return cursor;
		default:
			return null;
		}
		
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		// TODO Auto-generated method stub
		return 0;
	}

}
