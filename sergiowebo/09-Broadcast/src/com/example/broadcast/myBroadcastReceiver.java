package com.example.broadcast;

import com.example.broadcast.data.CallsContract;

import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.telephony.TelephonyManager;
import android.test.mock.MockContentResolver;
import android.util.Log;

public class myBroadcastReceiver extends BroadcastReceiver{

	private static final String TAG = "myBroadcastReceiver";
	private MockContentResolver mContentResolver;

	@Override
	public void onReceive(Context arg0, Intent intent) {
		// TODO Auto-generated method stub
		Log.d(TAG, "onReceive");
		
		String state = intent.getStringExtra(TelephonyManager.EXTRA_STATE);
		
		if(state != null && state.equals(TelephonyManager.EXTRA_STATE_RINGING)){
			String tlf = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);
			Log.d(TAG, "Llamada entrante de "+tlf);
			
			ContentValues values = new ContentValues();
			values.put(CallsContract.UsersTable.NUMBER, tlf);
			
			Uri uri = CallsContract.UsersTable.getUri();
			
			Uri newUri = mContentResolver.insert(uri, values);
		}
	}

}
