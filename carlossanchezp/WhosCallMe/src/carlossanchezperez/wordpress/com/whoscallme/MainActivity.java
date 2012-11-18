package carlossanchezperez.wordpress.com.whoscallme;



import whoscallme.db.CallsConstant;
import whoscallme.db.CallsConstant.CallsTable;

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
import android.util.Log;
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
		Log.v(CallsConstant.LOGTAG, "Main Oncreate");
		String[] from = new String[] { CallsTable.NUMBER, CallsTable.TIME };
		int[] to = new int[] { android.R.id.text1, android.R.id.text2 };
		
		adapter = new SimpleCursorAdapter(this, layout, cursor, from, to, SimpleCursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
		adapter.setViewBinder(this);
		
		setListAdapter(adapter);
		Log.v(CallsConstant.LOGTAG, "Main Oncreate 2");
		getLoaderManager().initLoader(0, null, this);
		Log.v(CallsConstant.LOGTAG, "Main Oncreate 3");
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
		Log.v(CallsConstant.LOGTAG, "Main onCreateLoader");
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
		Log.v(CallsConstant.LOGTAG, "Main setViewValue");
		if(view.getId() == android.R.id.text2) {
			final long timestamp = cursor.getLong(columnIndex);
			Log.v(CallsConstant.LOGTAG, "Main setViewValue getId == text2");
			CharSequence dateStr = DateUtils.getRelativeTimeSpanString(timestamp);
		
			((TextView) view).setText(dateStr);
			return true;
		}
		
		return false;
	}
}
