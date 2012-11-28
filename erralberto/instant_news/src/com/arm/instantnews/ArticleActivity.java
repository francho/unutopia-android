package com.arm.instantnews;

import com.arm.instantnews.app.AppIntent;
import com.arm.instantnews.data.FeedContract;
import com.arm.instantnews.data.FeedDbHelper;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.widget.TextView;

public class ArticleActivity extends Activity {

	private TextView titleView;
	private TextView dateView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_article);

		titleView = (TextView) findViewById(R.id.article_title);
		dateView = (TextView) findViewById(R.id.article_date);
	}

	@Override
	protected void onStart() {
		super.onStart();

		final long id = getIntent().getLongExtra(AppIntent.EXTRA_ID, -1);
		
		loadArticle(id);
	}

	private void loadArticle(long id) {
		final FeedDbHelper dbHelper = new FeedDbHelper(this);
		final SQLiteDatabase db = dbHelper.getReadableDatabase();
		
		final String table = FeedContract.Articles.TABLE_NAME;
		final String[] columns = null;
		final String selection = FeedContract.Articles._ID + "=?";
		final String[] selectionArgs = new String[]{ ""+id };
		final String groupBy = null;
		final String having = null;
		final String orderBy = null;
		
		final Cursor cursor = db.query(table, columns, selection, selectionArgs, groupBy, having, orderBy);
		cursor.moveToFirst();
		
		final String title = cursor.getString(cursor.getColumnIndex(FeedContract.Articles.TITLE));
		titleView.setText(title);
		
		final long pubdate = cursor.getLong(cursor.getColumnIndex(FeedContract.Articles.PUB_DATE));
		dateView.setText(DateUtils.getRelativeTimeSpanString(this, pubdate));
		
		cursor.close();
	}

	@SuppressWarnings("unused")
	private CharSequence parseDate(long pubdate) {
		
		return null;
	}

}

