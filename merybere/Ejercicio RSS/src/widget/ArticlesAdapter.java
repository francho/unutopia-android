package widget;

import java.util.List;
import java.util.Map;

import com.merybere.apps.ejercicio_rss.R;

import data.ArticleContract;

import android.content.Context;
import android.text.format.DateUtils;
import android.widget.SimpleAdapter;
import android.widget.TextView;

// SimpleAdapter con el proceso de inicialización
public class ArticlesAdapter extends SimpleAdapter {

    // Correspondencia del HashMap con los ids de los objetos que se muestran:
    // - elementos del Array de HashMaps de eventos (from)
    // - elementos del diseño en XML de cada una de las filas (to)
    private static final String[] FROM = new String[] {ArticleContract.TITLE, ArticleContract.DATE};
    private static final int[] TO = new int[]{R.id.article_row_title, R.id.article_row_date};
    
	private Context context;
    
	// Constructor: recibe dos únicos parámetros
	//   - Contexto que se tiene que pasar cuando se instancia
	//   - Lista de elementos que queremos mostrar
	public ArticlesAdapter(Context context, List<? extends Map<String, ?>> data) {
		
		// Llamamos al super utilizando lo que hemos definido como constantes
		// El layout que se pasa es el que hemos definido para una línea de artículo
		super(context, data, R.layout.article_row, FROM, TO);
		
		this.context = context;
	}
	
	// Método que se utiliza para mostrar campos de texto
	@Override
	public void setViewText(TextView v, String text) {
		if(isDateView(v)) {
			text = getFormattedDate(text);
		}
		super.setViewText(v, text);
	}

	private String getFormattedDate(String text) {
		// Convertir el texto en un objeto fecha
		Long millis = Long.parseLong(text);
		
		// Relativo a la fecha actual, cuánto tiempo ha transcurrido
		return (String)DateUtils.getRelativeTimeSpanString(context, millis);
	}

	// Método que comprueba si un campo de texto es la fecha
	private boolean isDateView(TextView v) {
		// Obtener el id de la vista que quiero comprobar si es fecha
		return v.getId() == R.id.article_row_date;
	}


}