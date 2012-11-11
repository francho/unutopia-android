package org.francho.unutopia.mycontenterprovider.test;

import org.francho.unutopia.mycontenterprovider.data.MembersContract;
import org.francho.unutopia.mycontenterprovider.data.MembersContract.UsersTable;
import org.francho.unutopia.mycontenterprovider.data.MyContentProvider;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.test.ProviderTestCase2;
import android.test.mock.MockContentResolver;

public class MyContentProviderTest extends ProviderTestCase2<MyContentProvider> {

	private MockContentResolver mContentResolver;

	public MyContentProviderTest() {
		super(MyContentProvider.class, MembersContract.AUTHORITY);	
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		
		mContentResolver = getMockContentResolver();
	}
	
	public void testUsersUri() {
		Uri expected = Uri.parse("content://org.francho.unutopia.mycontentprovider/users");
		Uri actual = UsersTable.getUri();
		
		assertEquals(expected, actual);
	}
	
	public void testInsert() {
		ContentValues values = new ContentValues();
		values.put(MembersContract.UsersTable.USERNAME, "Clark Ken");
		values.put(MembersContract.UsersTable.EMAIL, "super@man.com");
		
		Uri uri = UsersTable.getUri();
		
		Uri newUri = mContentResolver.insert(uri, values);
		
		assertNotNull(newUri);
	}
	
	public void testSelectOne() {
		ContentValues values = new ContentValues();
		values.put(MembersContract.UsersTable.USERNAME, "Clark Ken");
		values.put(MembersContract.UsersTable.EMAIL, "super@man.com");
		
		Uri uri = UsersTable.getUri();
		
		Uri newUri = mContentResolver.insert(uri, values);
		
		assertNotNull(newUri);
		
		Cursor cursor = mContentResolver.query(newUri, null, null, null, null);
		assertEquals(cursor.getCount(), 1);
		
		cursor.moveToFirst();
		String email = cursor.getString(cursor.getColumnIndex(UsersTable.EMAIL));
		String name = cursor.getString(cursor.getColumnIndex(UsersTable.USERNAME));
		
		assertEquals("Clark Ken", name);
		assertEquals("super@man.com",email);
		
		cursor.close();
	}
	
	/**
	 * lanza un test en el que se actualiza un registro de la base de datos.
	 */
	public void testUpdateOne () {
		ContentValues values = new ContentValues ();
		
		values.put (MembersContract.UsersTable.USERNAME, "Peter Parker");
		values.put (MembersContract.UsersTable.EMAIL, "spider@man");
		
		// obtenemos la uri para acceder al registro con _id = 1;
		Uri uri = UsersTable.getUri (1);
		
		int testResult = mContentResolver.update (uri, values, null , null );
		
		assertFalse (testResult == -1);
		
		
	}

	/**
	 * Lanza un test en el que se realiza una actualizaci—n masiva de los datos de la tabla UsersTabla.TABLE_NAME
	 */
	public void testMassiveUpdate () {
		ContentValues values = new ContentValues ();
		
		values.put (MembersContract.UsersTable.USERNAME, "Peter Parker");
		values.put (MembersContract.UsersTable.EMAIL, "spider@man");
		
		// obtenemos la uri del content provider
		Uri uri = UsersTable.getUri ();
		
		int testResult = mContentResolver.update (uri, values, null , null );
		
		assertFalse (testResult == -1);
		
	}

	/**
	 * Lanza un test en el que se elimina un registro de la tabla UsersTabla.TABLE_NAME
	 */
	public void testDeleteOne () {
		// recuperamos la uri para el registro con id = 1;
		Uri uri = UsersTable.getUri (1);
		
		// lanzamos el test de borrado
		int testResult = mContentResolver.delete (uri, null, null);
		
		assertFalse (testResult == -1);
		
	}
	
	/**
	 * Lanza un test en el que se realiza una borrado masivo de los datos de la tabla UsersTabla.TABLE_NAME
	 */
	public void testMassiveDelete () {
		// recuperamos la uri del content provider
		Uri uri = UsersTable.getUri ();
		
		// lanzamos el test de borrado
		int testResult = mContentResolver.delete (uri, UsersTable.USERNAME + " = 'Clark Kent'" , null);
		
		assertFalse (testResult == -1);
		
	}

}
