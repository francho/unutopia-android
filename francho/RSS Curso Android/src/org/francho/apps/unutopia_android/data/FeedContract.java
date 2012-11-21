package org.francho.apps.unutopia_android.data;

import android.net.Uri;
import android.provider.BaseColumns;

public class FeedContract {

	public static final String DB_NAME = "feeds.db";
	public static final String AUTHORITY = "org.francho.unutopia_android";

	public static class Articles implements BaseColumns {
		private Articles() {
		}
		
		public static final String TABLE_NAME = "articles";

		public static final String TITLE = "title";
		public static final String LINK = "link";
		public static final String PUB_DATE = "pubdate";
		public static final String DESCRIPTION = "description";
		public static final String CONTENT = "content";
		
		public static Uri getUri() {
			return Uri.parse("content://"+AUTHORITY+"/feeds");
		}

		public static Uri getUri(long id) {
			return Uri.parse("content://"+AUTHORITY+"/feeds/"+id);
		}
	}
}
