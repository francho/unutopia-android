package com.webo.FeedReader;

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
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        
        //Listener de toda la pantalla
        RelativeLayout relativeLayout = (RelativeLayout)findViewById(R.id.relativeLayout_splash);
        relativeLayout.setOnTouchListener(this);
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
