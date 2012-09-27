package edu.uc.ui;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class StopTrackingGPSActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stop_tracking_gps);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_stop_tracking_gps, menu);
        return true;
    }
}
