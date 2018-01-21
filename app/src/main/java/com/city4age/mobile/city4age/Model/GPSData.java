package com.city4age.mobile.city4age.Model;

import java.util.Date;

/**
 * Created by srdjan.milakovic on 30/07/2017.
 */
public class GPSData {
    private Date timestamp;
    private double latitude;
    private double longitude;

    public GPSData(Date timestamp, double latitude, double longitude) {
        this.timestamp = timestamp;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}
