package arm.feedreader.instantnews;

import arm.feedreader.instantnews.app.AppIntent;
import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;



public class SplashActivity extends Activity implements OnClickListener {
	
	 protected int SPLASH_DISPLAY_LENGTH = 10;
	 private boolean terminar = false;
	 
	 
	 
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	
      //Quitamos barra de titulo de la aplicacion
        //this.requestWindowFeature(Window.FEATURE_NO_TITLE);
      //Quitamos barra de notificaciones 
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        View mainView = findViewById(R.id.splash_main);
		mainView.setOnClickListener(this);
		
    }
    
  
    
    @Override
	public void onResume() {
		super.onResume();
		Handler handler = new Handler();
        handler.postDelayed(getRunnableStartApp(), SPLASH_DISPLAY_LENGTH * 1000);
        
	}
    
    protected void onPause() {
    	super.onPause();
		terminar(true);
		
		
	}
    public void terminar (boolean fin){
    	this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN, WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
        this.terminar=fin;
    }
		/**
         * Metodo en el cual se debe incluir dentro de run() el código que se quiere realizar una
         * vez ha finalizado el tiempo que se desea mostrar la actividad de splash.Si terminar es true, 
         * no realiza ninguna acción.
         **/
        private Runnable getRunnableStartApp(){
        	Runnable runnable = new Runnable(){
        		 public void run(){
        			 if(!terminar)
        				 startNextActivity();
        			 }
        	};
        	return runnable;
        }



	public void onClick(View v) {
		terminar(true); 
		startNextActivity();
	} 
	
	public void startNextActivity() {
		
		Intent intent = new Intent(AppIntent.getArticleListIntent());
  		startActivity(intent);		
	}
	
	
}

