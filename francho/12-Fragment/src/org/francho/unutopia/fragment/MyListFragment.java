package org.francho.unutopia.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class MyListFragment extends ListFragment {

	OnNumberListener numberListener = null;

	public interface OnNumberListener {
		public void onNumberSelected(String numberText);
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		Context context = getActivity();
		String[]data = getResources().getStringArray(R.array.numeros);
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(context,
				android.R.layout.simple_list_item_1,
				data);
	
		setListAdapter(adapter);
	}

	
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		ArrayAdapter adapter = (ArrayAdapter) getListAdapter();
		
		String numberText = (String) adapter.getItem(position);
		
		if(numberListener != null) {
			numberListener.onNumberSelected(numberText);
		}
	}



	public void setNumberListener(OnNumberListener numberListener) {
		this.numberListener = numberListener;
	}
}
