package com.merybere.app.services.app;

import android.app.IntentService;
import android.content.Intent;
import android.os.ResultReceiver;
import android.util.Log;

public class MyIntentService extends IntentService {

	private static final String TAG = "MyIntentService";
	private ResultReceiver resultReceiver = new ResultReceiver(null);
	
	public MyIntentService() {
		// El constructor necesita una etiqueta para la cola de tareas
		super(TAG);
	}

	// Aquí la tarea pesada
	@Override
	protected void onHandleIntent(Intent intent) {
		Log.d(TAG, "onHandleIntent");
		
		resultReceiver = (ResultReceiver) intent.getParcelableExtra("com.merybere.app.EXTRA_MYRESULTRECEIVER");
		
		// Comprobar que nos han pasado el resultReceiver, para evitar null pointer exception
		if(resultReceiver != null) {
			// Al arrancar, mandar un result code 1
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
