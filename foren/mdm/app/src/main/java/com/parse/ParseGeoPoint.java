package com.parse;

import android.location.Criteria;
import android.location.Location;
import bolts.Continuation;
import bolts.Task;
import java.util.Locale;

/* loaded from: classes2.dex */
public class ParseGeoPoint {
    static double EARTH_MEAN_RADIUS_KM = 6371.0d;
    static double EARTH_MEAN_RADIUS_MILE = 3958.8d;
    private double latitude;
    private double longitude;

    public ParseGeoPoint() {
        this.latitude = 0.0d;
        this.longitude = 0.0d;
    }

    public ParseGeoPoint(double latitude, double longitude) {
        this.latitude = 0.0d;
        this.longitude = 0.0d;
        setLatitude(latitude);
        setLongitude(longitude);
    }

    public ParseGeoPoint(ParseGeoPoint point) {
        this(point.getLatitude(), point.getLongitude());
    }

    public void setLatitude(double latitude) {
        if (latitude > 90.0d || latitude < -90.0d) {
            throw new IllegalArgumentException("Latitude must be within the range (-90.0, 90.0).");
        }
        this.latitude = latitude;
    }

    public double getLatitude() {
        return this.latitude;
    }

    public void setLongitude(double longitude) {
        if (longitude > 180.0d || longitude < -180.0d) {
            throw new IllegalArgumentException("Longitude must be within the range (-180.0, 180.0).");
        }
        this.longitude = longitude;
    }

    public double getLongitude() {
        return this.longitude;
    }

    public double distanceInRadiansTo(ParseGeoPoint point) {
        double lat1rad = this.latitude * 0.017453292519943295d;
        double lat2rad = point.getLatitude() * 0.017453292519943295d;
        double sinDeltaLatDiv2 = Math.sin((lat1rad - lat2rad) / 2.0d);
        double sinDeltaLongDiv2 = Math.sin(((this.longitude * 0.017453292519943295d) - (point.getLongitude() * 0.017453292519943295d)) / 2.0d);
        return 2.0d * Math.asin(Math.sqrt(Math.min(1.0d, (sinDeltaLatDiv2 * sinDeltaLatDiv2) + (Math.cos(lat1rad) * Math.cos(lat2rad) * sinDeltaLongDiv2 * sinDeltaLongDiv2))));
    }

    public double distanceInKilometersTo(ParseGeoPoint point) {
        return distanceInRadiansTo(point) * EARTH_MEAN_RADIUS_KM;
    }

    public double distanceInMilesTo(ParseGeoPoint point) {
        return distanceInRadiansTo(point) * EARTH_MEAN_RADIUS_MILE;
    }

    public static Task<ParseGeoPoint> getCurrentLocationInBackground(long timeout) {
        Criteria criteria = new Criteria();
        criteria.setAccuracy(0);
        criteria.setPowerRequirement(0);
        return LocationNotifier.getCurrentLocationAsync(Parse.getApplicationContext(), timeout, criteria).onSuccess(new Continuation<Location, ParseGeoPoint>() { // from class: com.parse.ParseGeoPoint.1
            @Override // bolts.Continuation
            public ParseGeoPoint then(Task<Location> task) throws Exception {
                Location location = task.getResult();
                return new ParseGeoPoint(location.getLatitude(), location.getLongitude());
            }
        });
    }

    public static void getCurrentLocationInBackground(long timeout, LocationCallback callback) {
        ParseTaskUtils.callbackOnMainThreadAsync(getCurrentLocationInBackground(timeout), callback);
    }

    public static Task<ParseGeoPoint> getCurrentLocationInBackground(long timeout, Criteria criteria) {
        return LocationNotifier.getCurrentLocationAsync(Parse.getApplicationContext(), timeout, criteria).onSuccess(new Continuation<Location, ParseGeoPoint>() { // from class: com.parse.ParseGeoPoint.2
            @Override // bolts.Continuation
            public ParseGeoPoint then(Task<Location> task) throws Exception {
                Location location = task.getResult();
                return new ParseGeoPoint(location.getLatitude(), location.getLongitude());
            }
        });
    }

    public static void getCurrentLocationInBackground(long timeout, Criteria criteria, LocationCallback callback) {
        ParseTaskUtils.callbackOnMainThreadAsync(getCurrentLocationInBackground(timeout, criteria), callback);
    }

    public String toString() {
        return String.format(Locale.US, "ParseGeoPoint[%.6f,%.6f]", Double.valueOf(this.latitude), Double.valueOf(this.longitude));
    }
}
