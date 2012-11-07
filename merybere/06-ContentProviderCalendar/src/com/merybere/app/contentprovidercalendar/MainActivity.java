package com.merybere.app.contentprovidercalendar;

import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.app.ListActivity;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.view.Menu;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends ListActivity implements LoaderCallbacks<Cursor> {

    private SimpleCursorAdapter adapter;

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        int layout = android.R.layout.simple_list_item_2;
		Cursor cursor = null;	// En blanco para que se encargue el loader de cargarlo
		String[] from = new String[] { CalendarContract.Events.TITLE,
				CalendarContract.Events.DTSTART };
		int[] to = new int[]{ android.R.id.text1, android.R.id.text2 };
		int flags = 0;
		adapter = new SimpleCursorAdapter(this, layout, cursor, from, to, flags );
		
		// Indicar que hay que usar este adaptador
		setListAdapter(adapter);
		
		// Lanzar el loader manager
		// - Id del loader, por si hay más de uno
		// - Parámetros
		// - Loader callbacks
		getLoaderManager().initLoader(0, null, this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    // Un loader se encarga de abrir el helper y cargar el cursor
	@Override
	public Loader<Cursor> onCreateLoader(int id, Bundle args) {
		Context context = this;
		Uri uri = CalendarContract.Events.CONTENT_URI;
		String[] projection = new String[] {
				CalendarContract.Events._ID,
				CalendarContract.Events.TITLE,
				CalendarContract.Events.DTSTART
		};
		String selection = null;
		String[] selectionArgs = null;
		String shortOrder = CalendarContract.Events.DTSTART + " DESC";
		return new CursorLoader(context, uri, projection, selection, selectionArgs, shortOrder);
	}

	
	@Override
	public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
		adapter.swapCursor(cursor);
		
	}

	@Override
	public void onLoaderReset(Loader<Cursor> arg0) {
		// TODO Auto-generated method stub
		
	}
}
