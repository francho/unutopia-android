package com.albertoRM.fuentes;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Typeface;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity {
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView fuentePersonalizada=(TextView)findViewById(R.id.fuente);
		Typeface fuenteJoker=Typeface.createFromAsset(getAssets(),"fonts/Jokerman.TTF");
		fuentePersonalizada.setTypeface(fuenteJoker);
		
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
