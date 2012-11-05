package cat.foixench.apps.lectorss.utils;


import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.text.format.DateUtils;

public class Utils {
	/**
     * recupera la version de la aplicaci—n almacenada en el manifiesto de la aplicacion
     * @return valor de la version almacenada en el manifiesto.
     */
    public static String getManifestVersionName (Activity act) {
    	String strVersion = "";
    	PackageInfo packInfo;
    	
    	try {
    		packInfo = act.getPackageManager().getPackageInfo (act.getPackageName (), 0);
    		strVersion = packInfo.versionName;
    		
    	} catch (NameNotFoundException nnfe) {
    		strVersion ="unknown";
    	}
    	return strVersion;
    	
    	
    }
    
    public static String millisToDate (Context context, long millis) {
    	
    	return (String) DateUtils.getRelativeTimeSpanString (context, millis);
    	
    }
    public static String millisToDate (Context context, String millis) {
    	
    	return Utils.millisToDate (context, Long.parseLong (millis));
    	
    }
}
