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
}
