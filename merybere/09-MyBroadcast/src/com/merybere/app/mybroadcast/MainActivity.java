package com.merybere.app.mybroadcast;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

public class MainActivity extends Activity implements OnClickListener, LocationListener {

    private LocationManager locationManager;

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        // Creamos el view del botón como variable local, porque no lo necesito más
        View button = findViewById(R.id.button1);
        // Añadimos el listener que se queda escuchando cuando se produzca el click
        button.setOnClickListener(this);
    }

	// Llamada cuando se produzca el click del botón
    @Override
    public void onClick(View v) {
        Intent intent = new Intent("merybere.app.HOLA_MUNDO");
        // El método sendBroadcast viene en el contexto
        sendBroadcast(intent);
    }

	@Override
	protected void onStart() {
		// Lo primero llamar al super
		super.onStart();
		
		// Comunicación con el servicio de localización
		// getSystemService devuelve un objeto LocationManager, y hay que castearlo
		locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
		// Definición del listener: al manejador de localización le pedimos que avise cuando
		// se produzcan cambios en la localización del gps cada cierto intervalo de tiempo y de distancia
		// (ponemos 0seg y 0m, para que cada vez que haya un cambio mínimo nos avise)
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);

	}

	@Override
	protected void onStop() {
		locationManager.removeUpdates(this);
		super.onStop();
	}

	// Manejo cuando cambia la localización
	@Override
	public void onLocationChanged(Location location) {
		Log.d("GPS", "lat=" + location.getLatitude() + " lon=" + location.getLongitude());
		
	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}
}
