package org.cacahuete.app.feedreader.db;

import android.net.Uri;
import android.provider.BaseColumns;

public class RssContract {
	public static final String DB_NAME="feedreader.db";
	public static final String AUTHORITY = "org.cacahuete.app.feedreader.rsscontentprovider";
	
	private RssContract() {
		
		
	}
	

	
	
	public static class ArticlesTable implements BaseColumns{
		private ArticlesTable() {}
		public static final String TABLE_NAME = "articles";
		
		public static final String TITLE="title";
		public static final String LINK="link";
		public static final String PUBDATE="pubDate";
		public static final String TS_DATE="ts_date";
		public static final String DESCRIPTION="description";
		public static final String CONTENT="content";
		
		public static Uri getUri() {
			return Uri.parse("content://"+AUTHORITY+"/articles");
		}

		public static Uri getUri(long id) {
			return Uri.parse("content://"+AUTHORITY+"/articles/"+id);
		}
		
	}
	
	public static class FeedsTable implements BaseColumns{
		private FeedsTable() {}
		public static final String TABLE_NAME = "feeds";
		
		public static final String URL="url";
		public static final String LASTCHECKTIMESTAMP="lastCheckTimestamp";
		public static final String NAME="name";
		public static final String DESCRIPTION="description";
		
		public static Uri getUri() {
			return Uri.parse("content://"+AUTHORITY+"/feeds");
		}

		public static Uri getUri(long id) {
			return Uri.parse("content://"+AUTHORITY+"/feeds/"+id);
		}
		
	}
	
}