package com.example.mycspcontentprovider.test;


import com.example.mycspcontentprovider.data.MembersContract;
import com.example.mycspcontentprovider.data.MembersContract.UsersTable;
import com.example.mycspcontentprovider.data.MyContentProvider;


import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.test.ProviderTestCase2;
import android.test.mock.MockContentResolver;

public class MyCspContentProviderTest extends ProviderTestCase2<MyContentProvider> {

	private MockContentResolver mContentResolver;

	public MyCspContentProviderTest() {
		super(MyContentProvider.class, MembersContract.AUTHORITY);	
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		
		mContentResolver = getMockContentResolver();
	}
	
	public void testUsersUri() {
		Uri expected = Uri.parse("content://com.example.mycontentprovider/users");
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
	
	public void testDeleteOne() {
		ContentValues values = new ContentValues();
		values.put(MembersContract.UsersTable.USERNAME, "Clark Ken");
		values.put(MembersContract.UsersTable.EMAIL, "super@man.com");
		
		Uri uri = UsersTable.getUri();
		Uri newUri = mContentResolver.insert(uri, values);
		
		int rowsDeleted = mContentResolver.delete(UsersTable.getUri(1), null, null);
		assertEquals(rowsDeleted, 1);
		}
	
	
	public void testUpdateOne() {
		ContentValues values = new ContentValues();
		values.put(MembersContract.UsersTable.USERNAME, "Clark Ken");
		values.put(MembersContract.UsersTable.EMAIL, "super@man.com");
		
		Uri uri = UsersTable.getUri();
		Uri newUri = mContentResolver.insert(uri, values);
		
		ContentValues newValues = new ContentValues();
		newValues.put(MembersContract.UsersTable.USERNAME, "Peter Parker");
		
		int rowsUpated = mContentResolver.update(UsersTable.getUri(1), newValues, null, null);
		assertEquals(rowsUpated, 1);
		}



}
