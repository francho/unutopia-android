package com.valles.rssreader;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ProgressBar;
import android.widget.TextView;

public class SplashActivity extends Activity {

	private ProgressBar Loadpro;
	private TextView Continuar;
	private TextView TxtLoad ;
	
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        
        
       final TextView TxtTit = (TextView) findViewById(R.id.lbltit);
       final TextView TxtNom = (TextView) findViewById(R.id.lblnom);
       Continuar = (TextView) findViewById(R.id.btnloading);
       Loadpro = (ProgressBar) findViewById(R.id.proload);
       TxtLoad = (TextView) findViewById(R.id.loading);
       
        Typeface font1 = Typeface.createFromAsset(getAssets(),"Last Ninja.ttf");
        Continuar.setTypeface(font1);
        TxtLoad.setTypeface(font1);
        TxtTit.setTypeface(font1);
        TxtNom.setTypeface(font1);
        
        Continuar.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {           	
            	Intent intent = new Intent(SplashActivity.this, ArticleListActivity.class);     
                startActivity(intent);             	
            }
        });
        
        Continuar.setVisibility(View.GONE);
        
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
	    			 alpha.cancel();
	    			 TxtLoad.setAnimation(null);		 
	    			 TxtLoad.setVisibility(View.GONE);
	    			 Continuar.setVisibility(View.VISIBLE); 
	    		 }
	    	}.start();      
		}
}
