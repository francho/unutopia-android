package com.valles.rssreader;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.ImageView;
import android.widget.TextView;

public class ItemReader extends Activity {

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.item_reader);
		final Context context = this;
		
		Bundle bundle = getIntent().getExtras();
		
		final TextView LblTitulo = (TextView) findViewById(R.id.lbl_titulo_reader);
	    final Typeface font1 = Typeface.createFromAsset(getAssets(),"Last Ninja.ttf");
	    LblTitulo.setTypeface(font1);
	    
	    final TextView TxtDate = (TextView) findViewById(R.id.date_reader);
	    final TextView TxtTit = (TextView) findViewById(R.id.titulo_reader);
	    final ImageView ImgReader = (ImageView)findViewById(R.id.imgen_reader);
	    
	    TxtDate.setText(bundle.getString("FECHA"));
	    TxtTit.setText(bundle.getString("TITULO"));
	    ImgReader.setImageDrawable(context.getResources().getDrawable(bundle.getInt("IMAGEN")));
	    
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
