package org.cacahuete.app.feedreader.db;

import android.provider.BaseColumns;

public class RssContract {
	public static final String DB_NAME="articles.db";
	
	private RssContract() {}
	
	public static class FeedTable implements BaseColumns{
		private FeedTable() {}
		public static final String TABLE_NAME = "articles";
		
		public static final String TITLE="title";
		public static final String LINK="link";
		public static final String PUBDATE="pubDate";
		public static final String DESCRIPTION="description";
		public static final String CONTENT="content";
		
		
	}
	
}