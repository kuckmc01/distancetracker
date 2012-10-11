package edu.uc.service;

import java.util.Date;




public interface IGPSService  {


	/**
	 * Saves the coordinates to the distance object 
	 * @param currentTime currentTime currentTime is used as the key to save the coordinates into a LinkedHashMap, 
	 * this will give a sequential order that can be used later to paint the area on a map at given times
	 * @param latitude the latitude coordinate
	 * @param longitude the longitude coordinate
	 */
	public void saveCoordinates(Date currentTime, double latitude, double  longitude);
	
	/**
	 * Saves the start time into the distance object
	 * @param startTime  The formatted start time 
	 */
	public void saveStartTime(String startTime);  //may run into issues with Date type vs String type need to look into
	
	/**
	 * Saves the end time into the distance object
	 * @param endTime  The formatted end time
	 */
	public void saveEndTime(String endTime);
	
	/**
	 * Saves today's date to the distance object
	 * @param todaysDate
	 */
	public void saveTodaysDate(String todaysDate);
	
	/**
	 * Persists the distance object to the database through the DAO
	 */
	public void saveDistanceObject();
	
	/**
	 * Not sure if I need this 
	 * @return
	 */
//	public String fetchCoordinates();
	
	/**
	 * Not sure if I need this yet
	 * @return
	 */
	public boolean trackingStatus();
	
	
}
