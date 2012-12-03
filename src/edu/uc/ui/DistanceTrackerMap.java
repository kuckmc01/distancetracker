package edu.uc.ui;

import java.util.List;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

import edu.uc.dao.GPSDAODatabase;
import edu.uc.dto.Coordinates;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

public class DistanceTrackerMap extends MapActivity {
	
	private int latitude;
	private int longitude;
	private GPSDAODatabase dao;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_map);
	    dao = new GPSDAODatabase(this);
		MapView mapView = (MapView) findViewById(R.id.mapview);
	    mapView.setBuiltInZoomControls(true);
	    List<Overlay> mapOverlays = mapView.getOverlays();
	    Drawable drawable = this.getResources().getDrawable(R.drawable.androidmaker);
	    DistanceTrackerItemizedOverlay itemizedoverlay = new DistanceTrackerItemizedOverlay(drawable, this);

	    
	    List<Coordinates> points = dao.CursorForMap();
	    points.size(); 
	    for (int i = 0; i < points.size();)
	    {
	    int testlat =  (int) dao.CursorForMap().get(i).getLatitude();
	    int testlong =  (int) dao.CursorForMap().get(i).getLongitude();

	    GeoPoint point = new GeoPoint(testlat ,testlong);
	    OverlayItem overlayitem = new OverlayItem(point, "" + testlat, "" + testlong);
		itemizedoverlay.addOverlay(overlayitem);
		mapOverlays.add(itemizedoverlay);

	    i++;
	    }
	    //GeoPoint point = new GeoPoint(latitude,longitude);
		//OverlayItem overlayitem = new OverlayItem(point, ""+ latitude ,""+ longitude);
	    
	
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
