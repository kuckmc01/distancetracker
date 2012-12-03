package edu.uc.service;


import java.util.Date;

import edu.uc.dto.Coordinates;
import edu.uc.dto.Distance;
import edu.uc.dao.GPSDAODatabase;
import android.util.Log;


public class GPSServiceStub implements IGPSService {

	private GPSDAODatabase dao;

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
			Coordinates coordinates = new Coordinates(currentTime, latitude, longitude, tripid);
			;
				newDistance.setDistanceCoordinates(coordinates);
				
				try {
					//saving the coordinates.
					dao.saveCoordinatesdatabase(coordinates);
					
				} catch (Exception e) {
					 Log.e(getClass().getName(), "Error saving : " + e.getMessage());
					
					e.printStackTrace();
				}
				
			}
	}

	public void saveDistanceObject() {
		// TODO Auto-generated method stub
		
	}

	



}
