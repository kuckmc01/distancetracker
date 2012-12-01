package edu.uc.ui;

import java.util.List;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

public class DistanceTrackerMap extends MapActivity {
	
	private int latitude;
	private int longitude;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_map);
		MapView mapView = (MapView) findViewById(R.id.mapview);
	    mapView.setBuiltInZoomControls(true);
	    List<Overlay> mapOverlays = mapView.getOverlays();
	    Drawable drawable = this.getResources().getDrawable(R.drawable.androidmaker);
	    DistanceTrackerItemizedOverlay itemizedoverlay = new DistanceTrackerItemizedOverlay(drawable, this);
	    //GeoPoint point = new GeoPoint(latitude,longitude);
		//OverlayItem overlayitem = new OverlayItem(point, ""+ latitude ,""+ longitude);
	    GeoPoint point = new GeoPoint(19240000,-99120000);
	    OverlayItem overlayitem = new OverlayItem(point, "Hola, Mundo!", "I'm in Mexico City!");
		itemizedoverlay.addOverlay(overlayitem);
		mapOverlays.add(itemizedoverlay);
	
	}
	 
	
	@Override
	protected boolean isRouteDisplayed() {
	    return false;
	}
	
	 private int getLatitude() {
		return latitude;
	}

	private void setLatitude(int latitude) {
		this.latitude = latitude;
	}
	private int getLongitude() {
		return longitude;
	}

	private void setLongitude(int longitude) {
		this.longitude = longitude;
	}
	

}
