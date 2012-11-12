package cat.foixench.apps.callsregister.data;

import android.net.Uri;
import android.provider.BaseColumns;

public class CallsContract {
	public static final String DB_NAME = "calls.db";
	public static final String AUTHORITY = "cat.foixench.callsregister";
	public static final String PATH = "calls";
	public static final String MIME_CALLS_COLLECTION = "android.cursor.dir/vnd.cat.foixench.callscontentprovider.calls";
	public static final String MIME_CALLS_ITEM = "android.cursor.item/vnd.cat.foixench.callscontentprovider.calls";
	
	private CallsContract () {
		// no hacemos nada. creamos constructor privado para evitar que se instancie esta clase
	}
	
	public static class IncommingTable implements BaseColumns {
		
		// Constantes para el acceso a la estructura de la tabla.
		public static final String TABLE_NAME ="INCOMMING";
		public static final String COLUMN_PHONE = "PHONE";
		public static final String COLUMN_CALL_DATE = "DATE";
		
		
		private IncommingTable () {
			super ();
		}
		
		// Metodos para el acceso al content provider
		public static Uri getUri() {
			return Uri.parse ("content://" + AUTHORITY + "/" + PATH);
		}

		public static Uri getUri(long id) {
			return Uri.parse("content://" + AUTHORITY + "/" + PATH + "/" + id);
		}

	}
}
