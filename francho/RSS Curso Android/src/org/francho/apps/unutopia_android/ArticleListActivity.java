package org.francho.apps.unutopia_android;

import java.util.HashMap;

import org.francho.apps.unutopia_android.app.AppIntent;
import org.francho.apps.unutopia_android.data.DummyFeeds;
import org.francho.apps.unutopia_android.data.FeedContract;
import org.francho.apps.unutopia_android.widget.FeedsAdapter;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.ListView;

public class ArticleListActivity extends ListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
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
		inflater.inflate(R.menu.activity_articlelist, menu);
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

}
