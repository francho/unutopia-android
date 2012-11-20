package org.francho.unutopia.fragment;

import org.francho.unutopia.fragment.MyListFragment.OnNumberListener;

import android.os.Bundle;
import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;

public class DetailActivity extends FragmentActivity  {

	private boolean twoColumns;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail);
		

		MyDetailFragment detail = new MyDetailFragment();
		detail.setArguments(getIntent().getExtras());
		FragmentTransaction transaction = getSupportFragmentManager()
				.beginTransaction();
		transaction.replace(R.id.fragment_detail, detail);
		transaction.commit();
	}

}
