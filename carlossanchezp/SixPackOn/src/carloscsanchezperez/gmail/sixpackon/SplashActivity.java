package carloscsanchezperez.gmail.sixpackon;


import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class SplashActivity extends Activity {
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        
        TextView txtVersion = (TextView) findViewById(R.id.txtVersion);
        
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
        		
        		Intent i = new Intent (this, AboutActivity.class);
    			startActivity(i);
        		
        		result = true;
        		break;
        		
        	case R.id.menu_settings :
        		
        		result = true;
        		break;
        	
        	default:
                result = super.onOptionsItemSelected(item);
        }
        
        return result;
    }
    
 
}
