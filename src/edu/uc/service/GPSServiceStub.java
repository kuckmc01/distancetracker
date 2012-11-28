package edu.uc.service;


import java.util.Date;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import edu.uc.dao.GPSDAOStub;
import edu.uc.dao.IGPSDAO;
import edu.uc.dto.Coordinates;
import edu.uc.dto.Distance;
import edu.uc.dao.GPSDAODatabase;
import edu.uc.dao.GPSDAOStub;
import edu.uc.dao.GPSDAOStub;
import edu.uc.dao.GPSDAOStub;
import edu.uc.dao.GPSDAOStub;
import edu.uc.dao.GPSDAOStub;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;


public class GPSServiceStub implements IGPSService {

	private GPSDAODatabase dao;
	IGPSDAO gpspersist = new GPSDAOStub();
	Distance newDistance = new Distance();
	
	
	public void saveStartTime(String startTime) {
		newDistance.setStartTime(startTime);
	}

	public void saveEndTime(String endTime) {
		newDistance.setEndTime(endTime);
	}

	public void saveTodaysDate(String todaysDate) {
		newDistance.setTodaysDate(todaysDate);
	}

	public boolean trackingStatus() {
		// TODO Auto-generated method stub
		return false;
	}

	public void saveCoordinates(Date currentTime, double latitude,
			double longitude, GPSDAODatabase dao, double tripid) {
		if(String.valueOf(latitude) != null && String.valueOf(longitude) != null &&
				String.valueOf(latitude).length() != 0 && String.valueOf(longitude).length() != 0	  )
			{
			Coordinates coordinates = new Coordinates();
				coordinates.setLatitude(latitude);
				coordinates.setLongitude(longitude);
				coordinates.setCurrentTime(currentTime);
				newDistance.setDistanceCoordinates(coordinates);
				
				try {
					
					dao.saveCoordinatesdatabase(coordinates);
					
				} catch (Exception e) {
					 Log.e(getClass().getName(), "Error saving : " + e.getMessage());
					
					e.printStackTrace();
				}
				//part that will store in database clk
				//dao.saveCoordinatesdatabase(coordinates);
			}
	}

	public void saveDistanceObject() {
		gpspersist.persistDistanceObject(newDistance);
		
	}



}
