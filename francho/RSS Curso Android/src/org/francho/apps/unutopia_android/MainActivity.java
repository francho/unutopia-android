package org.francho.apps.unutopia_android;

import org.francho.apps.unutopia_android.ArticleListFragment.ArticlesListener;
import org.francho.apps.unutopia_android.app.RssIntent;

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
			startActivity(RssIntent.getAboutIntent());
			return true;
		default:
			return super.onMenuItemSelected(featureId, item);
		}

	}

	@Override
	public void onArticleSelected(String link) {
		if(mTwoPane) {
			final Bundle arguments = new Bundle();
			arguments.putString(RssIntent.EXTRA_ARTICLE_LINK, link);
			
			final ArticleDetailFragment fragment = new ArticleDetailFragment();
			fragment.setArguments(arguments);
			
			final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
			transaction.replace(R.id.article_detail_container, fragment);
			transaction.commit();
		} else {
			final Intent intent = RssIntent.getArticleIntent(link);
			startActivity(intent);
		}
	}

}
