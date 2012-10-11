package edu.uc.dto;

import java.util.Date;

public class Coordinates {

	private Date currentTime;
	private double latitude;
	private double longitude;
	
	
	public Coordinates(Date t, double x, double y)
	{
				setCurrentTime(t);
				setLatitude(x);
				setLongitude(y);
	}
	
	public Coordinates() {
		// TODO Auto-generated constructor stub
	}

	public double getLatitude() {
		return latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public Date getCurrentTime() {
		return currentTime;
	}
	@Override
	public String toString(){
		return "( " + getCurrentTime() + ", " + getLatitude() + ", " + getLongitude() + ")";
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public void setCurrentTime(Date currentTime) {
		this.currentTime = currentTime;
	}


}
