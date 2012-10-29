package carloscsanchezperez.gmail.sixpackon;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.os.Bundle;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

import android.widget.SimpleAdapter;

import android.app.ListActivity;

import android.widget.ListView;



public class ArticleListActivity extends ListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// Tiene q linkar el layout xml con la Activity
		setContentView(R.layout.articlelist);
		
  		// Acoplamos el adaptador con los datos en articlelist
		SimpleAdapter adapter = this.getAdapter ();
		setListAdapter(adapter);
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
	
		Intent intent = new Intent (this, ArticleDetailActivity.class);
		startActivity (intent);
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.articlelist, menu);
		return true;
	}


	
	private SimpleAdapter getAdapter () {

		String [] cab = new String [] {"title", "author", "date", "info" };
		int [] dat = new  int [] {R.id.title, R.id.author, R.id.date, R.id.info};
		
		// Creamos el array
		List <HashMap <String,String>> values = new ArrayList <HashMap <String,String>> ();
		
		// Datos q vendrán de BBDD o servicios web
		for (int i = 1; i < 5; i++) {
			HashMap <String, String> item = new HashMap <String, String> ();
			item.put("title", "Titular " + i);
			item.put("author", "Autor " + i);
			item.put("date", "12/03/2012 ");
			item.put("info", "Info");
			
			values.add (item);
		}
		
		SimpleAdapter adapter = new SimpleAdapter(this, values, R.layout.articlelistitem, cab, dat);
		
		return adapter;
	}
}
