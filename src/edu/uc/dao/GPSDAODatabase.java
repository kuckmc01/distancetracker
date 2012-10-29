package edu.uc.dao;

import java.util.Date;
import java.util.HashMap;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import edu.uc.dto.Coordinates;
import edu.uc.dto.Distance;

/*
 * The goal of this class is to store the coordinates and time into
 * a database for ease of use in calculations. We were previously using a
 * distance object, and we may still use that but it may be easier to store in 
 * a database.
 */
public class GPSDAODatabase extends SQLiteOpenHelper implements IGPSDAO 

 {
	
	 
		public GPSDAODatabase(Context context, String trackresults ) {
		super(context, trackresults, null, 0);
		// TODO Auto-generated constructor stub
	}
		private static final String Coordinates_Table = "coordinates";
		private static final String lattitude = "lattitude";
		private static final String longitude = "longitude";
		private static final String date = "date";
		static final String coID = "coID";
		//creating database
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			// TODO Auto-generated method stub
			db.execSQL("CREATE TABLE "+Coordinates_Table+" (_id INTEGER PRIMARY KEY AUTOINCREMENT, " + coID + " INT, "+ lattitude + " INT, "+longitude + " INT" + date + " date;");


		}
		//saving the coordinates
		public void saveCoordinatesdatabase(Coordinates coordinates)
		{
			ContentValues values = new ContentValues();
			values.put(coID, "1");
			values.put(lattitude, coordinates.getLatitude());
			values.put(longitude, coordinates.getLongitude());
			values.put(date,coordinates.getCurrentTime().toString());
			getWritableDatabase().insert(Coordinates_Table, coID, values);
			getWritableDatabase().insert(Coordinates_Table, date, values);
			getWritableDatabase().insert(Coordinates_Table, longitude, values);
			getWritableDatabase().insert(Coordinates_Table, lattitude, values);
			

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
