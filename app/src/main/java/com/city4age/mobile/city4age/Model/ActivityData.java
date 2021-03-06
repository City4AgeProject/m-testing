package com.city4age.mobile.city4age.Model;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by srdjan.milakovic on 08/07/2017.
 */
public class ActivityData {
    public int favCounter = 0;

    private int id;
    private String activity_name;
    private Date activity_start_date;
    private Date activity_end_date;
    private String activity_description;
    private String activity_enum;
    private ArrayList<GPSData> gpsData;
    private ArrayList<BluetoothData> bluetoothData;
    private ArrayList<WifiData> wifiData;
    private ArrayList<RecognitionData> recognitionData;

    public ActivityData() {
        gpsData = new ArrayList<>();
        bluetoothData = new ArrayList<>();
        wifiData = new ArrayList<>();
        recognitionData = new ArrayList<>();
    }

    public String getActivityEnum() {
        return activity_enum;
    }

    public void setActivityEnum(String activity_enum) {
        this.activity_enum = activity_enum;
    }

    public ArrayList<GPSData> getGpsData() {
        return gpsData;
    }

    public void addSensorData(GPSData data) {
        this.gpsData.add(data);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getActivity_name() {
        return activity_name;
    }

    public void setActivity_name(String activity_name) {
        this.activity_name = activity_name;
    }

    public Date getActivity_start_date() {
        return activity_start_date;
    }

    public void setActivity_start_date(Date activity_start_date) {
        this.activity_start_date = activity_start_date;
    }

    public Date getActivity_end_date() {
        return activity_end_date;
    }

    public void setActivity_end_date(Date activity_end_date) {
        this.activity_end_date = activity_end_date;
    }

    public String getActivity_description() {
        return activity_description;
    }

    public void setActivity_description(String activity_description) {
        this.activity_description = activity_description;
    }

    public ArrayList<BluetoothData> getBluetoothData() {
        return bluetoothData;
    }

    public void addBluetoothData(BluetoothData bluetoothData) {
        this.bluetoothData.add(bluetoothData);
    }

    public ArrayList<WifiData> getWifiData() {
        return wifiData;
    }

    public void addWifiData(WifiData wifiData) {
        this.wifiData.add(wifiData);
    }

    public ArrayList<RecognitionData> getRecognitionData() {
        return recognitionData;
    }

    public void addRecognitionData(RecognitionData recData) {
        this.recognitionData.add(recData);
    }
}
