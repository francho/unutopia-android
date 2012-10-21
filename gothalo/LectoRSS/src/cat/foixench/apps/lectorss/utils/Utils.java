package cat.foixench.apps.lectorss.utils;


import android.app.Activity;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;

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
}
