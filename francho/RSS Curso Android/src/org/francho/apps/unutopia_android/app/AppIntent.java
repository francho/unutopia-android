package org.francho.apps.unutopia_android.app;

import android.content.Intent;

public class AppIntent extends Intent {

	private static final String ACTION_LIST_ARTICLES = "unutopia.intent.category.LIST_ARTICLES";
	private static final String ACTION_LIST_ABOUT = "unutopia.intent.category.ABOUT";
	private static final String ACTION_VIEW_ARTICLE = "unutopia.intent.category.VIEW_ARTICLE";
	public static final String EXTRA_ID = "unutopia.intent.EXTRA_ID";

	public static Intent getArticleListIntent() {
		return new Intent(ACTION_LIST_ARTICLES);
	}

	public static Intent getAboutIntent() {
		return new Intent(ACTION_LIST_ABOUT);
	}

	public static Intent getArticleIntent(long id) {
		Intent intent = new Intent(ACTION_VIEW_ARTICLE);
		intent.putExtra(EXTRA_ID, id);

		return intent;
	}

}
