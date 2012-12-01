package edu.uc.dao;

import java.util.Date;
import java.util.HashMap;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;
import edu.uc.dto.Coordinates;
import edu.uc.dto.Distance;
import edu.uc.service.Calculations;
import edu.uc.service.GPSServiceStub;

/*
 * The goal of this class is to store the coordinates and time into
 * a database for ease of use in calculations. We were previously using a
 * distance object.
 */
public class GPSDAODatabase extends SQLiteOpenHelper implements IGPSDAO 

 {
	
		private static final String Coordinates_Save = "Coordinates_Save";
		private static final String latitude = "latitude";
		private static final String longitude = "longitude";
		private static final String dates = "dates";
		private static final String tripid = "tripID";
		
		static final String coID = "_id";
		//creating database
		
		public GPSDAODatabase(Context context ) {
			super(context, "Coordinates_Save", null, 1 );
		}
		@Override
		public void onCreate(SQLiteDatabase db) {
		String createTableStatement = "CREATE TABLE Coordinates_Save (_id INTEGER PRIMARY KEY AUTOINCREMENT, tripID DOUBLE, coID INT, latitude  DOUBLE, longitude DOUBLE,  dates date);";
		
			db.execSQL(createTableStatement);
		}
		
		public void saveCoordinatesdatabase(Coordinates coordinates) throws Exception
		{
		ContentValues values = new ContentValues();
		
		
		
		values.put(latitude, coordinates.getLatitude());
		values.put(longitude, coordinates.getLongitude());
		values.put(dates, coordinates.getCurrentTime().toString());
		values.put(tripid, coordinates.getCurrentTrip());
		
		
		
			getWritableDatabase().insert(Coordinates_Save, dates, values);
		
				 
		
		
		}
		public android.database.Cursor Cursor()
		{
			Cursor cursor = getReadableDatabase().query(Coordinates_Save, new String[] {"_id", "tripID" ,"longitude",
	  		  "latitude", "dates" }, null, null, null,null, null);    
		
			return cursor;
		}
	
	public Double CursorTripID()
	{
		Cursor cursor = getReadableDatabase().query(Coordinates_Save, new String[] {"_id", "tripID" ,"longitude",
	  		  "latitude", "dates" }, null, null, null,null, null);    
			
			cursor.moveToLast();
			System.out.println(Double.valueOf(cursor.getString(1)));
			 return Double.valueOf(cursor.getString(1));
			
			
	}
			
		
		
		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub

		}
		//Below methods are from GPSDAO
		public void persistCoordinates(double longitude, double latitude,
				Date currentTime) {
			// TODO Auto-generated method stub
			
		}
		public Distance fetchDistanceObject(String key_StartTime)
				throws Exception {
			// TODO Auto-generated method stub
			return null;
		}
		public void persistDistanceObject(Distance distanceObject) {
			// TODO Auto-generated method stub
			
		}
		public HashMap<String, Distance> fetchAllDistances() {
			// TODO Auto-generated method stub
			return null;
		}



}
