package data;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.text.format.Time;

// Clase que extiende de ArrayList de HashMap (el tipo de dato que nos va a pedir el adapter
// Se autoinicializa con tres artículos
public class DummyArticles {

	// Método para inicializar la tabla de una bd con artículos
	public static void insertDummyArticles(SQLiteDatabase db) {
		
		addArticle(db, "Primer artículo de prueba", "2012-10-10T15:25:45.000Z");
		addArticle(db, "Olga Román publica nuevo disco", "2012-10-05T17:45:00.000Z");
		addArticle(db, "Teatro Che y Moche vuelve con Oua Umplute", "2012-10-02T14:00:00.000Z");
		addArticle(db, "Comienzan los cursos de teatro del Teatro de las Esquinas", "2012-09-10T15:25:45.000Z");
		addArticle(db, "Inaguración del Teatro de las Esquinas", "2012-09-05T17:45:00.000Z");
		addArticle(db, "Millán Salcedo inaugura el Teatro de las Esquinas", "2012-09-02T14:00:00.000Z");
		addArticle(db, "Carmen París en el Teatro Principal", "2012-09-04T15:25:45.000Z");
		addArticle(db, "Noche de Todos los Santos en el Teatro de las Esquinas", "2012-09-03T17:45:00.000Z");
		addArticle(db, "Don Juan y cena en el Teatro de las Esquinas", "2012-09-02T14:00:00.000Z");
		addArticle(db, "Segundo artículo de prueba", "2012-08-10T15:25:45.000Z");
		addArticle(db, "Tercer artículo de prueba", "2012-08-05T17:45:00.000Z");
		addArticle(db, "Cuarto artículo de prueba", "2012-08-02T14:00:00.000Z");
	}
	
	public static void addArticle(SQLiteDatabase db, String title, String date) {
	    
		ContentValues values = new ContentValues();
	    
	    values.put(ArticlesContract.Articles.TITLE, title);
	    
	    // Convertir la fecha al formato deseado
	    Long millis = parseDate(date);
	    values.put(ArticlesContract.Articles.PUB_DATE, millis);
	    
	    db.insert(ArticlesContract.Articles.TABLE_NAME, null, values);
	}

	private static Long parseDate(String date) {
		Time time = new Time();
		time.parse3339(date);
		Long millis = time.normalize(false);
		return millis;
	}
	
}
