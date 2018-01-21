package com.city4age.mobile.city4age.Model;

import java.util.Date;

/**
 * Created by srdjan.milakovic on 20/09/2017.
 */
public class BluetoothData {
    private Date timestamp;
    private String device;

    public BluetoothData(Date timestamp, String device) {
        this.timestamp = timestamp;
        this.device = device;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public String getDevice() {
        return device;
    }
}
