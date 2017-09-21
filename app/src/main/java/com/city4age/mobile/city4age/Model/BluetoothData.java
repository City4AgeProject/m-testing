package com.city4age.mobile.city4age.Model;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by srdjan.milakovic on 20/09/2017.
 */
public class BluetoothData {
    private Date timestamp;
    private ArrayList<String> devices;

    public BluetoothData(Date timestamp, ArrayList<String> devices) {
        this.timestamp = timestamp;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public ArrayList<String> getDevices() {
        return devices;
    }

    public void setDevices(ArrayList<String> devices) {
        this.devices = devices;
    }
}
