package org.francho.apps.unutopia_android.data;

import java.util.ArrayList;
import java.util.HashMap;

import android.text.format.Time;

public class DummyFeeds extends ArrayList<HashMap<String,Object>> {
	public DummyFeeds () {
		addFeed("Art’culo 1", "2012-10-13T16:00:00.000Z");
		addFeed("Otro art’culo", "2012-10-13T16:00:00.000Z");
		addFeed("M‡s de lo mismo", "2012-10-17T15:00:00.000Z");
		addFeed("Otro", "2012-10-13T10:30:00.000Z");
	}
	
	public void addFeed(String title, String date) {
		HashMap<String, Object> feedMap = new HashMap<String,Object>();
		
		feedMap.put(FeedContract.TITLE, title);
		
		Long millis = parseDate(date);
		feedMap.put(FeedContract.DATE, millis);

		add(feedMap);
	}

	private Long parseDate(String date) {
		Time time = new  Time();
		time.parse3339(date);
		Long millis = time.normalize(false);
		return millis;
	}
}
