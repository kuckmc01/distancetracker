package edu.uc.service;

import java.nio.charset.Charset;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.database.Cursor;
import android.database.DatabaseUtils;
import edu.uc.dao.GPSDAODatabase;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract.Data;
import android.widget.Toast;


public  class Calculations  {
	
	static double  distancetotal;
	
	public static void time(GPSDAODatabase dao)
	
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
			System.out.println("milli1" + milliseconds1);
			long milliseconds2= date2.getTime();

			System.out.println("milli2" + milliseconds2);
			float  totaltime = (milliseconds2 - milliseconds1) / 3600000;
			System.out.println("The total time was:" + totaltime);

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

	
	}

	public static void calc(GPSDAODatabase dao)
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
			
			 latitude2 = Double.valueOf(myCursor.getString(2));
			 longitude2 = Double.valueOf(myCursor.getString(3));
			 
			double totallat =  69.1 * (latitude2 - latitude1);
			 double totallong = 69.1* (longitude2 - longitude1) * Math.cos(latitude1/57.3);
			 
			 latitude1  = latitude2;
			 longitude1 = 	longitude2;
			 
			 myCursor.moveToNext();
			 distancetotal = Math.sqrt ((totallat * totallat) + (totallong * totallong));
			
			 totaltotal = distancetotal + totaltotal;
			 
				System.out.println("The subtotal distance is " + distancetotal);
		}	 
		System.out.println("The total distance is " + totaltotal);
		}
		
	}
	