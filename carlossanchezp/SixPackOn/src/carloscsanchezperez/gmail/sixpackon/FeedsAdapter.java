package carloscsanchezperez.gmail.sixpackon;
import carloscsanchezperez.gmail.sixpackon.MembersContract.FeedsTable;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.widget.SimpleCursorAdapter;
import android.text.format.DateUtils;
import android.widget.TextView;
//import static android.provider.BaseColumns._ID;
//title + link + pubDate + description + content



public class FeedsAdapter extends SimpleCursorAdapter  {
	//private static final String[] FROM = new String[] { FeedsTable.TITLE, FeedsTable.PUBDATE };
	//private static final int[] TO = new int[] { R.id.feed_listitem_title,	R.id.feed_listitem_date };
	private Context context;
	//----------------------------------------------------
	   private static final String[] FROM = new String[] { FeedsTable.TITLE, FeedsTable.LINK, FeedsTable.PUBDATE, FeedsTable.DESCRIPTION, FeedsTable.CONTENT, };
	   private static final int[] TO = new int[] { R.id.title, R.id.link, R.id.pubDate, R.id.description, R.id.content,};
	   
	   //private static String ORDER_BY = TITLE + " DESC";
	   //private SimpleCursorAdapter adapter;
	
	
	//--------------------------------------------------

	public FeedsAdapter(Context context) {
		//super(context, R.layout.articlelistitem , null, FROM, TO, FLAG_REGISTER_CONTENT_OBSERVER);
		super(context, R.layout.articledetail , null, FROM, TO, FLAG_REGISTER_CONTENT_OBSERVER);
		
		this.context = context;
		initFeedsCursor(context);
	}


	private void initFeedsCursor(Context context) {
		final DbHelper helper = new DbHelper(context);
		final SQLiteDatabase db = helper.getReadableDatabase();
		
		String table = FeedsTable.TABLE_NAME;
		//title + link + pubDate + description + content
		String[] columns = new String[]{FeedsTable._ID, FeedsTable.TITLE, FeedsTable.LINK, FeedsTable.PUBDATE, FeedsTable.DESCRIPTION, FeedsTable.CONTENT};
		String selection = null;
		String[] selectionArgs = null;
		String groupBy = null;
		String having = null;
		//String orderBy = FeedsTable.PUBDATE + " DESC";
		String orderBy = FeedsTable.TITLE + " DESC";
		
		Cursor cursor = db.query(table, columns, selection, selectionArgs, groupBy, having, orderBy);
		
		this.swapCursor(cursor);
	}


	@Override
	public void setViewText(TextView v, String text) {
		if(isDateView(v)) {
			text = getFormattedDate(text);
		}
		super.setViewText(v, text);
	}

	private boolean isDateView(TextView v) {
		return v.getId() == R.id.pubDate;
	}
	
	private String getFormattedDate(String text) {
		Long millis = Long.parseLong(text);
		
		return (String)DateUtils.getRelativeTimeSpanString(context, millis);
	}

}
