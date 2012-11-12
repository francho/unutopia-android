package org.francho.unutopia.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MyBroadcastReceiver2 extends BroadcastReceiver {

	private static final String TAG = "MyBroadcastReceiver2";

	@Override
	public void onReceive(Context context, Intent intent) {
		Log.d(TAG, "onReceive");
	}

}
