package org.cacahuete.app.feedreader;

import java.util.ArrayList;
import java.util.HashMap;

import org.cacahuete.app.feedreader.R;

import android.os.Bundle;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;



/**
* Splash screen activity
*
*/
public class ArticleListActivity extends ListActivity {
	
	private ArrayList<HashMap<String,String>> datos;
	
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		datos=this.cargarNoticias();
		//System.out.println(datos.toString());
			
		int layout = android.R.layout.simple_list_item_2;
		String[] from = new String[] { "titular", "entradilla" };
		int[] to = new int[] { android.R.id.text1, android.R.id.text2 };
		
		
		SimpleAdapter adapter = new SimpleAdapter(this, datos, layout, from, to);
		
		setListAdapter(adapter);
		
		
		
	}

	/**
	 * @see android.app.ListActivity#onListItemClick(android.widget.ListView, android.view.View, int, long)
	 */
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		// creamos un intent para abrir la activity deseada
		Intent intent = new Intent (this, ArticleDetailActivity.class);
		
		HashMap<String,String> linea=(HashMap<String, String>) l.getItemAtPosition(position);
		
		//System.out.println(linea.toString());
		
		intent.putExtra ("articulo", (HashMap<String,String>) linea);
				
		startActivity (intent);
	}
	
		
		
	private ArrayList<HashMap<String, String>> cargarNoticias() {
		
		ArrayList<HashMap<String, String>> datos = new ArrayList<HashMap<String, String>>() ;
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("titular", "titular 1");
		map.put("entradilla", "entradilla 1");
		map.put("fecha", "1-1-2012");
		map.put("texto", "texto 1");
		datos.add(map);
				
		HashMap<String, String> map2 = new HashMap<String, String>();
		map2.put("titular", "titular 2");
		map2.put("entradilla", "entradilla 2");
		map2.put("fecha", "1-1-2012");
		map2.put("texto", "texto 2");
		datos.add(map2);
		
		HashMap<String, String> map3 = new HashMap<String, String>();
		map3.put("titular", "titular 3");
		map3.put("entradilla", "entradilla 3");
		map3.put("fecha", "1-1-2012");
		map3.put("texto", "texto 3");
		datos.add(map3);
		
		//System.out.println(datos.toString());
		return datos;
		
	}
	
	
	
	
	
	
	
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.article_list, menu);
        return true;
    }
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle item selection
	    switch (item.getItemId()) {
	        case R.id.aboutscreen:
	            this.showAboutScreen();
	            return true;
	        
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
	
	private void showAboutScreen() {
		Context context = this;
		Intent intent = new Intent(context, AboutScreenActivity.class);
		startActivity(intent);
	}

}


