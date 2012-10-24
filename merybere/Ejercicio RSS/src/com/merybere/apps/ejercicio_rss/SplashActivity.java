package com.merybere.apps.ejercicio_rss;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;

public class SplashActivity extends Activity {

    private static final String TAG = "SplashActivity";
	private View titulo;

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
				// Mensajero (se crea el mensaje que se va a pasar
				Intent intent = new Intent(context, ArticleListActivity.class);
				
				startActivity(intent);
				
			}
        });
    }

    @Override
	protected void onPause() {
		Log.d(TAG, "onPause");
		super.onPause();
	}

	@Override
	protected void onRestart() {
		Log.d(TAG, "onRestart");
		super.onRestart();
	}

	@Override
	protected void onResume() {
		Log.d(TAG, "onResume");
		super.onResume();
	}

	@Override
	protected void onStart() {
		Log.d(TAG, "onStart");
		super.onStart();
	}

	@Override
	protected void onStop() {
		Log.d(TAG, "onStop");
		super.onStop();
	}

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.splash, menu);
        return true;
    }
}
