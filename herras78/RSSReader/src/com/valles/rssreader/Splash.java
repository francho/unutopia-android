package com.valles.rssreader;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.Menu;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ProgressBar;
import android.widget.TextView;

public class Splash extends Activity {

	private ProgressBar Loadpro;
	
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        
        Loadpro = (ProgressBar) findViewById(R.id.proload);
        TextView TxtTit = (TextView) findViewById(R.id.lbltit);
        TextView TxtNom = (TextView) findViewById(R.id.lblnom);
        TextView TxtLoad = (TextView) findViewById(R.id.loading);
       
        Typeface font1 = Typeface.createFromAsset(getAssets(),"Last Ninja.ttf");
        TxtLoad.setTypeface(font1);
        TxtTit.setTypeface(font1);
        TxtNom.setTypeface(font1);
        
        Animation scale = AnimationUtils.loadAnimation(this, R.anim.scalealpha);
        Animation alpha = AnimationUtils.loadAnimation(this, R.anim.alpha);
        alpha.reset();
        scale.reset();
        TxtLoad.startAnimation(alpha);
        TxtTit.startAnimation(scale);
        
        SimuCargando();
        
    }

	public void SimuCargando(){
		final Animation alpha = AnimationUtils.loadAnimation(this, R.anim.alpha);

			new CountDownTimer(7000, 500) {  	
				int progreso=0;
	    		 public void onTick(long millisUntilFinished) {   			
	    			progreso++;
	    			Loadpro.setProgress(progreso);
	    		 }
	    		 public void onFinish() {
	    			 Loadpro.setProgress(12);
	    			 Intent intent = new Intent(Splash.this, Main.class);     
	                 startActivity(intent); 
	    		 }
	    	}.start();      
		}
}
