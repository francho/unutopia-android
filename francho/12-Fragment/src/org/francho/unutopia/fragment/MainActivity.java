package org.francho.unutopia.fragment;

import org.francho.unutopia.fragment.MyListFragment.OnNumberListener;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;

public class MainActivity extends FragmentActivity implements OnNumberListener {

	private boolean twoColumns;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// getFragmentManager();
		MyListFragment fragment = (MyListFragment) getSupportFragmentManager()
				.findFragmentById(R.id.fragment_list);

		fragment.setNumberListener(this);

		if (getSupportFragmentManager().findFragmentById(R.id.fragment_detail) != null) {
			twoColumns = true;
		} else {
			twoColumns = false;
		}

	}

	@Override
	public void onNumberSelected(String numberText) {
		Bundle args = new Bundle();
		args.putString("TEXTNUMBER", numberText);

		if (twoColumns) {
			MyDetailFragment detail = new MyDetailFragment();
			detail.setArguments(args);
			FragmentTransaction transaction = getSupportFragmentManager()
					.beginTransaction();
			transaction.replace(R.id.fragment_detail, detail);
			transaction.commit();
		} else {
			Intent intent = new Intent(this, DetailActivity.class);
			intent.putExtras(args);
			startActivity(intent);
		}
	}

}
