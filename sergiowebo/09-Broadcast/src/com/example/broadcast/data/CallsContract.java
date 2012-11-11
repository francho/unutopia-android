package com.example.broadcast.data;

import android.net.Uri;
import android.provider.BaseColumns;

public class CallsContract {
	public static final String DB_NAME="calls.db";
	public static final String AUTHORITY = "com.example.broadcast";
	
	private CallsContract() {}
	
	public static class UsersTable implements BaseColumns{
		private UsersTable() {}
		public static final String TABLE_NAME = "calls";
		
		public static final String NUMBER="number";
		//public static final String EMAIL="email";
		
		public static Uri getUri() {
			return Uri.parse("content://"+AUTHORITY+"/calls");
		}

		public static Uri getUri(long id) {
			return Uri.parse("content://"+AUTHORITY+"/calls/"+id);
		}
		
	}
	
}
