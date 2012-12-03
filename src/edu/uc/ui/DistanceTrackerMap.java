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


	private GPSDAODatabase dao;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map);

		//load the layout file into the map
		MapView mapView = (MapView) findViewById(R.id.mapview);
		// this unable you to zoom in and zoom out
		mapView.setBuiltInZoomControls(true);
		//initialize the dao instance.
		dao = new GPSDAODatabase(this);
		List<Overlay> mapOverlays = mapView.getOverlays();
		// add a picture to show where is your current position on the map
		Drawable drawable = this.getResources().getDrawable(R.drawable.androidmaker);
		DistanceTrackerItemizedOverlay itemizedoverlay = new DistanceTrackerItemizedOverlay(drawable, this);
		// get the coordinates from the database
		List<Coordinates> points = dao.CursorForMap();
		// loop through and display the coordinates on the map 
		for (int i = 0; i < points.size();i++)
		{
			int testlat =  (int) dao.CursorForMap().get(i).getLatitude();
			int testlong =  (int) dao.CursorForMap().get(i).getLongitude();
			//Create the GeoPoint that defines the map coordinates for the overlay item.
			GeoPoint point = new GeoPoint(testlat, testlong);
			OverlayItem overlayitem = new OverlayItem(point, "" + testlat, "" + testlong);
			//add the overlay item to the collection and add the itemizedOverlay to the mapView
			itemizedoverlay.addOverlay(overlayitem);
			mapOverlays.add(itemizedoverlay);
		} 

	}

	//This method(isRouteDisplayed()) is required to see if you're currently displaying any route information.

	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}




}
