package org.francho.apps.unutopia_android;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

public class ArticleDetailActivity extends FragmentActivity {


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_article_detail);

		if (savedInstanceState == null) {
			ArticleDetailFragment fragment = new ArticleDetailFragment();
			fragment.setArguments(getIntent().getExtras());
			
			FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
			transaction.replace(R.id.article_detail_container, fragment);
			transaction.commit();
		}
	}
}
