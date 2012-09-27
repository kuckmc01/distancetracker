package edu.uc.service;

public interface IGPSService  {

	//uses the persistence layer method to save the starting coordinates
	public void saveCoordinates(double longitude, double latitude);
	
	//request longitude and latitude from the persistence layer
	public String fetchCoordinates();
	
	// returns true if the GPS is still tracking coordinates
	public boolean trackingStatus();
	
	public void startLocationManager();
	
	public void startLocationListener();
	
	
	
	
}
