package com.valles.rssreader;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

public class Main extends Activity {
	 
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);
        
        final TextView TxtTitulo = (TextView) findViewById(R.id.lbltitulo);
        final Typeface font1 = Typeface.createFromAsset(getAssets(),"Last Ninja.ttf");
        TxtTitulo.setTypeface(font1);
        
        final ImageView acercade = (ImageView) findViewById(R.id.acercade);
        acercade.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {           	
            	PopAcerca();            	
            }
        });
   
    }
	
	public void PopAcerca(){

		final AlertDialog.Builder acerca = new AlertDialog.Builder(this);
		final View vista = LayoutInflater.from(this).inflate(R.layout.popacercade,(ViewGroup) findViewById(R.id.dialogacercade));
		final TextView Titulo = (TextView) vista.findViewById(R.id.lblpoptit);
		final Typeface font1 = Typeface.createFromAsset(getAssets(),"Last Ninja.ttf");
		Titulo.setTypeface(font1);
		
		acerca.setIcon(R.drawable.ayudab);
		acerca.setTitle("Acerca de...");
		acerca.setView(vista);
		acerca.setPositiveButton("Aceptar",new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			} 
		});
		acerca.create();
		acerca.show();
	}
}

