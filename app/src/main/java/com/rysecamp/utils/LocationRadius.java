package com.rysecamp.utils;

import android.location.Location;
import android.util.Log;

public class LocationRadius {

    public static double getDistanceBetweenGeoPoints(double latA, double longA, double latB, double longB) {

        double result = 0;
        try {

            Location locationA = new Location("");
            locationA.setLatitude(latA);
            locationA.setLongitude(longA);

            Location locationB = new Location("");
            locationB.setLatitude(latB);
            locationB.setLongitude(longB);
            result = locationA.distanceTo(locationB);


        } catch (Exception ex) {
            ex.getMessage();
            Log.d("Dis error  : ", "" + ex.getMessage());
        }

        return result;
    }
}
