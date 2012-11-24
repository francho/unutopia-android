package com.valles.rssreader;

import com.valles.rssreader.db.RssDbHelper;
import com.valles.rssreader.db.RssContract.FeedsTable;
import com.valles.rssreader.db.RssContract.SourceTable;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SourceCreation extends Activity{
	
	public  RssDbHelper helper = new RssDbHelper(this);
	private EditText SourceName;
	private EditText SourceURL;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.source_creation);
		
		EditText SourceName  = (EditText)findViewById(R.id.s_text_name);
		EditText SourceURL = (EditText)findViewById(R.id.s_text_url);
		
		final Button Aceptar = (Button) findViewById(R.id.s_button_save);
		Aceptar.setOnClickListener(new OnClickListener() {
            public void onClick(View v) { 
            	SaveSource();
            	Toast.makeText(SourceCreation.this, R.string.added_source, Toast.LENGTH_SHORT).show();
            	Salir();            	
            }
        });
	}
	
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			Salir();
           return true;
        }
        return false;
    }
	
	public void Salir(){
		Intent intent = new Intent(SourceCreation.this, ArticleListActivity.class);     
        startActivity(intent); 
	}
	
	public void SaveSource(){
		
		ContentValues values = new ContentValues();
        values.put(SourceTable.NAME, SourceName.getText().toString());
        values.put(SourceTable.URL, SourceURL.getText().toString());
        values.put(SourceTable.IMAGE, R.drawable.feed_imgen);

        final SQLiteDatabase WDB = helper.getWritableDatabase();
        WDB.insert(SourceTable.TABLE_NAME, null, values );
        WDB.close();
	}
	
	

}
