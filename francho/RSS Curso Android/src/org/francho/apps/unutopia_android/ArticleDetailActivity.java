package org.francho.apps.unutopia_android;

import org.francho.apps.unutopia_android.app.AppIntent;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

public class ArticleDetailActivity extends FragmentActivity {


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_article_detail);

		if (savedInstanceState == null) {
			long id = getIntent().getLongExtra(AppIntent.EXTRA_ARTICLE_ID, -1);
			Bundle arguments = new Bundle();
			arguments.putLong(AppIntent.EXTRA_ARTICLE_ID, id);
			
			ArticleDetailFragment fragment = new ArticleDetailFragment();
			fragment.setArguments(arguments);
			
			FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
			transaction.replace(R.id.article_detail_container, fragment);
			transaction.commit();
		}
	}
}
