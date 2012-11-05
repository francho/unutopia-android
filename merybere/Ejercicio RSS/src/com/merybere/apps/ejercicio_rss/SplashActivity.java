package com.merybere.apps.ejercicio_rss;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import app.AppIntent;

public class SplashActivity extends Activity {

    private View titulo;
	private CountDownTimer timer = null;

	@Override
    public void onCreate(Bundle savedInstanceState) {
		
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.splash);
        
        // Cachear el objeto título clickable
        titulo = findViewById(R.id.titulo);
        
        // Listener para responder al evento click
        titulo.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				
				Context context = SplashActivity.this;
				// Mensajero (se crea el mensaje que se va a pasar)
				Intent intent = new Intent(context, ArticleListActivity.class);
				
				startActivity(intent);
				
			}
        });
    }

    @Override
	protected void onPause() {
		super.onPause();
		
		if(null != timer) {
			timer.cancel();
			timer = null;
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		
		// Contador a 10 segundos
		timer = new SplashTimer(10000, 10000);
		timer.start();
	}


	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.splash, menu);
        return true;
    }
	
	private void nextActivity() {
		
		// Mensajero (se crea el mensaje que se va a pasar)
		final Intent intent = AppIntent.getArticleListIntent();
		startActivity(intent);
	}
	
	//Timer Class inside my Activity
    class SplashTimer extends CountDownTimer{

        public SplashTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onFinish() {
            nextActivity();
        }

        @Override
        public void onTick(long millisUntilFinished) {
        	
        }
    }
}
