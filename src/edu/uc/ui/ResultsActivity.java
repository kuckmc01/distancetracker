package edu.uc.ui;

import java.util.List;

import edu.uc.dao.GPSDAODatabase;
import edu.uc.service.Calculations;
import edu.uc.service.GPSServiceStub;
import edu.uc.service.IGPSService;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class ResultsActivity extends Activity {
	private GPSDAODatabase dao;
	protected Button btnMapResults;
	protected Button btnCalculate;
	protected TextView lblmiles;
	protected TextView lbltime;
	IGPSService service;
	 static String spinnervalue = null;
    

	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        btnMapResults = (Button) findViewById(R.id.button1);
        btnCalculate = (Button) findViewById(R.id.button2);
        service = new GPSServiceStub();
        dao = new GPSDAODatabase(this);
        final Spinner spinner = (Spinner) findViewById(R.id.spinner1);
        lblmiles = (TextView) findViewById(R.id.miles);
    	lbltime = (TextView) findViewById(R.id.time);
    	double miles = Calculations.calc(dao);
    	double time = Calculations.time(dao);
    	lblmiles.setText("Miles Traveled: " + miles);
        lbltime.setText("Time Traveled: " + time);
        
       
        btnMapResults.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {

				Intent intent = new Intent(v.getContext(), DistanceTrackerMap.class);
				startActivity(intent);
				System.out.println(spinner.getSelectedItem().toString());
		 }
		});
        
        btnCalculate.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {

				System.out.println(spinner.getSelectedItem().toString());
				spinnervalue =  spinner.getSelectedItem().toString();
				
				double miles = Calculations.calc(dao);
		    	double time = Calculations.time(dao);
				lblmiles.setText("Miles Traveled: " + miles);
		        lbltime.setText("Time Traveled: " + time);
		        
		 }
		});
        
       
        
        spinnerpopulate();
    }
    
    public static String spinnerSet() {
		
		return spinnervalue;
        
	}
    public void spinnerpopulate() {

	//setContentView (R.layout.activity_results);
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
