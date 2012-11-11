/**
 * 
 */
package carloscsanchezperez.gmail.sixpackon;


import carloscsanchezperez.gmail.sixpackon.MembersContract.FeedsTable;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.widget.TextView;

public class ArticleDetailActivity extends Activity  {
	   //title + link + pubDate + description + content
	   //title + link + pubDate + description + content
	   private TextView titleView;
	   private TextView linkView;
	   private TextView dateView;
	   private TextView descriptionView;
	   private TextView contentView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView (R.layout.articledetail);
		
		// recuperamos los parametros via Intent
		//Intent intent = this.getIntent ();
		//String text = intent.getStringExtra("SixPackOn RSS");
		
		//TextView detailTitle = (TextView) findViewById (R.id.title);

		//detailTitle.setText (text);

		//--------------------------------------------
		
		//title + link + pubDate + description + content
		titleView = (TextView) findViewById(R.id.title);
		linkView = (TextView) findViewById(R.id.link);
		dateView = (TextView) findViewById(R.id.pubDate);
		descriptionView = (TextView) findViewById(R.id.description);
		contentView = (TextView) findViewById(R.id.content);
		
	
		
		//---------------------------------------------
		
		
		
	}
	
	@Override
	protected void onStart() {
		super.onStart();

		final long id = getIntent().getLongExtra(AppIntent.EXTRA_ID, -1);
		
		loadArticle(id);
	}

	private void loadArticle(long id) {
		final DbHelper dbHelper = new DbHelper(this);
		final SQLiteDatabase db = dbHelper.getReadableDatabase();
		
		final String table = FeedsTable.TABLE_NAME;
		final String[] columns = null;
		final String selection = FeedsTable._ID + "=?";
		final String[] selectionArgs = new String[]{ ""+id };
		final String groupBy = null;
		final String having = null;
		final String orderBy = null;
		
		final Cursor cursor = db.query(table, columns, selection, selectionArgs, groupBy, having, orderBy);
		cursor.moveToFirst();
		
		final String title = cursor.getString(cursor.getColumnIndex(FeedsTable.TITLE));
		titleView.setText(title);

		final String link = cursor.getString(cursor.getColumnIndex(FeedsTable.LINK));
		linkView.setText(title);

		final long pubdate = cursor.getLong(cursor.getColumnIndex(FeedsTable.PUBDATE));
		dateView.setText(DateUtils.getRelativeTimeSpanString(this, pubdate));

		final String description = cursor.getString(cursor.getColumnIndex(FeedsTable.DESCRIPTION));
		descriptionView.setText(title);
		
		final String content = cursor.getString(cursor.getColumnIndex(FeedsTable.CONTENT));
		contentView.setText(title);

		cursor.close();
	}

	private CharSequence parseDate(long pubdate) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
