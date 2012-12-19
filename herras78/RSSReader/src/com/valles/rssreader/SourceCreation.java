package com.valles.rssreader;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import nl.matshofman.saxrssreader.RssFeed;
import nl.matshofman.saxrssreader.RssItem;
import nl.matshofman.saxrssreader.RssReader;

import com.valles.rssreader.db.RssDbHelper;
import com.valles.rssreader.db.RssContract.FeedsTable;
import com.valles.rssreader.db.RssContract.SourceTable;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
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
		
		SourceName  = (EditText)findViewById(R.id.s_text_name);
		SourceURL = (EditText)findViewById(R.id.s_text_url);
		
		final Button Aceptar = (Button) findViewById(R.id.s_button_save);
		Aceptar.setOnClickListener(new OnClickListener() {
            public void onClick(View v) { 
            	SaveSource();       	
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
	
	public void CheckFields(){
		try {	
			if( SourceName.getText().toString() != "" || SourceURL.getText().toString() != ""){
				
				URL url = new URL(SourceURL.getText().toString());
				RssFeed feed = RssReader.read(url);
				ArrayList<RssItem> rssItems = feed.getRssItems();
				
				if(rssItems.size() > 0 && rssItems != null){	SaveSource();	}
				else{	Toast.makeText(SourceCreation.this, R.string.no_connect_url, Toast.LENGTH_SHORT).show();	}
				
			}else{
				Toast.makeText(SourceCreation.this, R.string.complet_fields, Toast.LENGTH_SHORT).show();
			}

		} catch (Exception e) {
			Toast.makeText(SourceCreation.this, "Error al conectar con " + SourceURL.getText().toString(), Toast.LENGTH_SHORT).show();
			Log.d("RSS","Error " + e + " al conectar con " + SourceURL.getText().toString());
		}		
	}
	
	public void SaveSource(){
		
		ContentValues values = new ContentValues();
        values.put(SourceTable.NAME, SourceName.getText().toString());
        values.put(SourceTable.URL, SourceURL.getText().toString());
        values.put(SourceTable.IMAGE, R.drawable.feed_imgen);

        final SQLiteDatabase WDB = helper.getWritableDatabase();
        WDB.insert(SourceTable.TABLE_NAME, null, values );
        WDB.close();
        
        Toast.makeText(SourceCreation.this, R.string.added_source, Toast.LENGTH_SHORT).show();
        Salir();
	}
	
	

}
