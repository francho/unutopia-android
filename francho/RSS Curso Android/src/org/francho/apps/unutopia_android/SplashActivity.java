package org.francho.apps.unutopia_android;

import org.francho.apps.unutopia_android.app.AppIntent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.View.OnClickListener;

public class SplashActivity extends Activity implements OnClickListener {

	private CountDownTimer timer = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		View rootView = findViewById(R.id.splash_root);
		rootView.setOnClickListener(this);
		
		launchSyncService();
	}

	private void launchSyncService() {
		startService(AppIntent.getSyncArticlesIntent());
	}

	@Override
	protected void onResume() {
		super.onResume();
		
		timer = new NextActivityTimer();
		timer.start();
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		
		if(null != timer) {
			timer.cancel();
			timer=null;
		}
		
	}

	@Override
	public void onClick(View v) {
		startNextActivity();
	};
	
	private void startNextActivity() {
		final Intent intent = AppIntent.getArticleListIntent();
		startActivity(intent);
	}

	class NextActivityTimer extends CountDownTimer {

		public NextActivityTimer() {
			super(10000, 10000);
		}

		@Override
		public void onTick(long millisUntilFinished) {
			// Do nothing
		}

		@Override
		public void onFinish() {
			startNextActivity();
		}
	}

	

}
