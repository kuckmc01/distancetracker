package edu.uc.ui;

import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.util.Date;

import android.app.Activity;
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

	LocationManager locationManager;
	LocationListener locationListener;
	String timeNow;
	Chronometer timer;
	DateFormat formattedTime;

	private int gpsInterval = 60;

	protected Button btnStartGPSTracking;
	protected Button btnStopGPSTracking;
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
		lblCurrentlyTracking = (TextView) findViewById(R.id.lblcurrentlytracking);
		lblTrackingStopped = (TextView) findViewById(R.id.lblstoptracking);
		lblLat = (TextView)	findViewById(R.id.lbllat);
		lblLong = (TextView) findViewById(R.id.lbllong);
		lblStartTime = (TextView) findViewById(R.id.lblstarttime);
		lblStopTime = (TextView) findViewById(R.id.lblstoptime);
		timer = (Chronometer) findViewById(R.id.chronometer1);

		formattedTime = new SimpleDateFormat("hh:mm:ss.SSS");
		locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
		initLocationListener();



		btnStartGPSTracking.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				//start recording gps coordinates

				requestUpdates();
				if(currentState == state2)
				{
					timer.setBase(SystemClock.elapsedRealtime());
					timer.start();
					currentState = state4;
					changeVisibility(lblTrackingStopped);
					lblStartTime.setText("Start time: " + formattedTime.format(new Date()));
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
					lblStartTime.setText("Start time: " + formattedTime.format(new Date()));
				}

				

			}
		});
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
					changeVisibility(lblCurrentlyTracking);
					changeVisibility(lblTrackingStopped);
				}
				
				lblStopTime.setText("Time stopped: " + formattedTime.format(new Date()));
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
