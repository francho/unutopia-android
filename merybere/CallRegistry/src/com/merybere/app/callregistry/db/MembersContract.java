package com.merybere.app.callregistry.db;

import android.net.Uri;
import android.provider.BaseColumns;

public class MembersContract {

	// Nombre del fichero de texto donde vamos a almacenar la base de datos
	public static final String DB_NAME="calls.db";
	// Esta AUTHORITY la usaremos en toda la programación como constante;
	// en el manifiesto hay que definirla igual
	public static final String AUTHORITY = "com.merybere.app.callregistry";
	
	// Como es una clase que no suele tener funcionalidad, se le pone un constructor privado.
	// Así nos aseguramos de que nadie va a hacer uso de ella
	private MembersContract() {}
	
	// Las clases que son de tablas implementan de BaseColumns
	public static class CallsRecordTable implements BaseColumns {
		// No interesa que se pueda instanciar, por lo que declaramos un constructor privado
		private CallsRecordTable() {}
		
		public static final String TABLE_NAME = "calls";
		public static final String PHONENUMBER="phonenumber";
		public static final String DATETIME="datetime";
		
		public static Uri getUri() {
			
			return Uri.parse("content://" + AUTHORITY + "/calls");
		}
		
		public static Uri getUri(long id) {
			
			return Uri.parse("content://" + AUTHORITY + "/calls/" + id);
		}
		
	}
}
