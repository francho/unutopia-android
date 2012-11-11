package data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
// Esta clase nos permite crear la BD, actualizarla, obtenerla en modo lectura/escritura, etc

public class ArticlesDbHelper extends SQLiteOpenHelper {

	// Versión de la base de datos
	private static final int DATABASE_VERSION = 4;

	// Constructor público que recibe como parámetro el contexto
	public ArticlesDbHelper(Context context) {
		// El tercer parámetro es null, un gestor para los pointers
		// El útimo parámetro es la versión. Por defecto, una constante
		super(context, ArticlesContract.DB_NAME, null, DATABASE_VERSION);
	}

	// Recibimos la BD, y en ella hay que crear las tablas
	@Override
	public void onCreate(SQLiteDatabase db) {
		// En este método se recibe la base de datos
		createTableArticles(db);
	}

	// Se recibe una BD ya creada, con un número de versión antiguo y uno nuevo.
	// Aquí se hacen las operaciones necesarias para actualizarla
	// Se usa, por ejemplo, para añadir tablas en una actualización de la aplicación
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

		createTableArticles(db);
	}

	private void createTableArticles(SQLiteDatabase db) {
		// Si la tabla ya existe, eliminarla; así se puede utilizar este método también en el Upgrade
		db.execSQL("DROP TABLE IF EXISTS " + ArticlesContract.Articles.TABLE_NAME);
		
		// Creación de la tabla Artículos
		db.execSQL("CREATE TABLE " + ArticlesContract.Articles.TABLE_NAME + "("
					+ ArticlesContract.Articles._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
					+ ArticlesContract.Articles.TITLE + " STRING,"
					+ ArticlesContract.Articles.LINK + " STRING,"
					+ ArticlesContract.Articles.PUB_DATE + " LONG,"
					+ ArticlesContract.Articles.DESCRIPTION + " TEXT,"
					+ ArticlesContract.Articles.CONTENT + " TEXT"
					+ ")"
					);
		
		// Cargar la tabla con los primeros datos de la clase DummyArticles
		DummyArticles.insertDummyArticles(db);
	}
}
