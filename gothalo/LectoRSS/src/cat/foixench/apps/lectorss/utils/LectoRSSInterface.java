/**
 * 
 */
package cat.foixench.apps.lectorss.utils;

/**
 * @author llorenc
 * Contiene constantes globales usadas durante la aplicaci—n LectoRSS.
 */
public interface LectoRSSInterface {

	/**
	 * identificadores de las activities de la aplicacion
	 */
	final static int SPLASH_ACTIVITY = 1;
	final static int ABOUT_ACTIVITY = 2;
	final static int ARTICLE_LIST_ACTIVITY = 3;
	final static int ARTICLE_DETAIL_ACTIVITY = 4;
	
	/** 
	 * tags para los logs
	 */
	
	final static String SPLASH_ACTIVITY_TAG = "LectoRSS - SplashActivity";
	
	
	/**
	 * nombre de parametros pasados entre activitys
	 * 
	 */
	final static String PARAM_TITLE = "titulo"; 
	final static String PARAM_AUTHOR = "autor";
	final static String PARAM_DATE = "fecha";
	final static String PARAM_ID = "id";
	
	/**
	 * identificadores de callbacks de carga
	 */
	final static int LOADER_LECTORSS_CONTENT_PROVIDER = 1;
	
	/**
	 * tag para identificar los intent services
	 */
	final static String SERVICE_TAG = "LectoRSSIntentService";
	
	/**
	 * Identificadores de los extras de los intents
	 */
	
	final static String EXTRA_FEED_RECEIVER = "EXTRA_FEED_RECEIVER";
	
	/** 
	 * flags proceso de carga de feeds
	 */
	
	final static int LOAD_STARTED = 1;
	final static int LOAD_ENDED = 0;
}
