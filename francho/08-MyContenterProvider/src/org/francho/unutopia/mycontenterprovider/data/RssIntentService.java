package org.francho.unutopia_android.services.app;


import android.app.IntentService;
import android.content.Intent;
import android.os.ResultReceiver;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

public class MyIntentService extends IntentService {



	private static final String TAG = "MyIntentService";
	private ResultReceiver resultReceiver;

	public MyIntentService() {
		super(TAG);
	}
	
	@Override
	protected void onHandleIntent(Intent intent) {
		Log.d(TAG, "onHandleIntent");
		
		resultReceiver = (ResultReceiver) intent.getParcelableExtra("EXTRA_MYRESULTRECEIVER");
		
		if(resultReceiver != null) {
			resultReceiver.send(1, null);
		}
		
		Dummy heavyTask = new Dummy();
		heavyTask.hardWork();
		
		if(resultReceiver != null) {
			resultReceiver.send(0, null);
		}
	}

	@Override
	public void onCreate() {
		Log.d(TAG, "onCreate");
		super.onCreate();
	}

	@Override
	public void onDestroy() {
		Log.d(TAG, "onDestroy");
		super.onDestroy();
	}

	
}
