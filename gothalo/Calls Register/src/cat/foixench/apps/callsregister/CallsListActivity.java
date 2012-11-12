package cat.foixench.apps.callsregister;

import android.app.ListActivity;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import cat.foixench.apps.callsregister.data.CallsContract.IncommingTable;
import cat.foixench.apps.callsregister.widget.CallsCursorAdapter;

public class CallsListActivity extends ListActivity {

	private CallsCursorAdapter adapter;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calls_list);
        adapter = new CallsCursorAdapter (this);
        setListAdapter (adapter);
		
    }
    

    /* (non-Javadoc)
	 * @see android.app.Activity#onStart()
	 */
	@Override
	protected void onStart() {
		super.onStart();
		adapter.swapCursor(this.getCursor ());
	}

	/* (non-Javadoc)
	 * @see android.app.Activity#onStop()
	 */
	@Override
	protected void onStop() {
		super.onStop();
		adapter.swapCursor (null);
	}


	private Cursor getCursor () {
		// recuperamos la uri del content provider de llamadas
		Uri uri = IncommingTable.getUri ();
		// queremos una lista ordenada por fecha
		String sortOrder = IncommingTable.COLUMN_CALL_DATE + " DESC ";
		
		return this.getContentResolver().query(uri, null, null, null, sortOrder);
		
	}

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_calls_list, menu);
        return true;
    }
}
