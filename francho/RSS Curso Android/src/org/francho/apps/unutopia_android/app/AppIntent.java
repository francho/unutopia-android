package org.francho.apps.unutopia_android.app;

import android.content.Intent;
import android.net.Uri;

public class AppIntent extends Intent {

	private static final String ACTION_LIST_ARTICLES = "unutopia.intent.category.LIST_ARTICLES";
	private static final String ACTION_LIST_ABOUT = "unutopia.intent.category.ABOUT";
	private static final String ACTION_VIEW_ARTICLE = "unutopia.intent.category.VIEW_ARTICLE";

	public static Intent getArticleListIntent() {
		return new Intent(ACTION_LIST_ARTICLES);
	}

	public static Intent getAboutIntent() {
		return new Intent(ACTION_LIST_ABOUT);
	}

	public static Intent getArticleIntent(String title) {
		Intent intent = new Intent(ACTION_VIEW_ARTICLE);
		intent.putExtra(EXTRA_TEXT, title);
		
		return intent;
	}

}
