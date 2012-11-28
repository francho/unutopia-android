package org.francho.unutopia.ejemplomapas;

import java.util.List;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
/**
 * 
 * https://developers.google.com/maps/documentation/android/hello-mapview
 * 
 */
public class MainActivity extends MapActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		MapView mapView = (MapView) findViewById(R.id.mapview);
	    mapView.setBuiltInZoomControls(true);
	    
	    List<Overlay> mapOverlays = mapView.getOverlays();
	    
	    
	    Drawable defaultMarker = getResources().getDrawable(R.drawable.ic_launcher);
		HelloItemizedOverlay myOverlay = new HelloItemizedOverlay(defaultMarker , this);
		mapOverlays.add(myOverlay);
		
		
		
		GeoPoint point = new GeoPoint(19240000,-99120000);
		OverlayItem overlayitem = new OverlayItem(point, "Hola, Mundo!", "I'm in Mexico City!");
		myOverlay.addOverlay(overlayitem);
		
		GeoPoint point2 = new GeoPoint(35410000, 139460000);
		OverlayItem overlayitem2 = new OverlayItem(point2, "Sekai, konichiwa!", "I'm in Japan!");
		
		
		myOverlay.addOverlay(overlayitem2);
	}


	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}

}
