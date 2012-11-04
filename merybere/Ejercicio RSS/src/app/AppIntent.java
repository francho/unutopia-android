package app;

import data.ArticleContract;
import android.content.Intent;

public class AppIntent extends Intent {

	private static final String ACTION_VIEW_ARTICLE = "unutopia.intent.category.VIEW_ARTICLE";

	//Cargar la activity del detalle de artículo
	public static Intent getArticleIntent(String title) {
		Intent intent = new Intent(ACTION_VIEW_ARTICLE);
		intent.putExtra(ArticleContract.TITLE, title);
		return intent;
	}

}

