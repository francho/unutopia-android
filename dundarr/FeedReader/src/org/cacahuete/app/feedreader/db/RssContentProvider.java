package org.cacahuete.app.feedreader.db;

import org.cacahuete.app.feedreader.db.RssContract.ArticlesTable;
import org.cacahuete.app.feedreader.db.RssContract.FeedsTable;
import org.cacahuete.app.feedreader.db.RssDbHelper;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

public class RssContentProvider extends ContentProvider {

	private RssDbHelper mDbHelper;
	private static final String TAG = "RSS content provider";
	private static final UriMatcher sUriMatcher;

	private static final int TYPE_ARTICLES_COLLECTION = 1;
	private static final int TYPE_ARTICLES_ITEM = 2;
	private static final int TYPE_FEEDS_COLLECTION = 3;
	private static final int TYPE_FEEDS_ITEM = 4;
	
	static {
		sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
		
		sUriMatcher.addURI(RssContract.AUTHORITY, "articles", TYPE_ARTICLES_COLLECTION);
		sUriMatcher.addURI(RssContract.AUTHORITY, "articles/#", TYPE_ARTICLES_ITEM);
		sUriMatcher.addURI(RssContract.AUTHORITY, "feeds", TYPE_FEEDS_COLLECTION);
		sUriMatcher.addURI(RssContract.AUTHORITY, "feeds/#", TYPE_FEEDS_ITEM);
	}
	
	
	@Override
	public boolean onCreate() {
		this.mDbHelper = new RssDbHelper(getContext());
		Log.d(TAG,"Creado content provider y enlace a RssDbHelper");
		return true;
	}
	
	@Override
	public String getType(Uri uri) {
		switch(sUriMatcher.match(uri)) {
		case TYPE_ARTICLES_COLLECTION:
			return "android.cursor.dir/vnd.org.cacahuete.app.feedreader.rsscontentprovider.articles";
		case TYPE_ARTICLES_ITEM:
			return "android.cursor.item/vnd.org.cacahuete.app.feedreader.rsscontentprovider.articles";
		case TYPE_FEEDS_COLLECTION:
			return "android.cursor.dir/vnd.org.cacahuete.app.feedreader.rsscontentprovider.feeds";
		case TYPE_FEEDS_ITEM:
			return "android.cursor.item/vnd.org.cacahuete.app.feedreader.rsscontentprovider.feeds";
		default:
			return null;
		}
	}
	
	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		// TODO Auto-generated method stub
		final SQLiteDatabase db = mDbHelper.getWritableDatabase();
		String whereClause="";
		Long id;
		String last_segment = uri.getLastPathSegment();
		if (esId(last_segment)==true) {
			id=Long.parseLong(last_segment);
			whereClause = ArticlesTable._ID + "==" + id;
		}
		
		String whereArgs[]=null;
		int num=db.delete(ArticlesTable.TABLE_NAME, whereClause, whereArgs);
		return num;		
	}

	

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		int uriType = sUriMatcher.match(uri);
		if( uriType != TYPE_ARTICLES_COLLECTION) {
			return null;
		}
		
		final SQLiteDatabase db = mDbHelper.getWritableDatabase();
		long id = db.insert(ArticlesTable.TABLE_NAME, null, values);
		
		Uri newUri = ArticlesTable.getUri(id);
		
		return newUri;
	}


	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		Long id=null;
		Log.d(TAG,"Empezando query con uri "+uri.toString()	);
		try {
			SQLiteDatabase db = mDbHelper.getReadableDatabase();
			if (esId(uri.getLastPathSegment())==true) {
				id=Long.parseLong(uri.getLastPathSegment());
			}
		
		
		switch(sUriMatcher.match(uri)) {
		case TYPE_ARTICLES_ITEM:
			Log.d(TAG,"estamos en articles item");
			
			if(selection==null) { selection = ""; } 
			selection += (!TextUtils.isEmpty(selection)) ? " AND" : "";
			selection += ArticlesTable._ID + "=" + id.toString();
		case TYPE_ARTICLES_COLLECTION:
			Log.d(TAG,"estamos en articles collection");
			String table = ArticlesTable.TABLE_NAME;
			String groupBy = null;
			String having = null;
			Log.d(TAG,"selection es "+selection);
			Cursor cursor = db.query(table, projection, selection, selectionArgs, groupBy, having, sortOrder);
			Log.d(TAG,"Devolviendo cursor con "+cursor.getCount()+" elementos");
			return cursor;
		case TYPE_FEEDS_ITEM:
			Log.d(TAG,"estamos en feeds item");
			
			if(selection==null) { selection = ""; } 
			selection += (!TextUtils.isEmpty(selection)) ? " AND" : "";
			selection += FeedsTable._ID + "==" + id.toString();
		case TYPE_FEEDS_COLLECTION:
			Log.d(TAG,"estamos en feeds collection");
			String table2 = FeedsTable.TABLE_NAME;
			String groupBy2 = null;
			String having2 = null;
			Cursor cursor2 = db.query(table2, projection, selection, selectionArgs, groupBy2, having2, sortOrder);
			Log.d(TAG,"cursor tiene "+cursor2.getColumnCount());
			return cursor2;
		default:
			Log.d(TAG,"devolviendo null");
			return null;
		}
		}
		catch (Exception e) {
			Log.d(TAG, "exception en coger bd");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
	/*
	 * Si fragment es un numero devuelve true, si no false
	 */
	private boolean esId(String fragment) {
		
		try {
			
			Long.parseLong(fragment);
		}
		catch (Exception e) {return false;}
		return true;
	}
	

}
