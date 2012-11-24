package com.valles.rssreader;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class AboutActivity extends Activity{

	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.about);
		
		final TextView Aceptar = (TextView) findViewById(R.id.btn_aceptar);
		Aceptar.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {           	
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
		Intent intent = new Intent(AboutActivity.this, ArticleListActivity.class);     
        startActivity(intent); 
	}

}
