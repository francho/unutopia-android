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

	private Uri insertRecord(String name, String email) {
		ContentValues values = new ContentValues();
		values.put(MembersContract.UsersTable.USERNAME, name);
		values.put(MembersContract.UsersTable.EMAIL, email);

		Uri uri = UsersTable.getUri();

		Uri newUri = mContentResolver.insert(uri, values);
		return newUri;
	}

	public void testUsersUri() {
		Uri expected = Uri
				.parse("content://org.francho.unutopia.mycontentprovider/users");
		Uri actual = UsersTable.getUri();

		assertEquals(expected, actual);
	}

	public void testInsert() {
		Uri newUri = insertRecord("Clark Ken", "super@man.com");

		assertNotNull(newUri);
	}

	public void testSelectOne() {
		Uri newUri = insertRecord("Clark Ken", "super@man.com");

		assertNotNull(newUri);

		Cursor cursor = mContentResolver.query(newUri, null, null, null, null);
		assertEquals(cursor.getCount(), 1);

		cursor.moveToFirst();
		String email = cursor
				.getString(cursor.getColumnIndex(UsersTable.EMAIL));
		String name = cursor.getString(cursor
				.getColumnIndex(UsersTable.USERNAME));

		assertEquals("Clark Ken", name);
		assertEquals("super@man.com", email);

		cursor.close();
	}
	
	public void testSelectMany() {
		insertRecord("Clark Ken", "super@man.com");
		insertRecord("Peter Parker", "spider@man.com");
		insertRecord("Tony Stark","iron@man.com");
		
		Uri uri = UsersTable.getUri();
		Cursor cursor = mContentResolver.query(uri, null, null, null, null);
		assertEquals(cursor.getCount(), 3);
		cursor.close();
		
		cursor = mContentResolver.query(uri, null, UsersTable.USERNAME + " like ?", new String[]{"Tony Stark"}, null);
		assertEquals(cursor.getCount(), 1);
		cursor.close();
	}

	public void testUpdateOne() {
		Uri newUri = insertRecord("Clark Ken", "super@man.com");

	}
	
}
