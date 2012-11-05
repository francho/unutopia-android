package carloscsanchezperez.gmail.sixpackon;

import carloscsanchezperez.gmail.sixpackon.MembersContract.FeedsTable;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.text.format.Time;

public class DummyFeeds {
	public static void insertDummyFeeds(SQLiteDatabase db) {
		// title + link + pubDate + description + content
		addFeed(db, "Title 1", "carlossanchezp.wordpress.com", "2012-11-23T16:00:00.000Z", "Desc 1","Content 1");
		addFeed(db, "Title 2", "carlossanchezp.wordpress.com", "2012-11-13T16:00:00.000Z", "Desc 2","Content 2");
		addFeed(db, "Title 3", "carlossanchezp.wordpress.com", "2012-11-27T15:00:00.000Z", "Desc 3","Content 3");
		addFeed(db, "Title 4", "carlossanchezp.wordpress.com", "2012-11-08T10:30:00.000Z", "Desc 4","Content 4");
	}
	
	
	public static void addFeed(SQLiteDatabase db, String title, String link, String date, String description, String content) {
		ContentValues values = new ContentValues();
		
		values.put(FeedsTable.TITLE, title);
		values.put(FeedsTable.LINK, link);
		Long millis = parseDate(date);
		values.put(FeedsTable.PUBDATE, millis);
		values.put(FeedsTable.DESCRIPTION, description);
		values.put(FeedsTable.CONTENT, content);
		// Insert into the Data Base
		db.insert(FeedsTable.TABLE_NAME, null, values);
	}

	private static Long parseDate(String date) {
		Time time = new  Time();
		time.parse3339(date);
		Long millis = time.normalize(false);
		return millis;
	}
}
