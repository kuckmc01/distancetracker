package edu.uc.ui;

import android.app.Activity;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class GPSLocation extends Activity {
	private static final String DEBUG_TAG = "DTrackerLogging";
	private double latitude;
	private double longitude;

	LocationManager locationManager;
	LocationListener locationListener;
	//time measured in seconds
	private int gpsInterval = 60;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
		initLocationListener();
		 Log.i(DEBUG_TAG, "Info about the oncreate method in the GPSLocation."); 
	}
	private void initLocationListener(){
		locationListener = new LocationListener(){

			public void onLocationChanged(Location location) {
				// TODO Auto-generated method stub
				setLatitude(location.getLatitude());
				setLongitude(location.getLongitude());


			}

			public void onProviderDisabled(String provider) {
				// TODO Auto-generated method stub

			}

			public void onProviderEnabled(String provider) {
				// TODO Auto-generated method stub

			}

			public void onStatusChanged(String provider, int status,
					Bundle extras) {
				// TODO Auto-generated method stub

			}
		};
	}
	protected void removeUpdates(){
		locationManager.removeUpdates(locationListener);
	}
	
	protected void requestUpdates(){
		if(locationListener != null  && locationManager != null){
			locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, gpsInterval * 1000, 0, locationListener);
			Toast.makeText(GPSLocation.this, "GPS location" + latitude + " " + longitude, 1).show();
		}
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

}
