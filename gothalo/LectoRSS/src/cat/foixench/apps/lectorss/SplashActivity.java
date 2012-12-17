package cat.foixench.apps.lectorss;

import cat.foixench.apps.lectorss.utils.LectoRSSInterface;
import cat.foixench.apps.lectorss.utils.Utils;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;
// import android.widget.Toast;

public class SplashActivity extends Activity implements OnClickListener , LectoRSSInterface {
	
	SplashActivity splash;
	
	LinearLayout splashBox;
	// private Handler delayed;
	MyResultReceiver resultReceiver = new MyResultReceiver();
	
/*	private Runnable delayedTask = new Runnable () {

		public void run() {
			
			Toast toast = Toast.makeText (splash, "cargando", Toast.LENGTH_LONG);
			toast.show ();
			// eliminamos posibles entradas en la cola de este runnable
			delayed.removeCallbacks(this);
			
			// lamamos al método onclick de la splashbox
			onClick (splashBox);
		}
		
		
	};*/

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // guardamos una referencia global a esta clase
        splash = this;
        
        // cargamos el layout splash.xml
        setContentView(R.layout.splash);
        
        // actualizamos la etiqueta de version
        TextView txtVersion = (TextView) findViewById(R.id.txtVersion);
        txtVersion.setText(Utils.getManifestVersionName(this));
        
        // recuperamos el recuadro de fondo del splash para hacerlo clickable.
        splashBox = (LinearLayout) findViewById (R.id.splash_box);
        splashBox.setOnClickListener(this);
        
        // creamos un temporizador para que, pasados 30 segundos, se muestre la activity ArticleListActivity
/*        delayed = new Handler ();
        delayed.removeCallbacks(delayedTask);
        delayed.postDelayed(delayedTask, 30000);*/
        
        // llamamos al volcado de feeds en la bbdd
        Intent service = new Intent ("cat.foixench.lectoRSS.LOAD_FEED");
		
		// añadimos info extra via el result receiver
		service.putExtra (EXTRA_FEED_RECEIVER, resultReceiver);
		
		startService (service);
        
    }
    
    
    
    /** 
     * cuando la activity entra en pausa, desactivamos la tarea en pausa
	 * @see android.app.Activity#onPause()
	 */
	@Override
	protected void onPause() {
		super.onPause();
		// desactivamos el timer
		// delayed.removeCallbacks(delayedTask);
		
	}


	/**
     * procesa el evento click sobre el cuadro de información de la activity. En este caso
     * se muestra la activity ArticleListActivity.
     */
	public void onClick(View v) {
		// mostramos la activity ArticleListActivity
		this.showActivity (ARTICLE_LIST_ACTIVITY);
	}
	
    /**
     * lanza una activity
     * @param activity identificador de la activity a lanzar
     */
    private void showActivity (int activity) {
    	Intent i = null;
    	
    	switch (activity) {
    		case ABOUT_ACTIVITY :	
    			i = new Intent (this, AboutActivity.class);
    			break;
    		case ARTICLE_LIST_ACTIVITY :
    			i = new Intent (this, ArticleListActivity.class);
    			break;
		}
    	if (i != null) {
    		startActivity(i);
    	} else {
    		Log.e (SPLASH_ACTIVITY_TAG, "activity no válida");
    	}
    }
    
    class MyResultReceiver extends ResultReceiver {
    	public MyResultReceiver () {
    		super (new Handler ());
    	}

		/**
		 * clase encargada de registrar el proceso carga de feeds.
		 * @see android.os.ResultReceiver#onReceiveResult(int, android.os.Bundle)
		 */
		@Override
		protected void onReceiveResult(int resultCode, Bundle resultData) {
			super.onReceiveResult(resultCode, resultData);
			
			switch (resultCode) {
				case LOAD_STARTED :
					
					break;
				case LOAD_ENDED : 
					showActivity (ARTICLE_LIST_ACTIVITY);
					break;
			}
		}
    	
    	
    }
}
