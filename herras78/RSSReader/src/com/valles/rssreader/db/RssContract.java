package com.valles.rssreader.db;

import android.provider.BaseColumns;

	public class RssContract {

		public static final String DB_NAME="rssfeed.db";
	
	private RssContract() {
		
	}
	
	public static class FeedsTable implements BaseColumns{
		private FeedsTable() {}
		
		public static final String TABLE_NAME = "feeds";
		public static final String TITLE = "title";
		public static final String PUB_DATE = "pub_date";
		public static final String IMAGE = "image";
		public static final String DESCRIPTION = "image";
		public static final String CONTENT = "content";
		public static final String AUTOR = "autor";
		public static final String CATEGORY = "category";
		public static final String URL = "url";
		
	}
}
