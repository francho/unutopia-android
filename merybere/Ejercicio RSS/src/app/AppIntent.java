package app;

import android.content.Intent;

public class AppIntent extends Intent {

	private static final String ACTION_VIEW_ARTICLE = "unutopia.intent.category.VIEW_ARTICLE";
	private static final String ACTION_ARTICLE_LIST = "unutopia.intent.category.ARTICLE_LIST";
	private static final String ACTION_ABOUT = "unutopia.intent.category.ABOUT";
	public static final String EXTRA_ID = "unutopia.intent.EXTRA_ID";

	public static Intent getArticleListIntent() {
		return new Intent(ACTION_ARTICLE_LIST);
	}
	
	public static Intent getAboutIntent() {
		return new Intent(ACTION_ABOUT);
	}
	
	//Cargar la activity del detalle de artículo
	public static Intent getArticleDetailIntent(long id) {
		Intent intent = new Intent(ACTION_VIEW_ARTICLE);
		intent.putExtra(EXTRA_ID, id);
		return intent;
	}

}

