package carloscsanchezperez.gmail.sixpackon;

import carloscsanchezperez.gmail.sixpackon.FeedsAdapter;
import carloscsanchezperez.gmail.sixpackon.MembersContract.FeedsTable;
//------------------------------------------
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import android.os.Bundle;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.SimpleAdapter;
import android.app.ListActivity;
import android.widget.ListView;

import android.util.Log;




public class ArticleListActivity extends ListActivity {
   
	   
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// Tiene q linkar el layout xml con la Activity
		setContentView(R.layout.articlelist);

				
		final FeedsAdapter adapter = new FeedsAdapter(this);

		setListAdapter(adapter);
	
		

	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		Intent intent = AppIntent.getArticleIntent(id);
		
		startActivity(intent);
	}



	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.articlelist, menu);
		return true;
	}

	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu_about:
			startActivity(AppIntent.getAboutIntent());
			return true;
		default:
			return super.onMenuItemSelected(featureId, item);
		}

	}

	// This method is: manual load data
	private SimpleAdapter getAdapterrrrr () {

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
