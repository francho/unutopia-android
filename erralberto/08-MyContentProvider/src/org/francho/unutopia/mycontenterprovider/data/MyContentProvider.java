package org.francho.unutopia.mycontenterprovider.data;

import org.francho.unutopia.mycontenterprovider.data.MembersContract.UsersTable;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.text.TextUtils;

public class MyContentProvider extends ContentProvider {

	private MyDbHelper mDbHelper;

	private static final UriMatcher sUriMatcher;

	private static final int TYPE_USERS_COLLECTION = 1;
	private static final int TYPE_USERS_ITEM = 2;
	
	static {
		sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
		
		sUriMatcher.addURI(MembersContract.AUTHORITY, "users", TYPE_USERS_COLLECTION);
		sUriMatcher.addURI(MembersContract.AUTHORITY, "users/#", TYPE_USERS_ITEM);
	}
	
	
	@Override
	public boolean onCreate() {
		mDbHelper = new MyDbHelper(getContext());
		return true;
	}
	
	@Override
	public String getType(Uri uri) {
		switch(sUriMatcher.match(uri)) {
		case TYPE_USERS_COLLECTION:
			return "android.cursor.dir/vnd.org.francho.unutopia.mycontentprovider.users";
		case TYPE_USERS_ITEM:
			return "android.cursor.item/vnd.org.francho.unutopia.mycontentprovider.users";
		default:
			return null;
		}
	}
	
	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		int count=0;
		final SQLiteDatabase db = mDbHelper.getWritableDatabase();
		switch(sUriMatcher.match(uri)) {
	      case TYPE_USERS_COLLECTION:
	        count = db.delete(UsersTable.TABLE_NAME, selection, selectionArgs);
	        break;
	      case TYPE_USERS_ITEM:
	        String id = uri.getPathSegments().get(1);
	        count = db.delete(UsersTable.TABLE_NAME, UsersTable._ID
	        + " = "
	        + id
	        + (!TextUtils.isEmpty(selection) ? " AND (" + selection
	        + ')' : ""), selectionArgs);
	        break;
	      default:
	        throw new IllegalArgumentException("Unknown URI " + uri);
	    }
	    getContext().getContentResolver().notifyChange(uri, null);
	    return count;

	}
	
	@Override
	  public int update(Uri uri, ContentValues values, String selection,String[] selectionArgs) {
		 int count = 0;
		    final SQLiteDatabase db = mDbHelper.getWritableDatabase();
		    switch(sUriMatcher.match(uri)) {
		      case TYPE_USERS_COLLECTION:
		        count = db.update(UsersTable.TABLE_NAME, values, selection,selectionArgs);
		        break;
		      case TYPE_USERS_ITEM:
		    	  String id = uri.getPathSegments().get(1);
		    	  count = db.update(UsersTable.TABLE_NAME, values, UsersTable._ID
		    		        + " = "
		    		        + id
		    		        + (!TextUtils.isEmpty(selection) ? " AND (" + selection
		    		        + ')' : ""), selectionArgs);
		    	  break;
		    	  default:
		    	  throw new IllegalArgumentException("Unknown URI " + uri);
		    }
		    getContext().getContentResolver().notifyChange(uri, null);
		    return count;
	  }
	 

	

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		int uriType = sUriMatcher.match(uri);
		if( uriType != TYPE_USERS_COLLECTION) {
			return null;
		}
		
		final SQLiteDatabase db = mDbHelper.getWritableDatabase();
		long id = db.insert(UsersTable.TABLE_NAME, null, values);
		
		Uri newUri = UsersTable.getUri(id);
		
		return newUri;
	}


	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		
		
		final SQLiteDatabase db = mDbHelper.getReadableDatabase();
		
		switch(sUriMatcher.match(uri)) {
		case TYPE_USERS_ITEM:
			String id = uri.getLastPathSegment();
			
			if(!TextUtils.isEmpty(selection)) {
				selection += " AND";
			} else {
				selection = "";
			}
			selection += UsersTable._ID + "==" + id;
			
			String table = UsersTable.TABLE_NAME;
			String groupBy = null;
			String having = null;
			Cursor cursor = db.query(table, projection, selection, selectionArgs, groupBy, having, sortOrder);
			return cursor;
		default:
			return null;
		}		
	}
}
