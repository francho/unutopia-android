package com.valles.rssreader;

import com.valles.rssreader.service.LoaderIntentService;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.Toast;

public class ProgressActivity extends Activity{
	
	private ProgressBar ProBar;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.progress_activity);
		
		ProBar = (ProgressBar) findViewById(R.id.progressload);
		
		Intent intentService = new Intent("com.valles.rssreader.ACTION_INTENT_SERVICE");
        intentService.putExtra("progress", 0);
    	this.startService(intentService);
		
    	IntentFilter filter = new IntentFilter();
        filter.addAction(LoaderIntentService.START_LOAD);
        filter.addAction(LoaderIntentService.SET_PROGRESS);
        filter.addAction(LoaderIntentService.END_LOAD);
        
        ProgressReceiver progressControl = new ProgressReceiver();
        registerReceiver(progressControl, filter);
	}
	
	private class ProgressReceiver extends BroadcastReceiver {
		
		public void onReceive(Context context, Intent intent) {
			
			if(intent.getAction().equals(LoaderIntentService.START_LOAD)) {
				int max = intent.getIntExtra("set_max", 0);
				int prog = intent.getIntExtra("progress", 0);
				ProBar.setMax(max);
				ProBar.setProgress(prog);
			}
			else if(intent.getAction().equals(LoaderIntentService.SET_PROGRESS)) {
				int prog = intent.getIntExtra("progress", 0);
				ProBar.setProgress(prog);
			}
			else if(intent.getAction().equals(LoaderIntentService.END_LOAD)) {
				Toast.makeText(ProgressActivity.this, "Carga Finalizada", Toast.LENGTH_SHORT).show();
				//Notifications(intent.getIntExtra("progress", 0));
				Intent toArticle = new Intent(ProgressActivity.this, ArticleListActivity.class);     
                startActivity(toArticle); 
			}
		}
    }
}
