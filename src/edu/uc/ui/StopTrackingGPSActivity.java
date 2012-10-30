package edu.uc.ui;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;

public class StopTrackingGPSActivity extends Activity {

    private static final String DEBUG_TAG = "DTrackerLogging";

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stop_tracking_gps);
        Log.i(DEBUG_TAG, "Info about the oncreate method in the StopGPSActivity."); 
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_stop_tracking_gps, menu);
        Log.i(DEBUG_TAG, "Info about the onCreateOptionsMenu method in the StopGPSActivity.");
        return true;
    }
}
