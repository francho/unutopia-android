package com.merybere.app.mycontentprovider.data;

import com.merybere.app.mycontentprovider.data.MembersContract.UsersTable;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.text.TextUtils;

public class MyContentProvider extends ContentProvider {

	private MyDbHelper mDbHelper;

	// Creación de un objeto de tipo UriMatcher (helper que nos pemite, ante una url
	// de los tipos que estamos manejando en los test, saber de qué tipo es)
	// Es algo que se va a usar a lo largo de todo el ContentProvider y no va a cambiar,
	// por tanto la definimos como estática
	private static final UriMatcher sUriMatcher;

	private static final int TYPE_USERS_COLLECTION = 1;

	private static final int TYPE_USERS_ITEM = 2; 
	// Inicialización de sUriMatcher en un bloque estático
	static {
		// Se le pasa como parámetro un valor numérico (como código del tipo de uri);
		// por defecto, el valor que devolverá para una no válida
		sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
		
		// Añadimos los tipos de uris que vamos creando en los tests
		// El tercer parámetro es el código que asignamos a este tipo de url
		sUriMatcher.addURI(MembersContract.AUTHORITY, "users", TYPE_USERS_COLLECTION);
		// Para un registro en concreto:
		sUriMatcher.addURI(MembersContract.AUTHORITY, "users/#", TYPE_USERS_ITEM);
	}
	
	@Override
	public boolean onCreate() {
		// Instanciar todo lo que necesitemos en el ContentProvider.
		// El que estamos haciendo está basado en el SQLite
		
		// Creamos el helper
		mDbHelper = new MyDbHelper(getContext());
		return true;
	}

	// Devolver un mimetype, que sirve para hacer los filtros de items
	@Override
	public String getType(Uri uri) {
		// Comprobar qué tipo de uri recibimos como parámetro, y pasarlo a numérico 
		switch(sUriMatcher.match(uri)) {
		case TYPE_USERS_COLLECTION:
			// la convención para los grupos de datos es android.cursor.dir
			// (vnd de vendor)
			return "android.cursor.dir/vnd.com.merybere.app.mycontentprovider/users";
		case TYPE_USERS_ITEM:
			// la convención para un solo ítem es android.cursor.dir
			return "android.cursor.item/vnd.com.merybere.app.mycontentprovider/users";
		default:
			return null;
		}
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		// Sacamos el tipo de uri a una variable, para poder debugear y ver su valor
		int uriType = sUriMatcher.match(uri);

		// Obtener la BD en modo escritura
		final SQLiteDatabase db = mDbHelper.getWritableDatabase();
		int numRows;
					
		switch(uriType) {
		case TYPE_USERS_ITEM:
			String id = uri.getLastPathSegment();
			
			// Si ya han pasado una cláusula where en el parámetro selection, concatenarle un AND,
			// para poder añadirle nuestra condición con el id de la uri
			if(!TextUtils.isEmpty(selection)) {
				selection += " AND ";
			} else {
				// Si el where era nulo, asignarle cadena vacía para que al concatenarlo no use el null
				selection = "";
			}
			
			selection += UsersTable._ID + "==" + id;
			// Delete (si funciona, devolverá un id, el autoincremental)
			numRows = db.delete(UsersTable.TABLE_NAME, selection, selectionArgs);
			return numRows;
		case TYPE_USERS_COLLECTION: 
			numRows = db.delete(UsersTable.TABLE_NAME, selection, selectionArgs);
			return numRows;
		default:
			return -1;
		}
		
	}

	// Convierte el ContentValues en un registro de BD, y devuelve una uri válida del ContentProvider, 
	// de forma que cuando se llame a ella devuelva únicamente este registro
	@Override
	public Uri insert(Uri uri, ContentValues values) {
		// Sacamos el tipo de uri a una variable, para poder debugear y ver su valor
		int uriType = sUriMatcher.match(uri);
		// Comprobamos que la url sea del tipo definido
		if(uriType != TYPE_USERS_COLLECTION) {
			return null;
		}
		
		// Obtener la BD en modo escritura
		final SQLiteDatabase db = mDbHelper.getWritableDatabase();
		// Insert (si funciona, devolverá un id, el autoincremental)
		long id = db.insert(UsersTable.TABLE_NAME, null, values);
		
		// Para convertir la url en única, 
		Uri newUri = UsersTable.getUri(id);
		
		return newUri;
	}

	// Método de lanzar una consulta
	// - Proyection: las columnas del select
	// - Selection: el where
	// - sortOrder: el order by
	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		
		// Obtener una bd en modo lectura, para lanzar todas las posibles queries
		final SQLiteDatabase db = mDbHelper.getReadableDatabase();
		
		String table;
		String groupBy;
		String having;
		Cursor cursor;
		
		// Cuando llamen a la query, ver de qué tipo es
		switch(sUriMatcher.match(uri)) {
		case TYPE_USERS_ITEM:
			// Del id que nos han pasado en la uri, quedarnos con la última parte
			// (es una cadena de texto con el id del registro)
			String id = uri.getLastPathSegment();
			
			// Si ya han pasado una cláusula where en el parámetro selection, concatenarle un AND,
			// para poder añadirle nuestra condición con el id de la uri
			if(!TextUtils.isEmpty(selection)) {
				selection += " AND ";
			} else {
				// Si el where era nulo, asignarle cadena vacía para que al concatenarlo no use el null
				selection = "";
			}
			selection += UsersTable._ID + "==" + id;
			
			// Montar la query
			table = UsersTable.TABLE_NAME;
			groupBy = null;
			having = null;
			cursor = db.query(table, projection, selection, selectionArgs, groupBy, having, sortOrder);
			return cursor;
		case TYPE_USERS_COLLECTION: 
			// Montar la query
			table = UsersTable.TABLE_NAME;
			groupBy = null;
			having = null;
			cursor = db.query(table, projection, selection, selectionArgs, groupBy, having, sortOrder);
			return cursor;
		default:
			return null;
		}
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		
		// Sacamos el tipo de uri a una variable, para poder debugear y ver su valor
		int uriType = sUriMatcher.match(uri);
		
		// Obtener la BD en modo escritura
		final SQLiteDatabase db = mDbHelper.getWritableDatabase();
		
		switch(uriType) {
		case TYPE_USERS_ITEM:
			// Del id que nos han pasado en la uri, quedarnos con la última parte (es una cadena)
			String id = uri.getLastPathSegment();
			
			// Si ya han pasado una cláusula where en el parámetro selection, concatenarle un AND,
			// para poder añadirle nuestra condición con el id de la uri
			if(!TextUtils.isEmpty(selection)) {
				selection += " AND ";
			} else {
				// Si el where era nulo, asignarle cadena vacía para que al concatenarlo no use el null
				selection = "";
			}
			selection += UsersTable._ID + "==" + id;
			// Update: devuelve el número de filas actualizadas
			int numRows = db.update(UsersTable.TABLE_NAME, values, selection, selectionArgs);
			return numRows;
		default:
			return -1;
		}
	}

}
