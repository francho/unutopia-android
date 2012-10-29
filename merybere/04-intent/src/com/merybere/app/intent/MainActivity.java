package com.merybere.app.intent;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;

public class MainActivity extends Activity implements OnClickListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        // Captura del botón
        findViewById(R.id.button1).setOnClickListener(this);
    }

	@Override
	public void onClick(View arg0) {
		
		// Ejemplos de llamadas en los botones
		
		// LANZAR UNA URL
		
		// Crear un intent de llamada
		Intent intent = new Intent(Intent.ACTION_VIEW);
		// Como dato, se pasa una url
		intent.setData(Uri.parse("http://www.francho.org"));
		
		startActivity(intent);
		
		// Más ejemplos: http://francho.org/2011/04/11/android-ejemplos-de-llamadas-a-intents/
	}


}
