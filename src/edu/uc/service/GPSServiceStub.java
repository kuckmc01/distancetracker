package edu.uc.service;


import java.util.Date;
import edu.uc.dao.GPSDAOStub;
import edu.uc.dao.IGPSDAO;
import edu.uc.dto.Coordinates;
import edu.uc.dto.Distance;



public class GPSServiceStub implements IGPSService {

	IGPSDAO gpspersist = new GPSDAOStub();
	Distance newDistance = new Distance();
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

	public boolean trackingStatus() {
		// TODO Auto-generated method stub
		return false;
	}

	public void saveCoordinates(Date currentTime, double latitude,
			double longitude) {
		// TODO Auto-generated method stub
		if(String.valueOf(latitude) != null && String.valueOf(longitude) != null &&
				String.valueOf(latitude).length() != 0 && String.valueOf(longitude).length() != 0	  )
			{
				coordinates.setLatitude(latitude);
				coordinates.setLongitude(longitude);
				coordinates.setCurrentTime(currentTime);
				newDistance.setDistanceCoordinates(coordinates);		
			}
	}

	public void saveDistanceObject() {
		gpspersist.persistDistanceObject(newDistance);
		
	}



}
