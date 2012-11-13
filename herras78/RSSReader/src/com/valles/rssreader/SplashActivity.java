package com.valles.rssreader;

import com.valles.rssreader.service.LoaderIntentService;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Typeface;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class SplashActivity extends Activity {

	private ProgressBar Loadpro;
	private TextView Continuar;
	private TextView TxtLoad ;
	private CountDownTimer timer;
	private Animation alpha;
	
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        
        Intent intentService = new Intent(SplashActivity.this, LoaderIntentService.class);
        intentService.putExtra("progress", 0);
		startService(intentService);
        
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
            	timer.cancel();
            	Intent intent = new Intent(SplashActivity.this, ArticleListActivity.class);     
                startActivity(intent);             	
            }
        });
        
        Continuar.setVisibility(View.GONE);

        alpha = AnimationUtils.loadAnimation(this, R.anim.alpha);
        alpha.reset();
        TxtLoad.startAnimation(alpha);
        
        IntentFilter filter = new IntentFilter();
        filter.addAction(LoaderIntentService.START_LOAD);
        filter.addAction(LoaderIntentService.SET_PROGRESS);
        filter.addAction(LoaderIntentService.END_LOAD);
        
        ProgressReceiver progressControl = new ProgressReceiver();
        registerReceiver(progressControl, filter);
    }
    
    public class ProgressReceiver extends BroadcastReceiver {
		
		public void onReceive(Context context, Intent intent) {
			
			if(intent.getAction().equals(LoaderIntentService.START_LOAD)) {
				int max = intent.getIntExtra("set_max", 0);
				int prog = intent.getIntExtra("progress", 0);
				Loadpro.setMax(max);
				Loadpro.setProgress(prog);
			}
			else if(intent.getAction().equals(LoaderIntentService.SET_PROGRESS)) {
				int prog = intent.getIntExtra("progress", 0);
				Loadpro.setProgress(prog);
			}
			else if(intent.getAction().equals(LoaderIntentService.END_LOAD)) {
				Toast.makeText(SplashActivity.this, "Carga Finalizada", Toast.LENGTH_SHORT).show();
				 alpha.cancel();
    			 TxtLoad.setAnimation(null);		 
    			 TxtLoad.setVisibility(View.GONE);
    			 Continuar.setVisibility(View.VISIBLE);
    			 AutoLaunch();
			}
		}
    }

	public void AutoLaunch(){
		timer = new CountDownTimer(3000,1000) {  	
			public void onTick(long millisUntilFinished) {}			

			public void onFinish() {
   			 	Intent intent = new Intent(SplashActivity.this, ArticleListActivity.class);     
                startActivity(intent); 
   		 	}	 
	    }.start();     
	}
}
