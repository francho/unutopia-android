package carlossanchezperez.wordpress.com.whoscallme;

import whoscallme.db.CallsConstant;
import whoscallme.db.CallsConstant.CallsTable;

import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.telephony.TelephonyManager;
import android.text.format.Time;
import android.util.Log;

public class IngoingCallReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		

		Log.v(CallsConstant.LOGTAG, "Broadcast Receiver OnRecibe");
		
		final String tlf = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);
			
		final Time now = new Time();
		now.setToNow();
			
		final ContentValues values = new ContentValues();
			
		values.put(CallsTable.NUMBER, tlf);
		values.put(CallsTable.TIME, now.toMillis(true));
		Log.v(CallsConstant.LOGTAG, "Broadcast Receiver OnRecibe Put Valuesdata");
		
		final Uri uri = CallsTable.getUri();
		context.getContentResolver().insert(uri, values);
		
	}

}
