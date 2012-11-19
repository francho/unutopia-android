package org.francho.unutopia_android.services;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ProgressBar;

public class MainActivity extends Activity {

	MyResultReceiver resultReceiver = new MyResultReceiver();
	private ProgressBar progressBar;
	private ImageView imageView;
	private LoadImageAsyncTask task;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        progressBar = (ProgressBar) findViewById(R.id.progressBar1);
        progressBar.setVisibility(View.INVISIBLE);
        
        imageView = (ImageView) findViewById(R.id.imageView1);
        
        findViewById(R.id.button1).setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				Intent service = new Intent("org.francho.unutopia_android.ACTION_SERVICE");
				startService(service);
			}
		});
        
        findViewById(R.id.button2).setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				Intent service = new Intent("org.francho.unutopia_android.ACTION_INTENT_SERVICE");
				service.putExtra("EXTRA_MYRESULTRECEIVER", resultReceiver);
				startService(service);
			}
		});
        
        
    }
    
    
    

    @Override
	protected void onStart() {
		super.onStart();
		
		task = new LoadImageAsyncTask(imageView);
		task.execute("http://www.android.com/images/marquee_jb.png");
	}


	@Override
	protected void onStop() {
		if(task != null ) {
			task.cancel(true);
		}
		
		super.onStop();
	}




	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    
    class MyResultReceiver extends ResultReceiver {

		public MyResultReceiver() {
			super(new Handler());
		}

		@Override
		protected void onReceiveResult(int resultCode, Bundle resultData) {
			super.onReceiveResult(resultCode, resultData);
			
			switch (resultCode) {
			case 1:
				progressBar.setVisibility(View.VISIBLE);
				break;
			case 0:
				progressBar.setVisibility(View.INVISIBLE);
				break;
			default:
				break;
			}
			
		}
		
		
    	
    }
}
