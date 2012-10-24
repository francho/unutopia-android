package org.francho.unutopia.listactivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.SimpleAdapter;

public class MyListActivity2 extends ListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		ArrayList<HashMap<String, String>> data = new ArrayList<HashMap<String, String>>() ;
		
		for(int x=0; x<10; x++) {
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("nombre", "Pepe "+x);
			map.put("apellido", "Lopez "+x);
			
			data.add(map);
		}
		
		
		int layout = android.R.layout.simple_list_item_2;
		String[] from = new String[] { "nombre", "apellido" };
		int[] to = new int[] { android.R.id.text1, android.R.id.text2 };
		
		
		SimpleAdapter adapter = new SimpleAdapter(this, data, layout, from, to);
		
		setListAdapter(adapter);
	}

	
}
