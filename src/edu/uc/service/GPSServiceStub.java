package edu.uc.service;

import java.util.Date;
import java.util.LinkedHashMap;
import edu.uc.dao.GPSDAOStub;
import edu.uc.dao.IGPSDAO;
import edu.uc.dto.*;


public class GPSServiceStub implements IGPSService {

	IGPSDAO gpspersist = new GPSDAOStub();
	Distance newDistance = new Distance();
	LinkedHashMap<String, Coordinates> coordinatesLHM = new LinkedHashMap<String, Coordinates>(); 
	Coordinates coordinates = new Coordinates();

	public void saveStartTime(String startTime) {
		newDistance.setStartTime(startTime);
	}

	public void saveEndTime(String endTime) {
		newDistance.setEndTime(endTime);
	}

	public void saveTodaysDate(String todaysDate) {
		newDistance.setTodaysDate(todaysDate);
	}

	public void saveCoordinates(String currentTime, double longitude,
			double latitude) {
		if(String.valueOf(latitude).length() != 0 || String.valueOf(latitude) != null ||
		   String.valueOf(longitude).length() != 0	|| String.valueOf(longitude) != null )
		{
			coordinates.setLatitude(latitude);
			coordinates.setLongitude(longitude);
			coordinatesLHM.put(currentTime, coordinates);
			newDistance.setDistanceCoordinates(coordinatesLHM);		
		}
	}

	public void saveDistanceObject() {
		// TODO Auto-generated method stub
		//is the cooridnatesLHM null ???? if so do not save to database
		gpspersist.persistDistanceObject(newDistance);
		
	}
	public boolean trackingStatus() {
		// TODO Auto-generated method stub
		return false;
	}

}
