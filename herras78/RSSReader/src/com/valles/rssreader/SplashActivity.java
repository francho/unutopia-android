package com.valles.rssreader;

import com.valles.rssreader.service.LoaderIntentService;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Typeface;
import android.util.Log;
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
	String TAG ="SplashActivity";
	private static final int NOTIFICATION_DOWNLOAD_ID = 1;
	
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
              	
        /*Intent intentService = new Intent(SplashActivity.this,LoaderIntentService.class);
        intentService.putExtra("progress", 0);
        this.startService(intentService);*/
        	
        Intent intentService = new Intent("com.valles.rssreader.ACTION_INTENT_SERVICE");
        intentService.putExtra("progress", 0);
    	this.startService(intentService);	             
 
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
				Notifications(intent.getIntExtra("progress", 0));
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
	
	public void Notifications(int num){
		
		String NotificationService = Context.NOTIFICATION_SERVICE;
		NotificationManager notificationManager =  (NotificationManager) getSystemService(NotificationService);
		
		int icono = android.R.drawable.btn_star;
		CharSequence textoEstado = num + " nuevas noticias!";
		long hora = System.currentTimeMillis();
		 
		Notification notifcation =  new Notification(icono, textoEstado, hora);
		
		Context contexto = getApplicationContext();
		CharSequence titulo = "HRSS Reader";
		CharSequence descripcion = "Han sido descargadas " + num + " nuevas noticias";
		 
		Intent notIntent = new Intent(contexto, SplashActivity.class);
		 
		PendingIntent contIntent = PendingIntent.getActivity(contexto, 0, notIntent, 0);
		 
		notifcation.setLatestEventInfo(contexto, titulo, descripcion, contIntent);
		notifcation.flags |= Notification.FLAG_AUTO_CANCEL;
		notifcation.flags |= Notification.DEFAULT_VIBRATE;
		
		notificationManager.notify(NOTIFICATION_DOWNLOAD_ID, notifcation);
	}
}
