package edu.uc.dao;

import java.util.Date;
import java.util.HashMap;

import edu.uc.dto.Distance;

public interface IGPSDAO {

	/**
	 * Currently not used
	 * @param longitude
	 * @param latitude
	 * @param currentTime
	 */
	public void persistCoordinates(double longitude, double latitude, Date currentTime);
	
	//might want to re-think the key, if i had a user log in i could use that but
	//if i had a user log in I would need some kind of session to so each time used would be unique
	//the distance object gets created each time the user hits the key that will make each distance 
	//object unique from each other, so maybe it would work with log in some how... 
	/**
	 * Returns the distance object from the database using the start time as the key
	 * @param key_StartTime  start time 
	 * @return distance object
	 * @throws Exception the exception checks for null or blank start time
	 */
	
	public Distance fetchDistanceObject(String key_StartTime) throws Exception;
	/**
	 * persist the distance object to the database this reduces the amount of tables and other things 
	 * need for storage
	 * @param distanceObject
	 */
	public void persistDistanceObject(Distance distanceObject);
	/**
	 * Returns the hashmap with all the distance objects
	 * @return hashmap
	 */
	public HashMap<String, Distance> fetchAllDistances();
}
