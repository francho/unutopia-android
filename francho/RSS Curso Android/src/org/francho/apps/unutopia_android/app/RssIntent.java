package org.francho.apps.unutopia_android.app;

import android.content.Intent;

public class RssIntent extends Intent {

	public static final String ACTION_LIST_ARTICLES = "unutopia.intent.action.LIST_ARTICLES";
	public static final String ACTION_LIST_ABOUT = "unutopia.intent.action.ABOUT";
	public static final String ACTION_VIEW_ARTICLE = "unutopia.intent.action.VIEW_ARTICLE";
	public static final String ACTION_SYNC_ARTICLES = "unutopia.intent.action.SYNC_ARTICLES";
	
	public static final String EXTRA_ARTICLE_LINK = "unutopia.intent.EXTRA_LINK";
	
	public static Intent getArticleListIntent() {
		return new Intent(ACTION_LIST_ARTICLES);
	}

	public static Intent getAboutIntent() {
		return new Intent(ACTION_LIST_ABOUT);
	}

	public static Intent getArticleIntent(String link) {
		Intent intent = new Intent(ACTION_VIEW_ARTICLE);
		intent.putExtra(EXTRA_ARTICLE_LINK, link);

		return intent;
	}

	public static Intent getSyncArticlesIntent() {
		return new Intent(ACTION_SYNC_ARTICLES);
	}
}
