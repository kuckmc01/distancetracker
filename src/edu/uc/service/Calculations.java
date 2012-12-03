package edu.uc.service;

import java.nio.charset.Charset;
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
	public static void calc(GPSDAODatabase dao)
	{
		
		Cursor myCursor = dao.Cursor(); 
		double latitude1;
		double longitude1;
		double latitude2;
		double longitude2;
		myCursor.moveToLast();
		 String time2 = myCursor.getString(6);	
		myCursor.moveToFirst();
		 latitude1 = Double.valueOf(myCursor.getString(2)) ;
		 longitude1 = Double.valueOf(myCursor.getString(1)) ;
		 String time1 = myCursor.getString(6);
		 
		 double totaltotal = 0 ;
		while (!myCursor.isAfterLast())
		{
			
			 latitude2 = Double.valueOf(myCursor.getString(2));
			 longitude2 = Double.valueOf(myCursor.getString(1));
			 
			double totallat =  69.1 * (latitude2 - latitude1);
			 double totallong = 69.1* (longitude2 - longitude1) * Math.cos(latitude1/57.3);
			 
			 latitude1  = latitude2;
			 longitude1 = 	longitude2;
			 
			 myCursor.moveToNext();
			 distancetotal = Math.sqrt ((totallat * totallat) + (totallong * totallong));
			
			 totaltotal = distancetotal + totaltotal;
		}	 
		}
		
	}
	