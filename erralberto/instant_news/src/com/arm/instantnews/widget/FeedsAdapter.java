package com.arm.instantnews.widget;

import com.arm.instantnews.R;
import com.arm.instantnews.data.FeedContract;
import com.arm.instantnews.data.FeedDbHelper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.widget.SimpleCursorAdapter;
import android.text.format.DateUtils;
import android.widget.TextView;

public class FeedsAdapter extends SimpleCursorAdapter  {
	private static final String[] FROM = new String[] { FeedContract.Articles.TITLE, FeedContract.Articles.PUB_DATE };
	private static final int[] TO = new int[] { R.id.feed_listitem_title,
			R.id.feed_listitem_date };
	private Context context;

	public FeedsAdapter(Context context) {
		super(context, R.layout.feed_listitem , null, FROM, TO, FLAG_REGISTER_CONTENT_OBSERVER);
		
		this.context = context;
		initArticlesCursor(context);
	}

	private void initArticlesCursor(Context context) {
		final FeedDbHelper helper = new FeedDbHelper(context);
		final SQLiteDatabase db = helper.getReadableDatabase();
		
		String table = FeedContract.Articles.TABLE_NAME;
		String[] columns = new String[]{FeedContract.Articles._ID, FeedContract.Articles.TITLE, FeedContract.Articles.PUB_DATE};
		String selection = null;
		String[] selectionArgs = null;
		String groupBy = null;
		String having = null;
		String orderBy = FeedContract.Articles.PUB_DATE + " DESC";		
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
		return v.getId() == R.id.feed_listitem_date;
	}
	
	private String getFormattedDate(String text) {
		Long millis = Long.parseLong(text);
		
		return (String)DateUtils.getRelativeTimeSpanString(context, millis);
	}

}

