package com.city4age.mobile.city4age.Model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by srdjan.milakovic on 08/07/2017.
 */

public class ActivityC4A {
    private int id;
    private String activity_name;
    private Date activity_start_date;
    private Date activity_end_date;
    private Date creation_date;
    private Date since;
    private int house_number;
    private int location_id;
    private String activity_description;
    private ArrayList<SensorDataC4A> sensor_data;

    public ActivityC4A() {
        sensor_data= new ArrayList<SensorDataC4A>();
    }

    public ArrayList<SensorDataC4A> getSensor_data() {
        return sensor_data;
    }

    public void setSensor_data(ArrayList<SensorDataC4A> sensor_data) {
        this.sensor_data = sensor_data;
    }

    public void addSensorData(SensorDataC4A data) {
        this.sensor_data.add(data);
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

    public Date getCreation_date() {
        return creation_date;
    }

    public void setCreation_date(Date creation_date) {
        this.creation_date = creation_date;
    }

    public Date getSince() {
        return since;
    }

    public void setSince(Date since) {
        this.since = since;
    }

    public int getHouse_number() {
        return house_number;
    }

    public void setHouse_number(int house_number) {
        this.house_number = house_number;
    }

    public int getLocation_id() {
        return location_id;
    }

    public void setLocation_id(int location_id) {
        this.location_id = location_id;
    }

    public String getActivity_description() {
        return activity_description;
    }

    public void setActivity_description(String activity_description) {
        this.activity_description = activity_description;
    }
}
