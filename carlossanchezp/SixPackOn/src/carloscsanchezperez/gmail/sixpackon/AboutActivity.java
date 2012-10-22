package carloscsanchezperez.gmail.sixpackon;


import android.os.Bundle;
import android.app.Activity;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.widget.TextView;


public class AboutActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about);
        
        // recuperamos el texto de licencia para poder activar su scrollbar
        TextView txtLicence = (TextView) findViewById(R.id.txtLicense) ;
        
        txtLicence.setMovementMethod(new ScrollingMovementMethod());
        
        // actualizamos la etiqueta de version
        TextView txtVersion = (TextView) findViewById(R.id.txtVersion);
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.about, menu);
        return true;
    }
}
