package org.francho.unutopia.ejer_registrollamadas;

import org.francho.unutopia.ejer_registrollamadas.db.CallslogContract.CallsTable;

import android.app.ListActivity;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.provider.CalendarContract;
import android.text.format.DateFormat;
import android.text.format.DateUtils;
import android.text.format.Time;
import android.util.TimeUtils;
import android.view.Menu;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.SimpleCursorAdapter.ViewBinder;
import android.widget.TextView;

public class MainActivity extends ListActivity implements
LoaderCallbacks<Cursor>, ViewBinder  {

	private SimpleCursorAdapter adapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		int layout = android.R.layout.simple_list_item_2;
		Cursor cursor = null;
		String[] from = new String[] { CallsTable.NUMBER, CallsTable.TIME };
		int[] to = new int[] { android.R.id.text1, android.R.id.text2 };
		adapter = new SimpleCursorAdapter(this, layout, cursor, from, to, SimpleCursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
		adapter.setViewBinder(this);
		
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
		Context context = this;
		Uri uri = CallsTable.getUri();
		String[] projection = new String[] { CallsTable._ID, CallsTable.NUMBER, CallsTable.TIME };
		String selection = null;
		String[] selectionArgs = null;
		String shortOrder = null;
		return new CursorLoader(context, uri, projection, selection,
				selectionArgs, shortOrder);
	}

	@Override
	public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
		adapter.changeCursor(cursor);
	}

	@Override
	public void onLoaderReset(Loader<Cursor> loader) {
	}

	@Override
	public boolean setViewValue(View view, Cursor cursor, int columnIndex) {
		if(view.getId() == android.R.id.text2) {
			final long timestamp = cursor.getLong(columnIndex);
			
			CharSequence dateStr = DateUtils.getRelativeTimeSpanString(timestamp);
		
			((TextView) view).setText(dateStr);
			return true;
		}
		
		return false;
	}
}
