package com.merybere.app.services;

import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ProgressBar;

public class MainActivity extends Activity {

	// Objeto parcelable, que pasaremos en el intent
	MyResultReceiver resultReceiver = new MyResultReceiver();
	private ProgressBar progressBar;
	private ImageView imageView;
	private LoadImageAsyncTask task;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        progressBar = (ProgressBar) findViewById(R.id.progressBar1);
        // Ocultar inicialmente la barra de progreso. Estados:
        // 	INVISIBLE: no se muestra pero se respeta el espacio que ocupa 
        // 	GONE: ocultarla y liberar el espacio
        // 	VISIBLE
        progressBar.setVisibility(View.INVISIBLE);
        
        // View para cambiar la imagen
        imageView = (ImageView) findViewById(R.id.imageView1);
        
        // Definimos el método a llamar al hacer click
        findViewById(R.id.button1).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// Intent con la acción que hemos llamado en el filtro en el manifiesto
				Intent service = new Intent("com.merybere.app.ACTION_SERVICE");
				// Iniciar el servicio
				startService(service);
			}
        	
        });
        
        findViewById(R.id.button2).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent service = new Intent("com.merybere.app.ACTION_INTENT_SERVICE");
				// Pasarle al mensajero (intent) un objeto a través del que nos vamos a comunicar
				// Este objeto nos devolverá estados
				service.putExtra("com.merybere.app.EXTRA_MYRESULTRECEIVER", resultReceiver);
				startService(service);
				
			}
        	
        });
    }

    // Lanzar la tarea en segundo plano
	@Override
	protected void onStart() {
		
		super.onStart();

		// Cargar la tarea asíncrona, con el imageView
		task = new LoadImageAsyncTask(imageView);
		// Ejecutar la tarea, pasándole la string con la url de una imagen
		task.execute("http://www.android.com/images/marquee_jb.png");
	}

	// Parar la tarea cuando acaba
	@Override
	protected void onStop() {

		if(task != null) {
			// Intentar parar la tarea
			task.cancel(true);
		}
		super.onStop();
	}
	
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    // Clase que android utiliza internamente para enviar y recibir resultados
    class MyResultReceiver extends ResultReceiver {

    	// Constructor: pide un handler, para comunicar desde hilos secundarios al hilo principal
		public MyResultReceiver() {
			super(new Handler());
		}

		// resultCode es un resultado numérico con el que interactuamos (id del estado)
		// resultData nos podría pasar otros datos extra 
		@Override
		protected void onReceiveResult(int resultCode, Bundle resultData) {
			
			super.onReceiveResult(resultCode, resultData);
			
			// Mostrar y ocultar la barra de progreso al recibir el código correspondiente
			switch (resultCode) {
			case 1:
				progressBar.setVisibility(View.VISIBLE);
				break;
			case 0:
				progressBar.setVisibility(View.INVISIBLE);
				break;
			default:
				break;
			}
		}
    	
    }
}
