package org.francho.unutopia.mycontenterprovider.data;

import org.francho.unutopia.mycontenterprovider.data.MembersContract.UsersTable;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.text.TextUtils;

public class MyContentProvider extends ContentProvider {

	private MyDbHelper mDbHelper;

	private static final UriMatcher sUriMatcher;

	private static final int TYPE_USERS_COLLECTION = 1;
	private static final int TYPE_USERS_ITEM = 2;
	
	static {
		sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
		
		sUriMatcher.addURI(MembersContract.AUTHORITY, "users", TYPE_USERS_COLLECTION);
		sUriMatcher.addURI(MembersContract.AUTHORITY, "users/#", TYPE_USERS_ITEM);
	}
	
	
	@Override
	public boolean onCreate() {
		mDbHelper = new MyDbHelper(getContext());
		return true;
	}
	
	@Override
	public String getType(Uri uri) {
		switch(sUriMatcher.match(uri)) {
		case TYPE_USERS_COLLECTION:
			return "android.cursor.dir/vnd.org.francho.unutopia.mycontentprovider.users";
		case TYPE_USERS_ITEM:
			return "android.cursor.item/vnd.org.francho.unutopia.mycontentprovider.users";
		default:
			return null;
		}
	}
	
	/**
	 * Elimina registros de la base de datos. En caso de recibir una uri de tipo coleccion, elimina segun
	 * la condicion en el parametro selection. Si se recibe una uri de tipo item, eliminamos segun selection y el 
	 * item indicado.
	 * por seguridad, en las uri de tipo colecci—n no se deja borrar si el parametro selection esta vacio. Para borrar
	 * todos los registros de una tabla se debe pasar en la selection un registro que siempre sea cierto, como "1=1"
	 * 
	 * @return nœmero de registros afectados por la sentencia o -1 si no se ha realizado el borrado de datos.
	 */
	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		int result = -1;
		SQLiteDatabase db = mDbHelper.getWritableDatabase();
		
		// actuamos segun si la uri lleva incorporado el id del registro a eliminar o no.
		switch (sUriMatcher.match (uri)) {
			case TYPE_USERS_ITEM :
				// recuperamos el id a eliminar
				String id = uri.getLastPathSegment ();
				
				if (TextUtils.isEmpty (selection)) {
					selection = UsersTable._ID + " = " + id;
				} else {
					// hay clausula adicional. a–adimos esta condicion.
					selection = " (" + selection + ") AND " + UsersTable._ID + " = " + id;
				}
				
				// lanzamos la sentencia delete
				result = db.delete (UsersTable.TABLE_NAME, selection, selectionArgs);
				break;
				
			case TYPE_USERS_COLLECTION : 
				// si no viene condicion en el seleccion, abortamos la operacion para evitar que se elimine toda la tabla
				// seguria siedo posible el vaciado pasando como selection "1=1";
				
				if (TextUtils.isEmpty (selection)) {
					result = -1;
				} else {
					result = db.delete (UsersTable.TABLE_NAME, selection, selectionArgs);
				}
				
				break;
			default :
				result = -1;
		}
		
		return result;
	}

	

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		int uriType = sUriMatcher.match(uri);
		if( uriType != TYPE_USERS_COLLECTION) {
			return null;
		}
		
		final SQLiteDatabase db = mDbHelper.getWritableDatabase();
		long id = db.insert(UsersTable.TABLE_NAME, null, values);
		
		Uri newUri = UsersTable.getUri(id);
		
		return newUri;
	}


	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		
		
		final SQLiteDatabase db = mDbHelper.getReadableDatabase();
		
		switch(sUriMatcher.match(uri)) {
		case TYPE_USERS_ITEM:
			String id = uri.getLastPathSegment();
			
			if(!TextUtils.isEmpty(selection)) {
				selection += " AND";
			} else {
				selection = "";
			}
			selection += UsersTable._ID + "==" + id;
			
			String table = UsersTable.TABLE_NAME;
			String groupBy = null;
			String having = null;
			Cursor cursor = db.query(table, projection, selection, selectionArgs, groupBy, having, sortOrder);
			return cursor;
		default:
			return null;
		}
		
	}

	/**
	 * Actualiza la tabla de usuarios. Puede recibir una uri de tipo Coleccion o de tipo Item. en el primer caso
	 * actualiza todos los registros afectados por la condicion indicada en el parametro selection. En caso de ser
	 * una uri de tipo Item, se a–ade a la condici—n que el _ID del registro sea el indicado.
	 * 
	 * @return numero de registros actualizados. -1 en caso de error.
	 */
	
	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		
		int result = -1;
		// accedemos a la base de datos
		final SQLiteDatabase db = mDbHelper.getWritableDatabase();
		
		// verificamos la consulta
		switch (sUriMatcher.match(uri)) {
			case TYPE_USERS_ITEM :
				// recuperamos el id del ultimo parametro de la uri
				String id = uri.getLastPathSegment ();
				
				// verificamos si hay parametros en la seleccion. 
				if (TextUtils.isEmpty (selection)) {
					selection = UsersTable._ID + " = " + id;
				} else {
					selection = "(" + selection + ") AND " + UsersTable._ID + " = " + id;				}
				
				// Realizamos el update
				result = db.update(UsersTable.TABLE_NAME, values, selection, selectionArgs);
				
				break;
			case TYPE_USERS_COLLECTION :
				// lanzamos la actualizacion sin filtrar por id.
				result = db.update(UsersTable.TABLE_NAME, values, selection, selectionArgs);
				break;
			default :
				// en otro caso no se nos identifica el registro a actualizar y devolvemos -1;
				result = -1;
		}

		return result;
	}

}
