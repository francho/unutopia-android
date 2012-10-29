package org.francho.unutopia_android.persistencia.db;

import android.provider.BaseColumns;

public class MembersContract {
	public static final String DB_NAME="users.db";
	
	private MembersContract() {}
	
	public static class UsersTable implements BaseColumns{
		private UsersTable() {}
		public static final String TABLE_NAME = "users";
		
		public static final String USERNAME="username";
		public static final String EMAIL="email";
		
	}
	
}
