package cat.foixench.apps.holamundo;

import android.os.Bundle;

import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;
import android.graphics.Typeface;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        // personalizamos la fuente del texto autor, para que use English.ttf,
        // que se encuentra en la carpeta Assets.
        TextView customFont = (TextView)findViewById(R.id.txvAuthor);
        Typeface font = Typeface.createFromAsset (getAssets (), "English.ttf");
        customFont.setTypeface(font);
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
