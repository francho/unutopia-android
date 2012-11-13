package org.cacahuete.app.registrollamadas.db;

import android.net.Uri;
import android.provider.BaseColumns;

public class CallslogContract {
	public static final String DB_NAME="log.db";
	public static final String AUTHORITY = "org.cacahuete.app.callslog";
	
	private CallslogContract() {}
	
	public static class CallsTable implements BaseColumns{
		private CallsTable() {}
		public static final String TABLE_NAME = "calls";
		
		public static final String TIME="time";
		public static final String NUMBER="number";
		
		public static Uri getUri() {
			return Uri.parse("content://"+AUTHORITY+"/calls");
		}

		public static Uri getUri(long id) {
			return Uri.parse("content://"+AUTHORITY+"/calls/"+id);
		}
		
	}
	
}
