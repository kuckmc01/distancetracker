package edu.uc.ui;

import android.os.Bundle;
import android.util.Log;
import android.app.Activity;


public class TrackingGPSActivity extends Activity {

    private static final String DEBUG_TAG = "DTrackerLogging";

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracking_gps);
        Log.i(DEBUG_TAG, "Info about the onCreate method in the StopTrackingActivity.");
    }
    


}
