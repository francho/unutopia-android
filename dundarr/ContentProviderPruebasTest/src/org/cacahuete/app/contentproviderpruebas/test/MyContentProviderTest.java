package org.cacahuete.app.contentproviderpruebas.test;

import org.cacahuete.app.contentproviderpruebas.data.MembersContract;
import org.cacahuete.app.contentproviderpruebas.data.MembersContract.UsersTable;
import org.cacahuete.app.contentproviderpruebas.data.MyContentProvider;

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
		Uri expected = Uri.parse("content://org.cacahuete.app.contentproviderpruebas/users");
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
	
	public void testUpdate() {
		Uri newUri = insertPrevio();
		assertNotNull(newUri);
		
		ContentValues values = new ContentValues();
		values.put(MembersContract.UsersTable.USERNAME, "modificado");
		values.put(MembersContract.UsersTable.EMAIL, "super@modificado.com");
		
		int num = mContentResolver.update(newUri, values,null,null);
		assertNotNull(num);
		
	}
	
	public void testDelete() {
				
		Uri newUri = insertPrevio();
		assertNotNull(newUri);
		mContentResolver.delete(newUri,null,null);
		
		Cursor cursor = mContentResolver.query(newUri, null, null, null, null);
		assertEquals(cursor.getCount(), 0);
		
	}
	
	private Uri insertPrevio() {
		ContentValues values = new ContentValues();
		values.put(MembersContract.UsersTable.USERNAME, "Clark Ken");
		values.put(MembersContract.UsersTable.EMAIL, "super@man.com");
		
		Uri uri = UsersTable.getUri();
		
		Uri newUri = mContentResolver.insert(uri, values);
		return newUri;
		
		
		
	}

}
