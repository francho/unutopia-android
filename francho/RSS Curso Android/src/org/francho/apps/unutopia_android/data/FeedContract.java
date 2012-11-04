package org.francho.apps.unutopia_android.data;

import android.provider.BaseColumns;

public class FeedContract {

	public static final String DB_NAME = "feeds.db";

	public static class Articles implements BaseColumns {
		private Articles() {
		}
		
		public static final String TABLE_NAME = "articles";

		public static final String TITLE = "title";
		public static final String LINK = "link";
		public static final String PUB_DATE = "pubdate";
		public static final String DESCRIPTION = "description";
		public static final String CONTENT = "content";
	}
}
