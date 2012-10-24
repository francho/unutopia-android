package org.cacahuete.app.feedreader.splash;

import android.os.Bundle;
import android.app.Activity;
import android.os.Handler;
import android.content.Intent;


/**
* Splash screen activity
*
*/
public class SplashScreenActivity extends Activity {
	

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.splash);
		
		Handler handler = new Handler();
		// 	run a thread after 2 seconds to start the home screen
		handler.postDelayed(new Runnable() {
		
			//@Override
			public void run() {
				//make sure we close the splash screen so the userwon't come back when it pres
				finish();
				//start the home screen
				Intent intent = new Intent(SplashScreenActivity.this, AboutScreenActivity.class);
				SplashScreenActivity.this.startActivity(intent);
			}
		}, 2000); // time in milliseconds (1 second = 1000 milliseconds) until the run() method 
	}
}
