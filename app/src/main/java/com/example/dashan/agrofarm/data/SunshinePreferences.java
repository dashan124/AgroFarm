
package com.example.dashan.agrofarm.data;
import  com.example.dashan.agrofarm.models.PlaceInfo;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.Preference;
import android.preference.PreferenceManager;

import com.example.dashan.agrofarm.R;
import com.example.dashan.agrofarm.models.PlaceInfo;

public class SunshinePreferences {


    /*
     * Human readable location string, provided by the API.  Because for styling,
     * "Mountain View" is more recognizable than 94043.
     */
    public static final String PREF_CITY_NAME = "city_name";

    /*
     * In order to uniquely pinpoint the location on the map when we launch the
     * map intent, we store the latitude and longitude.
     */
    public static final String PREF_COORD_LAT = "coord_lat";
    public static final String PREF_COORD_LONG = "coord_long";

    /*
     * Before you implement methods to return your REAL preference for location,
     * we provide some default values to work with.
     */
    private static final String DEFAULT_WEATHER_LOCATION = "NITK,India";
    private static final double[] DEFAULT_WEATHER_COORDINATES = {13.0116344, 74.79215169999999};

    private static final String DEFAULT_MAP_LOCATION =
            "1600 Amphitheatre Parkway, Mountain View, CA 94043";

    static public void setLocationDetails(Context c, String cityName, double lat, double lon) {
        /** This will be implemented in a future lesson **/
    }

    /**
     * Helper method to handle setting a new location in preferences.  When this happens
     * the database may need to be cleared.
     *
     * @param c               Context used to get the SharedPreferences
     * @param locationSetting The location string used to request updates from the server.
     * @param lat             The latitude of the city
     * @param lon             The longitude of the city
     */
    static public void setLocation(Context c, String locationSetting, double lat, double lon) {
        /** This will be implemented in a future lesson **/
    }

    /**
     * Resets the stored location coordinates.
     *
     * @param c Context used to get the SharedPreferences
     */
    static public void resetLocationCoordinates(Context c) {
        /** This will be implemented in a future lesson **/
    }

    /**
     * Returns the location currently set in Preferences. The default location this method
     * will return is "94043,USA", which is Mountain View, California. Mountain View is the
     * home of the headquarters of the Googleplex!
     *
     * @param context Context used to get the SharedPreferences
     * @return Location The current user has set in SharedPreferences. Will default to
     * "94043,USA" if SharedPreferences have not been implemented yet.
     */
    public static String getPreferredWeatherLocation(Context context) {
        /** This will be implemented in a future lesson **/
        SharedPreferences preferences= PreferenceManager.getDefaultSharedPreferences(context);
        String keyforLocation=context.getString(R.string.pref_location_key);
        String defaultLocation = context.getString(R.string.pref_location_default);
        return preferences.getString(keyforLocation, defaultLocation);

    }

    /**
     * Returns true if the user has selected metric temperature display.
     *
     * @param context Context used to get the SharedPreferences
     * @return true If metric display should be used
     */
    public static boolean isMetric(Context context) {
        /** This will be implemented in a future lesson **/

        SharedPreferences preferences=PreferenceManager.getDefaultSharedPreferences(context);

        String keyForUnits=context.getString(R.string.pref_units_key);
        String defaultUnits=context.getString(R.string.pref_units_metric);
        String preferredunits=preferences.getString(keyForUnits,defaultUnits);
        String metric=context.getString(R.string.pref_units_metric);
        boolean userPreferMetric;
        if(metric.equals(preferredunits)){
            userPreferMetric=true;
        }
        else{
            userPreferMetric=false;
        }
        return userPreferMetric;
    }

    /**
     * Returns the location coordinates associated with the location.  Note that these coordinates
     * may not be set, which results in (0,0) being returned. (conveniently, 0,0 is in the middle
     * of the ocean off the west coast of Africa)
     *
     * @param context Used to get the SharedPreferences
     * @return An array containing the two coordinate values.
     */
    public static double[] getLocationCoordinates(Context context) {
        return getDefaultWeatherCoordinates();
    }

    /**
     * Returns true if the latitude and longitude values are available. The latitude and
     * longitude will not be available until the lesson where the PlacePicker API is taught.
     *
     * @param context used to get the SharedPreferences
     * @return true if lat/long are set
     */
    public static boolean isLocationLatLonAvailable(Context context) {
        /** This will be implemented in a future lesson **/
        return false;
    }

    private static String getDefaultWeatherLocation() {
        /** This will be implemented in a future lesson **/
        return DEFAULT_WEATHER_LOCATION;
    }

    public static double[] getDefaultWeatherCoordinates() {
        //** This will be implemented in a future lesson **//
        //double latitude,logitude;
        return DEFAULT_WEATHER_COORDINATES;
    }

}