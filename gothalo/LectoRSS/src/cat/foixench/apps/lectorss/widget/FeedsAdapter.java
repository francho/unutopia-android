package cat.foixench.apps.lectorss.widget;

import cat.foixench.apps.lectorss.R;
import cat.foixench.apps.lectorss.db.RssContract.FeedsTable;
import cat.foixench.apps.lectorss.utils.Utils;
import android.content.Context;
import android.support.v4.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class FeedsAdapter extends SimpleCursorAdapter {
	
	private static final String [] FROM = new String [] {FeedsTable.COLUMN_TITLE, FeedsTable.COLUMN_AUTHOR, FeedsTable.COLUMN_PUB_DATE};
	private static final int [] TO = new  int [] {R.id.title, R.id.author, R.id.date};

	Context context;
	
	public FeedsAdapter (Context context) {
		super (context, R.layout.articlelistitem, null, FROM, TO, FLAG_REGISTER_CONTENT_OBSERVER);
		// gurardamos el contexto para futuras necesidades
		this.context = context;
	}


	/** 
	 * fija la vista de texto.
	 * @see android.support.v4.widget.SimpleCursorAdapter#setViewText(android.widget.TextView, java.lang.String)
	 */
	@Override
	public void setViewText(TextView v, String text) {
		
		if (v.getId () == R.id.date) {
			text = Utils.millisToDate (context, text);
		}
		super.setViewText(v, text);
	}

 
}
