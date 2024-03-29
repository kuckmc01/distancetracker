package edu.uc.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import android.database.Cursor;
import edu.uc.dao.GPSDAODatabase;


public  class Calculations  {
	
	static double  distancetotal;
	/* This calculates the time and subtracts the start from final.
	 * The date is in milliseconds since 1970 (Epoch time) so we have to divide by 360000 to get hours....
	 */
	public static String time(GPSDAODatabase dao)
	
	{
		Cursor myCursor = dao.Cursor(); 
		String time1;
		String time2;
		myCursor.moveToFirst();
		time1 = myCursor.getString(4);
		myCursor.moveToLast();
		time2 = myCursor.getString(4);
		

		DateFormat formatter;
		
		Date date1;
		Date date2;
		formatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
		try {
			date1 = (Date)formatter.parse(time1);
			date2 = (Date)formatter.parse(time2);
		
			long milliseconds1 = date1.getTime();
			long milliseconds2= date2.getTime();

			long  totaltime = (milliseconds2 - milliseconds1);
			
			String test = String.format("%02d:%02d:%02d", 
				    TimeUnit.MILLISECONDS.toHours(totaltime),
				    TimeUnit.MILLISECONDS.toMinutes(totaltime) - 
				    TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(totaltime)),
				    TimeUnit.MILLISECONDS.toSeconds(totaltime) - 
				    TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(totaltime)));
			
		
			return test;

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "123";
		
		

	
	}

	/*
	 * This is for calculating the coordinates. We use the distance formula with one slight modification due to earth's curvature
	 * We have tested our numbers against different websites and it is very close. More detailed calculations could be more exact.
	 */
	public static double calc(GPSDAODatabase dao)
	{
		
		Cursor myCursor = dao.Cursor(); 
		double latitude1;
		double longitude1;
		double latitude2;
		double longitude2;
		
		myCursor.moveToFirst();
		 latitude1 = Double.valueOf(myCursor.getString(2)) ;
		 longitude1 = Double.valueOf(myCursor.getString(3)) ;
		 
			

		 double totaltotal = 0 ;
		while (!myCursor.isAfterLast())
		{
			//gets second lat and long.
			 latitude2 = Double.valueOf(myCursor.getString(2));
			 longitude2 = Double.valueOf(myCursor.getString(3));
			 
			double totallat =  69.1 * (latitude2 - latitude1);
			 double totallong = 69.1* (longitude2 - longitude1) * Math.cos(latitude1/57.3);
			 
			 latitude1  = latitude2;
			 longitude1 = 	longitude2;
			 
			 myCursor.moveToNext();
			 distancetotal = Math.sqrt ((totallat * totallat) + (totallong * totallong));
			
			 totaltotal = distancetotal + totaltotal;
			 
				
		}	 
		 return totaltotal;
		}
		
	}
	