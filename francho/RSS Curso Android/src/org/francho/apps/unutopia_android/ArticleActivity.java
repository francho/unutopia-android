package org.francho.apps.unutopia_android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ArticleActivity extends Activity {

	private TextView titleView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_article);

		titleView = (TextView) findViewById(R.id.article_title);
	}

	@Override
	protected void onStart() {
		super.onStart();

		final String title = getIntent().getStringExtra(Intent.EXTRA_TEXT);
		titleView.setText(title);
	}

}
