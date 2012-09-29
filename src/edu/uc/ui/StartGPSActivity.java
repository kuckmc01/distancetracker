package edu.uc.ui;

import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.util.Date;
import edu.uc.service.GPSServiceStub;
import android.app.Activity;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;
import android.widget.Toast;


public class StartGPSActivity extends Activity {

	private static final String tag = "StartGPSActivity";
	private double latitude;
	private double longitude;
	private String startTime;
	private String endTime;
	private String todaysDate;
	private String currentTime;

	LocationManager locationManager;
	LocationListener locationListener;
	String timeNow;
	Chronometer timer;
	DateFormat formattedTime;
	DateFormat formattedDate;
	GPSServiceStub service; 

	private int gpsInterval = 15;

	protected Button btnStartGPSTracking;
	protected Button btnStopGPSTracking;
	protected Button btnViewResults;
	protected TextView lblCurrentlyTracking;
	protected TextView lblTrackingStopped;
	protected TextView lblLat;
	protected TextView lblLong;
	protected TextView lblStartTime;
	protected TextView lblStopTime;

	protected int currentState = 0;
	protected int state1 = 1;
	protected int state2 = 2;
	protected int state3 = 3;
	protected int state4 = 4;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.startgpsactivity);

		btnStartGPSTracking = (Button) findViewById(R.id.btnstartgpstracking);
		btnStopGPSTracking = (Button) findViewById(R.id.btnstoptracking);
		btnViewResults = (Button) findViewById(R.id.btnviewresults);
		lblCurrentlyTracking = (TextView) findViewById(R.id.lblcurrentlytracking);
		lblTrackingStopped = (TextView) findViewById(R.id.lblstoptracking);
		lblLat = (TextView)	findViewById(R.id.lbllat);
		lblLong = (TextView) findViewById(R.id.lbllong);
		lblStartTime = (TextView) findViewById(R.id.lblstarttime);
		lblStopTime = (TextView) findViewById(R.id.lblstoptime);
		timer = (Chronometer) findViewById(R.id.chronometer1);
		service = new GPSServiceStub();
		formattedTime = new SimpleDateFormat("hh:mm:ss");
		formattedDate = new SimpleDateFormat("yyyy/MM/dd");
		locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
		initLocationListener();


/** TODO:
 * The button starts gps tracking
 * need to save the following items to the DATABASE through the service layer and persistence layer:
 * -coordinates
 * -time started
 */
		btnStartGPSTracking.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				//start recording gps coordinates
				
				todaysDate = formattedDate.format(new Date());
				service.saveTodaysDate(todaysDate);
				
				requestUpdates();
				if(currentState == state2)
				{
					
					timer.setBase(SystemClock.elapsedRealtime());
					timer.start();
					currentState = state4;
					changeVisibility(lblTrackingStopped);
					changeVisibility(btnViewResults);
					startTime = formattedTime.format(new Date());
					lblStartTime.setText("Start time: " + startTime);
					lblStopTime.setText("Time stopped: ");
					
				}
				//starting page state, start button should become invisible on click
				//stop button should become visible, currently tracking label should become visible
				if(currentState == 0  || currentState == state4)
				{
					
					timer.start();
					currentState = state1;
					changeVisibility(btnStartGPSTracking);
					changeVisibility(btnStopGPSTracking);
					changeVisibility(lblCurrentlyTracking);
					startTime = formattedTime.format(new Date());
					lblStartTime.setText("Start time: " + startTime);
					service.saveStartTime(startTime);
				}
			}
		});
		/**TODO:
		 * need to save the following items to the DATABASE through the service layer and persistence layer:
		 * -ending coordinates
		 * -ending time
		 */
		btnStopGPSTracking.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				//stop recording gps coordinates

				removeUpdates();
				//2nd page state, stop button should become invisible 
				if(currentState == state1)
				{
					timer.stop();
					currentState = state2;
					changeVisibility(btnStopGPSTracking);
					changeVisibility(btnStartGPSTracking);
					changeVisibility(btnViewResults);
					changeVisibility(lblCurrentlyTracking);
					changeVisibility(lblTrackingStopped);
				}
				endTime = formattedTime.format(new Date());
				lblStopTime.setText("Time stopped: " + endTime);
				service.saveEndTime(endTime);
				
				//must be called last
				service.saveDistanceObject();
			}
		});
		btnViewResults.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				Intent intent = new Intent(v.getContext(), ResultsActivity.class);
				startActivityForResult(intent, 0);
				
			}
		});
	}


	private void initLocationListener(){
		locationListener = new LocationListener(){

			public void onLocationChanged(Location location) {
				latitude = location.getLatitude();
				longitude = location.getLongitude();

				lblLat.setText("lat: " + latitude);
				lblLong.setText("long: " + longitude);
				currentTime = formattedTime.format(new Date());
				service.saveCoordinates(currentTime, latitude, longitude);
				Toast.makeText(StartGPSActivity.this, "lat: " + latitude + " lng: " + longitude, Toast.LENGTH_LONG).show();

			}

			public void onProviderDisabled(String provider) {
				// TODO Auto-generated method stub

			}

			public void onProviderEnabled(String provider) {
				// TODO Auto-generated method stub

			}

			public void onStatusChanged(String provider, int status,
					Bundle extras) {
				switch (status) {
				case LocationProvider.OUT_OF_SERVICE:
					Log.v(tag, "Status Changed: Out of Service");
					Toast.makeText(StartGPSActivity.this, "Status Changed: Out of Service", Toast.LENGTH_SHORT).show();
					break;
				case LocationProvider.TEMPORARILY_UNAVAILABLE:
					Log.v(tag, "Status Changed: Temporarily Unavailable");
					Toast.makeText(StartGPSActivity.this, "Status Changed: Temporarily Unavailable",
							Toast.LENGTH_SHORT).show();
					break;
				case LocationProvider.AVAILABLE:
					Log.v(tag, "Status Changed: Available");
					Toast.makeText(StartGPSActivity.this, "Status Changed: Available",
							Toast.LENGTH_SHORT).show();
					break;
				}
			}
		};
	}
	protected void removeUpdates(){
		locationManager.removeUpdates(locationListener);

	}
	/**
	 * TODO: need to change this portion so that gps is tracked every 15 or 30 secs for accuracy on the map later on
	 * need to save each changed coordinates to the database to outline them on the map
	 */
	protected void requestUpdates(){
		if(locationListener != null  && locationManager != null){
			locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, gpsInterval * 1000, 0, locationListener);
			//Toast.makeText(context, text, duration)
			
			
		}
	}
	private void changeVisibility(TextView textView ){

		if(textView.isShown()){
			textView.setVisibility(4);
		}
		else{
			textView.setVisibility(0);
		}
	}
	private void changeVisibility(Button button ){
		if(button.isShown()){
			button.setVisibility(4);
		}
		else{
			button.setVisibility(0);
		}
	}

}
