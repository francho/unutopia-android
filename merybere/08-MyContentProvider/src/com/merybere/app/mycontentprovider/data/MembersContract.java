package com.merybere.app.mycontentprovider.data;

import android.net.Uri;
import android.provider.BaseColumns;

public class MembersContract {

	// Nombre del fichero de texto donde vamos a almacenar la base de datos
	public static final String DB_NAME="users.db";
	// Esta AUTHORITY la usaremos en toda la programación como constante;
	// en el manifiesto hay que definirla igual
	public static final String AUTHORITY = "com.merybere.app.mycontentprovider";
	
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
		
		public static Uri getUri() {
			
			return Uri.parse("content://" + AUTHORITY + "/users");
		}
		
		public static Uri getUri(long id) {
			
			return Uri.parse("content://" + AUTHORITY + "/users/" + id);
		}
		
	}
}
