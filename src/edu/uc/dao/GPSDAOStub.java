package edu.uc.dao;

import java.util.Date;
import java.util.HashMap;

import edu.uc.dto.Distance;

public class GPSDAOStub implements IGPSDAO {

        HashMap<String, Distance> distanceHashMap = new HashMap<String, Distance>();

        public void persistCoordinates(double longitude, double latitude,
                        Date currentTime) {
                // TODO Auto-generated method stub

        }

        public Distance fetchDistanceObject(String key_StartTime) throws Exception {

                if(key_StartTime != null && key_StartTime.length() == 0)
                {
                        if(distanceHashMap.containsKey(key_StartTime))
                        {
                                return distanceHashMap.get(key_StartTime);
                        }
                        else
                        {
                                throw new Exception("Distance record not found");
                        }
                }
                else
                {
                        throw new Exception("Distance record null or blank");
                }

        }

        public void persistDistanceObject(Distance distanceObject) {
                // TODO Auto-generated method stub
                String startTime = distanceObject.getStartTime();
                distanceHashMap.put(startTime, distanceObject);
                HashMap<String, Distance> hamster = distanceHashMap;
                
        }

        public HashMap<String, Distance> fetchAllDistances() {
                return distanceHashMap;
        }

}