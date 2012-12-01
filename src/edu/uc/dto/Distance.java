package edu.uc.dto;

import java.util.ArrayList;


public class Distance {

	 
	private String startTime;
	private String endTime;
	private String todaysDate;
	
	private ArrayList<Coordinates> distanceCoordinates = new ArrayList<Coordinates>();
	
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
	public ArrayList<Coordinates> getDistanceCoordinates() {
		return distanceCoordinates;
	}
	public void setDistanceCoordinates(Coordinates theCoordinates) {
		distanceCoordinates.add(theCoordinates);
	}


}
