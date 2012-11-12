package org.francho.unutopia.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.util.Log;

public class MyBroadcastReceiver extends BroadcastReceiver {

	private static final String TAG = "MyBroadcastReceiver";

	@Override
	public void onReceive(Context context, Intent intent) {
		Log.d(TAG, "onReceive");
		
		String state = intent.getStringExtra(TelephonyManager.EXTRA_STATE);
		
		if(state != null && state.equals(TelephonyManager.EXTRA_STATE_RINGING)) {
			String tlf = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);
			Log.d(TAG, "llamada entrante de " + tlf );
		}
		
	}

}
