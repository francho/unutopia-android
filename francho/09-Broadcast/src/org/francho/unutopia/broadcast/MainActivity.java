package org.francho.unutopia.broadcast;

import android.app.Activity;
import android.content.Intent;
import android.location.GpsStatus.Listener;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

public class MainActivity extends Activity implements OnClickListener, LocationListener {

    private LocationManager locationManager;


	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        View button = findViewById(R.id.button1);
        button.setOnClickListener(this);
    }

  
	@Override
	public void onClick(View v) {
		Intent intent = new Intent("francho.unutopia.HOLA_MUNDO");
		sendBroadcast(intent);
	}


	@Override
	protected void onStart() {
		super.onStart();
		
		locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
	}

	@Override
	protected void onStop() {
		locationManager.removeUpdates(this);
		super.onStop();
	}


	


	@Override
	public void onLocationChanged(Location location) {
		Log.d("GPS", "lat=" + location.getLatitude() + " lon=" +location.getLongitude());
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
