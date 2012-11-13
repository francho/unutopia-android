package cat.foixench.apps.callsregister.widget;

import android.content.Context;
import android.support.v4.widget.SimpleCursorAdapter;
import android.text.format.DateUtils;
import android.widget.TextView;
import cat.foixench.apps.callsregister.data.CallsContract.IncommingTable;


public class CallsCursorAdapter extends SimpleCursorAdapter {
	
	private static final String FROM [] = new String [] {IncommingTable.COLUMN_PHONE, IncommingTable.COLUMN_CALL_DATE};
    private static final int TO [] = new int [] {android.R.id.text1, android.R.id.text2};
    
    private Context context;
	
    public CallsCursorAdapter (Context context) {
		super (context, android.R.layout.two_line_list_item, null, FROM, TO, FLAG_REGISTER_CONTENT_OBSERVER);
		// gurardamos el contexto para futuras necesidades
		this.context = context;
	}
    
    @Override
	public void setViewText(TextView v, String text) {
		
		if (v.getId () == android.R.id.text2) {
			
			text = (String) DateUtils.getRelativeTimeSpanString (this.context,  Long.parseLong (text));
		}
		super.setViewText(v, text);
	}

}
