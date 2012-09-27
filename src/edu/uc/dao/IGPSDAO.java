package edu.uc.dao;

public interface IGPSDAO {

	public void persistCoordinates(double longitude, double latitude);
	
	public String fetchStoredCoordinates();
}
