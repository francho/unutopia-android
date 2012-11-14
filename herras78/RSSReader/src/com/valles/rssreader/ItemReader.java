package com.valles.rssreader;

import com.valles.rssreader.db.RssDbHelper;
import com.valles.rssreader.db.RssContract.FeedsTable;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.ImageView;
import android.widget.TextView;

public class ItemReader extends Activity {

	private RssDbHelper helper = new RssDbHelper(this);	
	
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.item_reader);
		final Context context = this;
		
		Bundle bundle = getIntent().getExtras();
		Cursor cursor = getFeed(bundle.getInt("ID"));
		cursor.moveToFirst();
		
		final TextView LblTitulo = (TextView) findViewById(R.id.lbl_titulo_reader);
	    final Typeface font1 = Typeface.createFromAsset(getAssets(),"Last Ninja.ttf");
	    LblTitulo.setTypeface(font1);
	    
	    final TextView TxtTit = (TextView) findViewById(R.id.titulo_reader);
	    final TextView TxtDate = (TextView) findViewById(R.id.date_reader);	    
	    final TextView TxtDesc = (TextView) findViewById(R.id.description_reader);
	    final TextView TxtContent = (TextView) findViewById(R.id.content_reader);
	    final ImageView ImgReader = (ImageView)findViewById(R.id.imgen_reader);
	    
	    
	    TxtTit.setText(cursor.getString(1));
	    TxtDate.setText(cursor.getString(2));
	    TxtDesc.setText(cursor.getString(3));
	    TxtContent.setText(cursor.getString(4));
	    
	    ImgReader.setImageDrawable(context.getResources().getDrawable(R.drawable.feed_imgen));
	    
	}
	
	private Cursor getFeed(int id) {
		SQLiteDatabase db = helper.getReadableDatabase();
		
		String table = FeedsTable.TABLE_NAME;
		String[] columns = new String[] {FeedsTable._ID,FeedsTable.TITLE,FeedsTable.PUB_DATE,FeedsTable.DESCRIPTION,FeedsTable.CONTENT};
		String selection = FeedsTable._ID + " = " + id;
		String[] selectionArgs = null;
		String groupBy = null;
		String having = null;
		String orderBy = null;
		return db.query(table, columns, selection, selectionArgs, groupBy, having, orderBy);
	}
	
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			Intent intent = new Intent(ItemReader.this, ArticleListActivity.class);     
	        startActivity(intent); 
           return true;
        }
        return false;
    }
}
