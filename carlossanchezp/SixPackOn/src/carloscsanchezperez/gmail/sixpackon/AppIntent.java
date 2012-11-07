package carloscsanchezperez.gmail.sixpackon;

import android.content.Intent;

public class AppIntent extends Intent {

	private static final String ACTION_LIST_ARTICLES = "sixpackon.intent.category.LIST_ARTICLES";
	private static final String ACTION_LIST_ABOUT = "sixpackon.intent.category.ABOUT";
	private static final String ACTION_VIEW_ARTICLE = "sixpackon.intent.category.VIEW_ARTICLE";
	public static final String EXTRA_ID = "sixpackon.intent.EXTRA_ID";

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
