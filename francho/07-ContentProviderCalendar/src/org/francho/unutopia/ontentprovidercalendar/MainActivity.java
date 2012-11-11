package org.francho.unutopia.ontentprovidercalendar;

import android.app.Activity;
import android.app.ListActivity;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.Menu;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends ListActivity implements
		LoaderCallbacks<Cursor> {

	private SimpleCursorAdapter adapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		int layout = android.R.layout.simple_list_item_2;
		Cursor cursor = null;
		String[] from = new String[] { CalendarContract.Events.TITLE,
				CalendarContract.Events.DTSTART };
		int[] to = new int[] { android.R.id.text1, android.R.id.text2 };
		adapter = new SimpleCursorAdapter(this, layout, cursor, from, to, 0);
	
		setListAdapter(adapter);
		
		getLoaderManager().initLoader(0, null, this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	// LoadManager

	@Override
	public Loader<Cursor> onCreateLoader(int id, Bundle args) {
		String shortOrder = CalendarContract.Events.DTSTART + " DESC";
		String[] selectionArgs = null;
		String selection = null;
		String[] projection = new String[] { CalendarContract.Events._ID,
				CalendarContract.Events.TITLE, CalendarContract.Events.DTSTART };
		Uri uri = CalendarContract.Events.CONTENT_URI;
		Context context = this;
		return new CursorLoader(context, uri, projection, selection,
				selectionArgs, shortOrder);
	}

	@Override
	public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
		adapter.swapCursor(cursor);
	}

	@Override
	public void onLoaderReset(Loader<Cursor> arg0) {
		adapter.swapCursor(null);
	}
}
