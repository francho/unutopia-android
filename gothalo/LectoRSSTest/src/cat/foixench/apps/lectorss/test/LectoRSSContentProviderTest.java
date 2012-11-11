package cat.foixench.apps.lectorss.test;

import cat.foixench.apps.lectorss.db.LectoRSSContentProvider;
import cat.foixench.apps.lectorss.db.RssContract;
import cat.foixench.apps.lectorss.db.RssContract.FeedsTable;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.test.ProviderTestCase2;
import android.test.mock.MockContentResolver;
import android.text.format.Time;


public class LectoRSSContentProviderTest extends ProviderTestCase2 <LectoRSSContentProvider> {

	private MockContentResolver mContentResolver;

	
	public LectoRSSContentProviderTest() {
		super (LectoRSSContentProvider.class, RssContract.AUTHORITY);
		
	}


	/** 
	 * configuracion del test
	 * @see android.test.ProviderTestCase2#setUp()
	 */
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		// obtenemos un content resolver de pruevas.
		this.mContentResolver = getMockContentResolver();
	}
	
	public void testUri () {
		Uri expected = Uri.parse("content://cat.foixench.apps.lectorss/feeds");
		Uri actual = FeedsTable.getUri ();
		
		assertEquals(expected, actual);
	}
	
	public void testInsert () {
		ContentValues values = new ContentValues ();
		
		Uri url = FeedsTable.getUri ();

		Time ahora = new Time ();
		ahora.setToNow();
		
		values.put(FeedsTable.COLUMN_TITLE, "Titulo de prueba");
		values.put(FeedsTable.COLUMN_LINK, "http://www.foo.com");
		values.put(FeedsTable.COLUMN_PUB_DATE, Long.valueOf (ahora.toMillis (true)).toString ());
		values.put(FeedsTable.COLUMN_DESCRIPTION, "En un lugar de la Mancha de cuyo nombre no quiero acordarme, no ha mucho tiempo que viv’a un hidalgo de los de lanza en astillero, adarga antigua, roc’n flaco y galgo corredor.");
		values.put(FeedsTable.COLUMN_AUTHOR, "gothalo");

		Uri newUri = mContentResolver.insert(url, values);
		
		assertNotNull(newUri);
	}
	
	/**
	 * verificamos la funcionalidad de seleccion del content provider
	 */
	public void testQuery () {
		
		// insertamos un par de valores para verificar la query
		ContentValues values = new ContentValues ();
		
		Uri url = FeedsTable.getUri ();

		Time ahora = new Time ();
		ahora.setToNow();
		
		values.put(FeedsTable.COLUMN_TITLE, "Titulo de prueba");
		values.put(FeedsTable.COLUMN_LINK, "http://www.foo.com");
		values.put(FeedsTable.COLUMN_PUB_DATE, Long.valueOf (ahora.toMillis (true)).toString ());
		values.put(FeedsTable.COLUMN_DESCRIPTION, "En un lugar de la Mancha de cuyo nombre no quiero acordarme, no ha mucho tiempo que viv’a un hidalgo de los de lanza en astillero, adarga antigua, roc’n flaco y galgo corredor.");
		values.put(FeedsTable.COLUMN_AUTHOR, "tester");

		Uri newUri = mContentResolver.insert(url, values);
		
		assertNotNull(newUri);
		
		values.clear();
		values.put(FeedsTable.COLUMN_TITLE, "Titulo de prueba2");
		values.put(FeedsTable.COLUMN_LINK, "http://www.fo2.com");
		values.put(FeedsTable.COLUMN_PUB_DATE, Long.valueOf (ahora.toMillis (true)).toString ());
		values.put(FeedsTable.COLUMN_DESCRIPTION, "En un lugar de la Mancha de cuyo nombre no quiero acordarme, no ha mucho tiempo que viv’a un hidalgo de los de lanza en astillero, adarga antigua, roc’n flaco y galgo corredor.");
		values.put(FeedsTable.COLUMN_AUTHOR, "tester");

		newUri = mContentResolver.insert(url, values);
		
		assertNotNull(newUri);
		
		// lanzamos la consulta
		String[] projection = null;
		String selection = FeedsTable.COLUMN_AUTHOR + "= ?";
		String[] selectionArgs = {"tester"};
		String sortOrder = FeedsTable._ID;
		
		Cursor cursor = mContentResolver.query(url, projection, selection, selectionArgs, sortOrder );
		
		cursor.moveToFirst();
		
		assertEquals(cursor.getCount(), 2);
		
		cursor.close ();
	}

	public void testQueryOne () {
		
		// insertamos un registro para verificar la query
		ContentValues values = new ContentValues ();
		
		Uri url = FeedsTable.getUri ();

		Time ahora = new Time ();
		ahora.setToNow();
		
		String title0 = "Titulo de prueba";
		String link0 = "http://www.foo.com";
		long pubDate0 =  ahora.toMillis (true);
		String description0 = "En un lugar de la Mancha de cuyo nombre no quiero acordarme, no ha mucho tiempo que viv’a un hidalgo de los de lanza en astillero, adarga antigua, roc’n flaco y galgo corredor.";
		String author0 = "tester";
		
		values.put(FeedsTable.COLUMN_TITLE, title0);
		values.put(FeedsTable.COLUMN_LINK, link0);
		values.put(FeedsTable.COLUMN_PUB_DATE, pubDate0);
		values.put(FeedsTable.COLUMN_DESCRIPTION, description0);
		values.put(FeedsTable.COLUMN_AUTHOR, author0);

		Uri newUri = mContentResolver.insert(url, values);
		
		assertNotNull(newUri);
		
		Cursor cursor = mContentResolver.query (newUri, null, null, null, null);
		assertEquals (cursor.getCount(), 1);
		
		cursor.moveToFirst();
		String title = cursor.getString (cursor.getColumnIndex (FeedsTable.COLUMN_TITLE));
		String link = cursor.getString (cursor.getColumnIndex (FeedsTable.COLUMN_LINK));
		long pubDate = cursor.getLong (cursor.getColumnIndex (FeedsTable.COLUMN_PUB_DATE));
		String description = cursor.getString (cursor.getColumnIndex (FeedsTable.COLUMN_DESCRIPTION));
		String author = cursor.getString (cursor.getColumnIndex (FeedsTable.COLUMN_AUTHOR));
		
		// verificamos los datos del registro
		assertEquals (title0, title);
		assertEquals (link0, link);
		assertEquals (pubDate0, pubDate);
		assertEquals (description0, description);
		assertEquals (author0, author);
		
		cursor.close ();
		
		
		
		
		
		
	}
	
	
}
