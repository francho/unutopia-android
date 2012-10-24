package com.webo.FeedReader;

import java.util.Timer;
import java.util.TimerTask;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.RelativeLayout;

public class SplashActivity extends Activity implements OnTouchListener {
	
	private long splashDelay = 5000; // 5 segundos
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        
        //Listener de toda la pantalla
        RelativeLayout relativeLayout = (RelativeLayout)findViewById(R.id.relativeLayout_splash);
        relativeLayout.setOnTouchListener(this);
        
        //Timer para pasar a la siguiente pantalla sino se pulsa la pantalla
        TimerTask task = new TimerTask(){
        	
        	@Override
        	public void run(){
        		finish();
        		Context context = SplashActivity.this;
    			Intent intent = new Intent(context, ArticleListActivity.class);
    			startActivity(intent);
        	}
        };
        
        Timer timer = new Timer();
        timer.schedule(task, splashDelay);
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.article_list, menu);
        return true;
    }

	public boolean onTouch(View arg0, MotionEvent arg1) {
		switch(arg0.getId()) {
		case R.id.relativeLayout_splash:
			Context context = this;
			Intent intent = new Intent(context, ArticleListActivity.class);
			startActivity(intent);
			break;
		}
		
		return false;
	}
}
