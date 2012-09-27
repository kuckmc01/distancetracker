package edu.uc.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.content.Intent;

public class StartGPSActivity extends Activity {

	protected Button btnStartGPSTracking;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.startgpsactivity);
		
		btnStartGPSTracking = (Button) findViewById(R.id.btnstartgpstracking);
		
		
		final GPSLocation location = new GPSLocation();
		

		 btnStartGPSTracking.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				//start recording gps coordinates
				location.requestUpdates();
				changeIntentToTrackingGPS();
			}
		});
	}
	private void changeIntentToTrackingGPS(){

		Intent changeIntent = new Intent(this, TrackingGPSActivity.class);
		startActivityForResult(changeIntent, 1);
	}
}
