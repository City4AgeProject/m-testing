package com.city4age.mobile.city4age.Model;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Srle on 9/22/2017.
 */
public class WifiData {
    private Date timestamp;
    private ArrayList<String> devices;

    public WifiData(Date timestamp, ArrayList<String> devices) {
        this.devices = devices;
        this.timestamp = timestamp;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public ArrayList<String> getDevices() {
        return devices;
    }
}
