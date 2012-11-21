package org.francho.apps.unutopia_android.data;

import java.util.ArrayList;

import org.francho.apps.unutopia_android.data.FeedContract.Articles;

import android.content.ContentProvider;
import android.content.ContentProviderOperation;
import android.content.ContentProviderResult;
import android.content.ContentValues;
import android.content.OperationApplicationException;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.text.TextUtils;

public class FeedContentProvider extends ContentProvider {

	private FeedDbHelper mDbHelper;

	private static final UriMatcher sUriMatcher;

	private static final int TYPE_FEEDS = 1;
	private static final int TYPE_FEED_ITEM = 2;
	
	static {
		sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
		
		sUriMatcher.addURI(FeedContract.AUTHORITY, "feeds", TYPE_FEEDS);
		sUriMatcher.addURI(FeedContract.AUTHORITY, "feeds/#", TYPE_FEED_ITEM);
	}
	
	
	@Override
	public boolean onCreate() {
		mDbHelper = new FeedDbHelper(getContext());
		return true;
	}
	
	@Override
	public String getType(Uri uri) {
		switch(sUriMatcher.match(uri)) {
		case TYPE_FEEDS:
			return "android.cursor.dir/vnd.org.francho.unutopia.mycontentprovider.users";
		case TYPE_FEED_ITEM:
			return "android.cursor.item/vnd.org.francho.unutopia.mycontentprovider.users";
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
		if( uriType != TYPE_FEEDS) {
			return null;
		}
		
		final SQLiteDatabase db = mDbHelper.getWritableDatabase();
		long id = db.replace(Articles.TABLE_NAME, null, values);
		db.close();
		
		Uri newUri = Articles.getUri(id);
		
		getContext().getContentResolver().notifyChange(uri, null);
		
		return newUri;
	}


	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		
		switch(sUriMatcher.match(uri)) {
		case TYPE_FEED_ITEM:
			String id = uri.getLastPathSegment();
			if(selection==null) { selection = ""; } 
			selection += (!TextUtils.isEmpty(selection)) ? " AND" : "";
			selection += Articles._ID + "==" + id;
		case TYPE_FEEDS:
			String table = Articles.TABLE_NAME;
			String groupBy = null;
			String having = null;
			
			final SQLiteDatabase db = mDbHelper.getReadableDatabase();
			Cursor cursor = db.query(table, projection, selection, selectionArgs, groupBy, having, sortOrder);
			
			return cursor;
		default:
			return null;
		}
		
	}
	
	

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		
		return 0;
	}

	@Override
	public ContentProviderResult[] applyBatch(
			ArrayList<ContentProviderOperation> operations)
			throws OperationApplicationException {
		
		return super.applyBatch(operations);
	}

	
}
