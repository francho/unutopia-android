package org.francho.unutopia.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MyDetailFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View rootView = inflater.inflate(R.layout.fragment_detail, container, false);
		
		TextView textView = (TextView) rootView.findViewById(R.id.textNumber);
		
		if(getArguments() != null) {
			String textNumber = getArguments().getString("TEXTNUMBER");
			textView.setText(textNumber);
		}
		
		return rootView;
	}

}
