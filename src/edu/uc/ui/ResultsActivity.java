package edu.uc.ui;

import java.util.List;

import edu.uc.dao.GPSDAODatabase;
import edu.uc.service.GPSServiceStub;
import edu.uc.service.IGPSService;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class ResultsActivity extends Activity {
	private GPSDAODatabase dao;

	IGPSService service; 
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
    
        service = new GPSServiceStub();
        dao = new GPSDAODatabase(this);
        spinnerpopulate();
    }
    public void spinnerpopulate() {

	setContentView (R.layout.activity_results);
	 Spinner spinner = (Spinner) findViewById(R.id.spinner1);
        List<String> trips = dao.SelectCursorTripID();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, 
               android.R.layout.simple_spinner_dropdown_item, 
                trips);
       adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
       
	}
    
    @Override	
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_results, menu);
        return true;
    }
}
