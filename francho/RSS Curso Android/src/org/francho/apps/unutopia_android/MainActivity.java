package org.francho.apps.unutopia_android;

import org.francho.apps.unutopia_android.ArticleListFragment.ArticlesListener;
import org.francho.apps.unutopia_android.app.AppIntent;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class MainActivity extends FragmentActivity implements ArticlesListener {

	private boolean mTwoPane = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_article_list);

		if (findViewById(R.id.article_detail_container) != null) {
			mTwoPane = true;

			//((ArticleDetailFragment) getSupportFragmentManager().findFragmentById(
			//		R.id.number_list)).setActivateOnItemClick(true);
		}

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

	@Override
	public void onArticleSelected(long id) {
		if(mTwoPane) {
			final Bundle arguments = new Bundle();
			arguments.putLong(AppIntent.EXTRA_ARTICLE_ID, id);
			
			final ArticleDetailFragment fragment = new ArticleDetailFragment();
			fragment.setArguments(arguments);
			
			final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
			transaction.replace(R.id.article_detail_container, fragment);
			transaction.commit();
		} else {
			final Intent intent = AppIntent.getArticleIntent(id);
			startActivity(intent);
		}
	}

}
