package data;

import android.provider.BaseColumns;

// Esta clase extiende de Object
public class ArticlesContract {
	
	// Nombre del fichero de texto donde vamos a almacenar la base de datos
	// Se define a nivel global
	public static final String DB_NAME="articles.db";
	
	// Como es una clase que no suele tener funcionalidad, se le pone un constructor privado.
	// Así nos aseguramos de que nadie va a hacer uso de ella
	private ArticlesContract() {}
		
	// Las subclases que son de tablas implementan de BaseColumns
	// El interfaz BaseColumns nos crea el campo _ID como identificador del objeto
	// y el campo count, que es el número de elementos que hay en la tabla
	public static class Articles implements BaseColumns {
		
		// No interesa que se pueda instanciar, por lo que declaramos un constructor privado
		private Articles() {}
			
		public static final String TABLE_NAME = "articles";
		
		// Nombres de las columnas
		public static final String TITLE = "title";
		public static final String LINK = "link";
		public static final String PUB_DATE = "pubDate";
		public static final String DESCRIPTION = "description";
		public static final String CONTENT = "content";
	}
	
}


