package cat.foixench.apps.callsregister.test;

import cat.foixench.apps.callsregister.data.CallsContentProvider;
import cat.foixench.apps.callsregister.data.CallsContract;
import cat.foixench.apps.callsregister.data.CallsContract.IncommingTable;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.test.ProviderTestCase2;
import android.test.mock.MockContentResolver;
import android.text.format.Time;

public class CallsContentProviderTest extends ProviderTestCase2<CallsContentProvider> {
	
	private MockContentResolver mContentResolver;

	public CallsContentProviderTest () {
		super (CallsContentProvider.class, CallsContract.AUTHORITY);
	}

	/* (non-Javadoc)
	 * @see android.test.ProviderTestCase2#setUp()
	 */
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		this.mContentResolver = getMockContentResolver();
	}
	
	public void testUri () {
		Uri expected = Uri.parse("content://cat.foixench.callsregister/calls");
		Uri actual = IncommingTable.getUri ();
		
		assertEquals(expected, actual);
		
	}
	
	public void testInsert () {
		Uri url = IncommingTable.getUri ();
		ContentValues values = new ContentValues ();
		
		Time ahora = new Time ();
		ahora.setToNow();
		
		values.put(IncommingTable.COLUMN_PHONE, "933326544");
		values.put(IncommingTable.COLUMN_CALL_DATE, ahora.toMillis (true));
		
		
		Uri newUri = mContentResolver.insert(url, values);
		
		assertNotNull (newUri);
	}
	
	public void testQueryOne () {
		// insertamos un valor de prueba en la base de datos
		Uri url = IncommingTable.getUri ();
		ContentValues values = new ContentValues ();
		
		Time ahora = new Time ();
		ahora.setToNow();
		
		values.put(IncommingTable.COLUMN_PHONE, "933326544");
		values.put(IncommingTable.COLUMN_CALL_DATE, ahora.toMillis (true));
		
		
		Uri newUri = mContentResolver.insert(url, values);
		
		assertNotNull (newUri);
		
		Cursor cursor = mContentResolver.query (newUri, null, null, null, null);
		
		
		boolean cursorWithResults;
		
		if ((cursor != null) && cursor.moveToFirst()) {
			cursorWithResults = true;
		} else {
			cursorWithResults = false;
		}
		
		// verificamos que haya registros y el cursor no esté vacio
		assertTrue(cursorWithResults);
		
		if (cursorWithResults) {
			String newPhone = cursor.getString(cursor.getColumnIndex (IncommingTable.COLUMN_PHONE));
			long newDate = cursor.getLong (cursor.getColumnIndex (IncommingTable.COLUMN_CALL_DATE));
			
			assertEquals (newPhone, "933326544");
			assertEquals (newDate, ahora.toMillis (true));
			
			cursor.close ();
		}
		
	}
	
	public void testQuery () {
		// insertamos dos registros de prueva
		Uri url = IncommingTable.getUri ();
		ContentValues values = new ContentValues ();
		
		Time ahora = new Time ();
		ahora.setToNow();
		
		values.put(IncommingTable.COLUMN_PHONE, "933326544");
		values.put(IncommingTable.COLUMN_CALL_DATE, ahora.toMillis (true));
		
		Uri newUri = mContentResolver.insert(url, values);
		
		assertNotNull (newUri);
		
		ahora = new Time ();
		ahora.setToNow();
		
		values.put(IncommingTable.COLUMN_PHONE, "934210605");
		values.put(IncommingTable.COLUMN_CALL_DATE, ahora.toMillis (true));
		
		newUri = mContentResolver.insert(url, values);
		
		assertNotNull (newUri);
		
		
		// verificamos la query
		Cursor cursor = mContentResolver.query (url, null, null, null, null);
		
		
		boolean cursorWithResults;
		
		if ((cursor != null) && cursor.moveToFirst()) {
			cursorWithResults = true;
		} else {
			cursorWithResults = false;
		}
		
		// verificamos que haya registros y el cursor no esté vacio
		assertTrue(cursorWithResults);
		
		if (cursorWithResults) {
			assertEquals (cursor.getCount (), 2);
			
			cursor.close ();
		}
		
	}
}
