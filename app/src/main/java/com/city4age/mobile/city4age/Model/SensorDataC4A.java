package com.city4age.mobile.city4age.Model;

import java.util.Date;

/**
 * Created by srdjan.milakovic on 30/07/2017.
 */

public class SensorDataC4A {
    private Date timestamp;
    private double latitude;
    private double longitude;

    public SensorDataC4A(Date timestamp, double latitude, double longitude) {
        this.timestamp = timestamp;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(int latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(int longitude) {
        this.longitude = longitude;
    }
}
