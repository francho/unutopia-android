package org.francho.unutopia.mycontenterprovider.data;

import android.net.Uri;
import android.provider.BaseColumns;

public class MembersContract {
	public static final String DB_NAME="users.db";
	public static final String AUTHORITY = "org.francho.unutopia.mycontentprovider";
	
	private MembersContract() {}
	
	public static class UsersTable implements BaseColumns{
		private UsersTable() {}
		public static final String TABLE_NAME = "users";
		
		public static final String USERNAME="username";
		public static final String EMAIL="email";
		
		public static Uri getUri() {
			return Uri.parse("content://"+AUTHORITY+"/users");
		}

		public static Uri getUri(long id) {
			return Uri.parse("content://"+AUTHORITY+"/users/"+id);
		}
		
	}
	
}
