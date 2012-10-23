package com.valles.rssreader;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

public class ArticleListActivity extends Activity {
	 
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.article_list);
        
        final TextView TxtTitulo = (TextView) findViewById(R.id.lbltitulo);
        final Typeface font1 = Typeface.createFromAsset(getAssets(),"Last Ninja.ttf");
        TxtTitulo.setTypeface(font1);
        
        final ImageView acercade = (ImageView) findViewById(R.id.acercade);
        acercade.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {           	
            	ToAbout();            	
            }
        });
    }
	
	public boolean onCreateOptionsMenu(Menu menu) {  
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.layout.menu_article_list, menu);
	    return true;
	}
	
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	        case R.id.menu_about:
	        	ToAbout();
	            return true;
	        case R.id.menu_buscar:
	           
	            return true;
	        case R.id.menu_mas:
	            
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
	
	public void ToAbout(){
		Intent intent = new Intent(ArticleListActivity.this, AboutActivity.class);     
        startActivity(intent); 
	}
}
	

