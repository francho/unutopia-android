package com.merybere.app.persistencia;

import com.merybere.app.persistencia.db.MembersContract.UsersTable;
import com.merybere.app.persistencia.db.MyDbHelper;

import android.os.Bundle;
import android.app.ListActivity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.Menu;

public class MainActivity extends ListActivity {

    private SimpleCursorAdapter adapter;

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_main);
        
        // getFilesDir();
        // getCacheDir();
        // getExternalCacheDir();
        // getExternalFilesDir(type);
        
        final MyDbHelper helper = new MyDbHelper(this);
        // Obtener la BD en modo escritura
        final SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(UsersTable.EMAIL, "pepe@super.man");
        values.put(UsersTable.USERNAME, "Pepe");
        
        // Id autoincremental que hemos puesto
        long id = db.insert(UsersTable.TABLE_NAME, null, values);
        db.close();
        
        //
        
        Context context = this;
		int layout = android.R.layout.simple_list_item_2;
		Cursor c = null;
		String[] from = new String[] { UsersTable.USERNAME, UsersTable.EMAIL};
		int[] to = new int[] { android.R.id.text1, android.R.id.text2};
		int flags = SimpleCursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER;
		
		adapter = new SimpleCursorAdapter(context, layout, c, from, to, flags);
		setListAdapter(adapter);
		
    }

    @Override
	protected void onStart() {
		
		super.onStart();
		
		// Al entrar, crea la BD vacía, y la sustituye una vez creada por la que hemos hecho
		adapter.changeCursor(getUsers());
	}

	@Override
	protected void onStop() {
		
		adapter.changeCursor(null);
		super.onStop();
	}

	private Cursor getUsers() {
		MyDbHelper helper = new MyDbHelper(this);
		SQLiteDatabase db = helper.getReadableDatabase();
		
		String table = UsersTable.TABLE_NAME;
		String[] columns = new String[] { UsersTable._ID, UsersTable.USERNAME, UsersTable.EMAIL};
		// la parte del where; usamos el ?, en los datos, ya que como se suele coger de pantalla, se normalizará
		String selection = null;
		String[] selectionArgs = null;
		String orderBy = UsersTable.USERNAME + " DESC";
		String groupBy = null;
		String having = null;
		return db.query(table, columns, selection, selectionArgs, groupBy, having, orderBy);
	}

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
