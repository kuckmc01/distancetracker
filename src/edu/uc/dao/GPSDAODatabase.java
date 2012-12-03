package edu.uc.dao;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import edu.uc.dto.Coordinates;
import edu.uc.dto.Distance;
import edu.uc.ui.ResultsActivity;

/*
 * The goal of this class is to store the coordinates and time into
 * a database for ease of use in calculations. We were previously using a
 * distance object.
 */
public class GPSDAODatabase extends SQLiteOpenHelper implements IGPSDAO 

 {
	//These are the strings we will use to create the database. 
		private static final String Coordinates_Save = "Coordinates_Save";
		private static final String latitude = "latitude";
		private static final String longitude = "longitude";
		private static final String dates = "dates";
		private static final String tripid = "tripID";
		String tripID1 = null;
		static final String coID = "_id";
		//creating database
		
		public GPSDAODatabase(Context context ) {
			super(context, "Coordinates_Save", null, 1 );
		}
		@Override
		//Query to create the database.
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
		//placing lat,long,dates,tripid into the database
		
		
			getWritableDatabase().insert(Coordinates_Save, dates, values);
		
				 
		
		
		}
		
		/*This cursor is used for calculations. The goal is to use the spinner to select the tripID but currently we have it hardcoded
		* The spinner is populated and onclick could populate this query.
		*
		*/
		public android.database.Cursor Cursor()
		{
			
			
			tripID1 = ResultsActivity.spinnerSet();
			if (tripID1 != null) {
				System.out.println(tripID1);
			}
			else {
				tripID1 = "1";
			}
			
			String whereclause = "tripID =" + tripID1;
			
			
			Cursor cursor = getReadableDatabase().query(Coordinates_Save, new String[] {"_id", "tripID" ,"latitude",
	  		  "longitude", "dates" }, whereclause, null, null,null, null);    
			
			return cursor;
		}
	
	public Double CursorTripID()
	{
		/* This Cursor is used to populate the tripid. In another database (oracle, mysql) Two tables would have been used, I think.
		We can use two tables in SQLite but the nested selects would have been messy. This is just as easy. This will be called when a trip
		is started.
		*/
		Cursor cursor = getReadableDatabase().query(Coordinates_Save, new String[] {"_id", "tripID" ,"longitude",
	  		  "latitude", "dates" }, null, null, null,null, null);    
			

			cursor.moveToLast();
			if(cursor.getPosition() == -1){
				return 0.0;
			}
			System.out.println(Double.valueOf(cursor.getString(1)));
			
			
			 return Double.valueOf(cursor.getString(1));
			
			
	}
	/* This trip is populate the cursor with all the trip ids. 
	 * 
	 */
	public List<String> SelectCursorTripID()
	{
		
		Cursor cursor = getReadableDatabase().rawQuery("select distinct tripid from Coordinates_save", null);
			cursor.moveToFirst();
			List<String> trips = new ArrayList<String>();

			while (!cursor.isAfterLast())
			{
				trips.add(cursor.getString(0));
				cursor.moveToNext();

			}
			
			return trips;
			
	}	
	/*
	 * This cursor is to populate the map coordinates to use for points.
	 */
	public List<Coordinates> CursorForMap()
	{
	
	
tripID1 = ResultsActivity.spinnerSet();
	if (tripID1 != null) {
		System.out.println(tripID1);
	}
	else {
		tripID1 = "1";
	}
	
	String Query = "select * from Coordinates_Save where tripid =" + tripID1 ; 
	
		Cursor cursor = getReadableDatabase().rawQuery(Query, null);
		List<Coordinates> coordinatesList = new ArrayList<Coordinates>();
		
		cursor.moveToFirst();
		while (!cursor.isAfterLast())
		{
			Coordinates coordinate = new Coordinates();

			coordinate.setLatitude(Double.valueOf(cursor.getString(3)));
			coordinate.setLongitude(Double.valueOf(cursor.getString(4)));
			DateFormat formatter;
			
			
			formatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
			
			String thing = cursor.getString(5);
			
			
			//We coudl not figure out how to a date out of the database as a date, even though it is stored as date in SQLlite
			Date date;
			try {
				date = (Date)formatter.parse(thing);
				coordinate.setCurrentTime(date);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
			coordinatesList.add(coordinate);
			 cursor.moveToNext();
		}
		
		 return coordinatesList;
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
