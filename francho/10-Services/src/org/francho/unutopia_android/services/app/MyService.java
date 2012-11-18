package org.francho.unutopia_android.services.app;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {

	
	
	private static final String TAG = "MyService";

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

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Log.d(TAG, "onStartCommand");
		Dummy heavyTask = new Dummy();
		heavyTask.hardWork();
		
		stopSelf();
		
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public IBinder onBind(Intent intent) {
		Log.d(TAG, "onBind");
		
		return null;
	}

}
