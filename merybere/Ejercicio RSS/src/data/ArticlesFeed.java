package data;

import java.net.URL;
import java.util.ArrayList;


import nl.matshofman.saxrssreader.RssFeed;
import nl.matshofman.saxrssreader.RssItem;
import nl.matshofman.saxrssreader.RssReader;

import android.util.Log;

public class ArticlesFeed {

	private static final String TAG = "ArticlesFeed";

	// Tarea pesada para ejecutar en segundo plano que accede a los feeds y los carga en la BD
	public void loadNewArticles() {
		Log.d(TAG, "load feed start");
		
		URL url;
		RssFeed feed;
		try {
			url = new URL("http://gozarte.wordpress.com/feed/");
			feed = RssReader.read(url);
			
			ArrayList<RssItem> rssItems = feed.getRssItems();
			for(RssItem rssItem : rssItems) {
			    Log.i("RSS Reader", rssItem.getTitle());
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		Log.d(TAG, "load feed stop");
	}
}
