package org.francho.unutopia_android.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class MainActivity extends Activity {

    private static final String TAG = "MainActivity";
	private TextView texto;



	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        texto = (TextView) findViewById(R.id.texto);
        
        texto.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Context context = MainActivity.this;
				Intent intent = new Intent(context, Activity2.class);
				intent.putExtra("EXTRA_TEXTO", texto.getText());
				
				
				startActivity(intent);
			}
        	
        });
    }
    
    

    @Override
	protected void onPause() {
		Log.d(TAG,"onPause");
		super.onPause();
	}



	@Override
	protected void onRestart() {
		Log.d(TAG,"onRestart");
		super.onRestart();
	}



	@Override
	protected void onResume() {
		Log.d(TAG,"onResume");
		super.onResume();
	}



	@Override
	protected void onStart() {
		Log.d(TAG,"onStart");
		super.onStart();
	}



	@Override
	protected void onStop() {
		Log.d(TAG,"onStop");
		super.onStop();
	}



	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
