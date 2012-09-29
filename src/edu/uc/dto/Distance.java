package edu.uc.dto;

import java.util.Date;
import java.util.LinkedHashMap;

public class Distance {

	 
	private String startTime;
	private String endTime;
	private String todaysDate;
	private LinkedHashMap<String, Coordinates> distanceCoordinates;
	
	public void setStartTime(String startTime){
		this.startTime = startTime;	
	}
	public String getStartTime(){
		return startTime;
	}
	public void setEndTime(String endTime){
		this.endTime = endTime;
	}
	public String getEndTime(){
		return endTime;
	}
	public void setTodaysDate(String todaysDate){
		this.todaysDate = todaysDate;
	}
	public String getTodaysDate(){
		return todaysDate;
	}
	public LinkedHashMap<String, Coordinates> getDistanceCoordinates() {
		return distanceCoordinates;
	}
	public void setDistanceCoordinates(LinkedHashMap<String, Coordinates> distanceCoordinates) {
		this.distanceCoordinates = distanceCoordinates;
	}

}
