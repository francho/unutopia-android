package org.francho.unutopia.intents;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class MainActivity extends Activity implements OnClickListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        findViewById(R.id.button1).setOnClickListener(this);
    }

	@Override
	public void onClick(View arg0) {
		// Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:41.656313,-0.877351"));
		
		// Enviar texto (sencillo)
		Intent intent = new Intent(Intent.ACTION_SEND);
				        intent.setType("text/plain");
		
		        intent.putExtra(Intent.EXTRA_SUBJECT, "asunto de prueba");
		
		        intent.putExtra(Intent.EXTRA_TEXT, "probando el envio");
		startActivity(intent);
	
		//ver http://francho.org/2011/04/11/android-ejemplos-de-llamadas-a-intents/
	}
}
