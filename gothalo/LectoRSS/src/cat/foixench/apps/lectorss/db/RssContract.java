package cat.foixench.apps.lectorss.db;

import android.provider.BaseColumns;


/**
 * Define variables estaticas para la definici—n de las tablas y de las bases de datos de la aplicacion LectoRSS.
 * @author llorenc
 *
 */
public class RssContract {
	
	public static final String DB_NAME = "rss.db";
	
	/**
	 * definimos un constructor privado para evitar que se creen instancias de esta clase. 
	 */
	private RssContract () {
		
	}
	
	public static class FeedsTable implements BaseColumns {
		private FeedsTable() {
			super ();
		}
		
		public static final String TABLE_NAME = "FEEDS";
		public static final String COLUMN_TITLE = "title";
		public static final String COLUMN_LINK = "link";
		public static final String COLUMN_PUB_DATE = "pubDate";
		public static final String COLUMN_DESCRIPTION = "description";
		public static final String COLUMN_AUTHOR = "author";
		
	}

}
