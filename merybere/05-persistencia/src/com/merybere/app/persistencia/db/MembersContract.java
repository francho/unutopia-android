package com.merybere.app.persistencia.db;

import android.provider.BaseColumns;

public class MembersContract {

	// Nombre del fichero de texto donde vamos a almacenar la base de datos
	public static final String DB_NAME="users.db";
	// Como es una clase que no suele tener funcionalidad, se le pone un constructor privado.
	// Así nos aseguramos de que nadie va a hacer uso de ella
	
	private MembersContract() {}
	
	// Las clases que son de tablas implementan de BaseColumns
	public static class UsersTable implements BaseColumns {
		// No interesa que se pueda instanciar, por lo que declaramos un constructor privado
		private UsersTable() {}
		
		public static final String TABLE_NAME = "users";
		public static final String USERNAME="username";
		public static final String EMAIL="email";
		
	}
}
