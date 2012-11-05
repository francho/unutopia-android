package data;

import java.util.ArrayList;
import java.util.HashMap;

import android.text.format.Time;

// Clase que extiende de ArrayList de HashMap (el tipo de dato que nos va a pedir el adapter
// Se autoinicializa con tres artículos
public class DummyArticles extends ArrayList<HashMap<String,Object>> {

	// Método para inicializar el array con artículos
	public DummyArticles () {
		
		addArticle("Primer artículo de prueba","2012-10-10T15:25:45.000Z");
		addArticle("Olga Román publica nuevo disco","2012-10-05T17:45:00.000Z");
		addArticle("Teatro Che y Moche vuelve con Oua Umplute","2012-10-02T14:00:00.000Z");
	}
	
	public void addArticle(String title, String date) {
	    HashMap<String, Object> article = new HashMap<String, Object>();
	    
	    article.put(ArticleContract.TITLE, title);
	    
	    // Convertir la fecha al formato deseado
	    Long millis = parseDate(date);
	    article.put(ArticleContract.DATE, millis);
	    
	    add(article);
	}

	private Long parseDate(String date) {
		Time time = new Time();
		time.parse3339(date);
		Long millis = time.normalize(false);
		return millis;
	}
	
}
