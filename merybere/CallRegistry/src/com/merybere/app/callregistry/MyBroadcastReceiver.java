package com.merybere.app.callregistry;

import com.merybere.app.callregistry.db.MembersContract.CallsRecordTable;

import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.telephony.TelephonyManager;
import android.text.format.Time;
//import android.util.Log;

public class MyBroadcastReceiver extends BroadcastReceiver {

	// private static final String TAG = "MyBroadcastReceiver";

	@Override
	public void onReceive(Context context, Intent intent) {
		// Log.d(TAG, "onReceive");
		
		// El TelephonyManager nos da acceso a los servicios del teléfono, sobre el intent.
		// La clave EXTRA_STATE nos devuelve una string con el estado de llamada
		String state = intent.getStringExtra(TelephonyManager.EXTRA_STATE);
		
		// Para saber si es una llamada entrante, y el número:
		if(state != null && state.equals(TelephonyManager.EXTRA_STATE_RINGING)) {
			// Capturamos el número de teléfono
			String tlf = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);
						
			// Capturamos el instante de tiempo en que se recibe la llamada
			final Time callTime = new Time();
			callTime.setToNow();
			
			// Log.d(TAG, "llamada entrante de " + tlf + " Hora: " + callTime.format3339(false));
			
			// Necesitamos un objeto ContentValues, cargar en él los datos que queremos insertar
			ContentValues values = new ContentValues();
			values.put(CallsRecordTable.DATETIME, callTime.toMillis(true));
			values.put(CallsRecordTable.PHONENUMBER, tlf);
			
			// Necesitamos una uri, a la que pasarle los valores y tiene que hacer el insert
			Uri uri = CallsRecordTable.getUri();
			
			ContentResolver mContentResolver = context.getContentResolver();
			// Llamada al insert.
			mContentResolver.insert(uri, values);
		}
	}

}
