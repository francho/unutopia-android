package org.francho.unutopia.ejer_registrollamadas;

import org.francho.unutopia.ejer_registrollamadas.db.CallslogContract.CallsTable;

import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.telephony.TelephonyManager;
import android.text.format.Time;

public class CallslogReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		String state = intent.getStringExtra(TelephonyManager.EXTRA_STATE);

		if (state != null && state.equals(TelephonyManager.EXTRA_STATE_RINGING)) {
			final String tlf = intent
					.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);
			
			final Time now = new Time();
			now.setToNow();
			
			final ContentValues values = new ContentValues();
			
			values.put(CallsTable.NUMBER, tlf);
			values.put(CallsTable.TIME, now.toMillis(true));
			
			final Uri uri = CallsTable.getUri();
			context.getContentResolver().insert(uri, values);
		}
	}

}
