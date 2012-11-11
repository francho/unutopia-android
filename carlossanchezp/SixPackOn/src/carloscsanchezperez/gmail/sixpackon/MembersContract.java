package carloscsanchezperez.gmail.sixpackon;

import android.provider.BaseColumns;

public class MembersContract {
	public static final String DB_NAME="feeds.db";
	
	private MembersContract() {}
	
	public static class FeedsTable implements BaseColumns{
		private FeedsTable() {}
		public static final String TABLE_NAME = "feed";
		
		public static final String TITLE = "title";
		public static final String LINK = "link";
		public static final String PUBDATE = "pubDate";
		public static final String DESCRIPTION = "description";
		public static final String CONTENT = "content";
		
	}
	
}
