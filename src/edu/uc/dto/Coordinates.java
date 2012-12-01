package edu.uc.dto;

import java.util.Date;

public class Coordinates {

	private Date currentTime;
	private double latitude;
	private double longitude;
	private double tripid;
	
	public Coordinates(Date t, double x, double y, double tripid)
	{
				setCurrentTime(t);
				setLatitude(x);
				setLongitude(y);
				setCurrentTrip(tripid);
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
	public void setCurrentTrip(double tripid){
		this.tripid = tripid;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getCurrentTrip(){
		return tripid;
		}
	
	public void setCurrentTime(Date currentTime) {
		this.currentTime = currentTime;
	}


}
