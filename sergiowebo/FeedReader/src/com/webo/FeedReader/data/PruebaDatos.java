package com.webo.FeedReader.data;

import java.util.ArrayList;

import com.webo.FeedReader.ArticleItem;

public class PruebaDatos {

	public ArrayList<ArticleItem> PruebaDatos() {
		ArrayList<ArticleItem> listItems = new ArrayList<ArticleItem>();
		
		listItems.add(new ArticleItem(1, "Titulo 1", "Autor 1", "26/06/2012"));
		listItems.add(new ArticleItem(2, "Titulo 2", "Autor 2", "26/06/2012"));
		listItems.add(new ArticleItem(3, "Titulo 3", "Autor 2", "26/06/2012"));
		
		return listItems;
	}

}
