package cat.foixench.apps.lectorss;

import cat.foixench.apps.lectorss.utils.Utils;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class SplashActivity extends Activity {
	
	final int ABOUT_ACTIVITY = 1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        
        // actualizamos la etiqueta de version
        TextView txtVersion = (TextView) findViewById(R.id.txtVersion);
        txtVersion.setText(Utils.getManifestVersionName(this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.splash, menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	boolean result = false;
        // Handle item selection
        switch (item.getItemId()) {
        	case R.id.menu_about :
        		
        		this.showActivity (ABOUT_ACTIVITY);
        		
        		result = true;
        		break;
        	
        	default:
                result = super.onOptionsItemSelected(item);
        }
        
        return result;
    }
    
    /**
     * lanza una activity
     * @param activity identificador de la activity a lanzar
     */
    private void showActivity (int activity) {
    	switch (activity) {
    		case ABOUT_ACTIVITY :
    		
    			Intent i = new Intent (this, AboutActivity.class);
    			startActivity(i);
    			break;
    	
		}
    }
}
