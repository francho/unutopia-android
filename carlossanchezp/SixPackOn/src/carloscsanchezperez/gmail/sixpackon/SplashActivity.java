package carloscsanchezperez.gmail.sixpackon;




import android.app.Activity;
import android.app.ProgressDialog;
import android.os.CountDownTimer;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;

import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.view.MenuItem;



import android.os.Handler;



public class SplashActivity extends Activity {
	private ProgressDialog progressDialog;	
	private final long startTime = 30000;
	private final long interval = 1000;
	private CspCountDownTimer countDownTimer;
	private long timeElapsed;
	
	
	private final Handler progressHandler = new Handler() {
		public void handleMessage(Message msg) {
			if (msg.obj != null) {
			}
			progressDialog.dismiss();
	    }
	};

	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.splash);
        
        
        TextView txtVersion = (TextView) findViewById(R.id.txtVersion);
        //------------------------ Counter 30 second
        countDownTimer = new CspCountDownTimer(startTime, interval);
        countDownTimer.start();
        // --------------------------------------------------------------
        View continueButton = findViewById(R.id.button1);
        continueButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Context context = SplashActivity.this;
				// ----------  message on load
				loadData();
				Intent intent = new Intent(context, ArticleListActivity.class);
				startActivity(intent);
			}
        	
        });
        
        
    	
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.splash, menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	boolean result = false;
        // Handle item selection
        switch (item.getItemId()) {
        	case R.id.menu_about :
        		
        		Intent i = new Intent (this, AboutActivity.class);
    			startActivity(i);
        		
        		result = true;
        		break;
        		
        	case R.id.menu_settings :
        		
        		result = true;
        		break;
        	
        	default:
                result = super.onOptionsItemSelected(item);
        }
        
        return result;
    }
    
    private void loadData() {
    	progressDialog = ProgressDialog.show(
    			SplashActivity.this,
    			"", 
    			"Por favor espere mientras se cargan los datos...", 
    			true);
    	new Thread(new Runnable(){
    		@Override
    		public void run() {
    			
                Message msg = progressHandler.obtainMessage();
                
    			progressHandler.sendMessage(msg);
    		}}).start();
    }    

	// CountDownTimer class
	public class CspCountDownTimer extends CountDownTimer
		{
		

			public CspCountDownTimer(long startTime, long interval)
				{
					super(startTime, interval);
				}

			@Override
			public void onFinish()
				{
				Context context = SplashActivity.this;
				loadData();
				Intent intent = new Intent(context, ArticleListActivity.class);
				startActivity(intent);
				}

			@Override
			public void onTick(long millisUntilFinished)
				{
					timeElapsed = startTime - millisUntilFinished;
				}
		}

	@Override
	protected void onPause() {
		super.onPause();
		
		if(null != countDownTimer) {
			countDownTimer.cancel();
			countDownTimer=null;
		}
		
	}
 
}
