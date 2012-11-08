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
	public void testDelete() {
		Uri newUri = insertPrueba();		
		assertNotNull(newUri);		
		mContentResolver.delete(newUri,null,null);		
		Cursor cursor = mContentResolver.query(newUri, null, null, null, null);		
		assertEquals(cursor.getCount(), 0);
		cursor.close();
	}
	private Uri insertPrueba() {
		ContentValues values = new ContentValues();
		values.put(MembersContract.UsersTable.USERNAME, "Clark Ken");
		values.put(MembersContract.UsersTable.EMAIL, "super@man.com");
		Uri uri = UsersTable.getUri();
		Uri newUri = mContentResolver.insert(uri, values);
		return newUri;
	}

	public void testUpdate() {
		Uri newUri = insertPrueba();
		assertNotNull(newUri);
		ContentValues values = new ContentValues();
		values.put(MembersContract.UsersTable.USERNAME, "Lois Lane");
		values.put(MembersContract.UsersTable.EMAIL, "super@woman.com");
		int num = mContentResolver.update(newUri, values,null,null);
		assertNotNull(num);
		
	}


}

