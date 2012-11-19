package org.cacahuete.app.feedreader;

import org.cacahuete.app.feedreader.R;

import android.os.Bundle;
import android.os.ResultReceiver;
import android.app.Activity;
import android.os.Handler;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;


/**
* Splash screen activity
*
*/
public class SplashScreenActivity extends Activity {
	private ImageView i;
	 MyResultReceiver resultReceiver = new MyResultReceiver();
	

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.splash);
		
		Intent service = new Intent("org.cacahuete.app.feedreader.ACTION_INTENT_SERVICE");
		service.putExtra("EXTRA_MYRESULTRECEIVER", resultReceiver);
		startService(service);
		
		
		i= (ImageView) findViewById(R.id.imageView1);
		i.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				Context context = SplashScreenActivity.this;
				Intent intent = new Intent(context, ArticleListActivity.class);
				startActivity(intent);
			}
		});
		
		
		
		
		
		
		
		
		
		
		
		
		Handler handler = new Handler();
		// 	run a thread after 2 seconds to start the home screen
		handler.postDelayed(new Runnable() {
		
			//@Override
			public void run() {
				//make sure we close the splash screen so the userwon't come back when it pres
				finish();
				//start the home screen
				
				
				Intent intent = new Intent(SplashScreenActivity.this, ArticleListActivity.class);
				SplashScreenActivity.this.startActivity(intent);
			}
		},5000); // time in milliseconds (1 second = 1000 milliseconds) until the run() method 
		
	}
	
	
}

class MyResultReceiver extends ResultReceiver {

	public MyResultReceiver() {
		super(new Handler());
	}

//	@Override
//	protected void onReceiveResult(int resultCode, Bundle resultData) {
//		super.onReceiveResult(resultCode, resultData);
//		
//		switch (resultCode) {
//		case 1:
//			progressBar.setVisibility(View.VISIBLE);
//			break;
//		case 0:
//			progressBar.setVisibility(View.INVISIBLE);
//			break;
//		default:
//			break;
//		}
		
	
	
	
	
}


