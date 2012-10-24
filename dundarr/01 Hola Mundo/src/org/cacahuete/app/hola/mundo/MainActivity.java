package org.cacahuete.app.hola.mundo;

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
        TextView customFont = (TextView)findViewById(R.id.linea3);
        Typeface font = Typeface.createFromAsset (getAssets (), "FEASFBRG.TTF");
        customFont.setTypeface(font);
        
     // personalizamos la fuente del texto autor, para que use English.ttf,
        // que se encuentra en la carpeta Assets.
        TextView customFont2 = (TextView)findViewById(R.id.linea5);
        Typeface font2 = Typeface.createFromAsset (getAssets (), "ROCKFONT.TTF");
        customFont2.setTypeface(font2);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
