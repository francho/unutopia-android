package org.francho.apps.unutopia_android.app;

import android.content.Intent;
import android.net.Uri;

public class AppIntent extends Intent {

	private static final String ACTION_LIST_ARTICLES = "unutopia.intent.category.LIST_ARTICLES";
	private static final String ACTION_LIST_ABOUT = "unutopia.intent.category.ABOUT";

	public static Intent getArticleListIntent() {
		return new Intent(ACTION_LIST_ARTICLES);
	}

	public static Intent getAboutIntent() {
		return new Intent(ACTION_LIST_ABOUT);
	}

}
