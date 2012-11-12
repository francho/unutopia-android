package cat.foixench.apps.callsregister;

import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.text.format.Time;
import cat.foixench.apps.callsregister.data.CallsContract.IncommingTable;

public class CallsBroadcastReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		String state =  intent.getStringExtra(TelephonyManager.EXTRA_STATE);
		
		if (state.equals (TelephonyManager.EXTRA_STATE_RINGING)) {
			
			ContentValues values = new ContentValues ();
			
			Time ahora = new Time ();
			ahora.setToNow ();
			
			values.put(IncommingTable.COLUMN_PHONE, intent.getStringExtra (TelephonyManager.EXTRA_INCOMING_NUMBER));
			values.put (IncommingTable.COLUMN_CALL_DATE, ahora.toMillis(true));
			
			context.getContentResolver ().insert  (IncommingTable.getUri(), values);
		}

	}

}
