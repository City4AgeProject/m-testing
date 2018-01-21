package com.city4age.mobile.city4age.Sensors;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.android.gms.location.ActivityRecognitionResult;
import com.google.android.gms.location.DetectedActivity;

/**
 * Created by Srle on 12/17/2017.
 */
public class ServiceActivityRecognition extends IntentService {

    private static final String TAG = ServiceActivityRecognition.class.getSimpleName();

    public static final String INTENT_FILTER_ACTION = "activity_detected";
    public static final String EXTRA_CONFIDENCE = "confidence";
    public static final String EXTRA_ACTIVITY = "activity";

    public ServiceActivityRecognition() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if(ActivityRecognitionResult.hasResult(intent)){
            ActivityRecognitionResult result = ActivityRecognitionResult.extractResult(intent);
            if(result != null && result.getMostProbableActivity() != null) {

                String line = getType(result.getMostProbableActivity().getType()) + " (" + result.getMostProbableActivity().getConfidence() + ")";

                Intent intentBroadcast = new Intent(INTENT_FILTER_ACTION);
                intentBroadcast.putExtra(EXTRA_ACTIVITY, getType(result.getMostProbableActivity().getType()));
                intentBroadcast.putExtra(EXTRA_CONFIDENCE, result.getMostProbableActivity().getConfidence());
                sendBroadcast(intentBroadcast);

                Intent i = new Intent("recognition_update");
                Bundle b = new Bundle();
                b.putString("type", line);
                i.putExtras(b);
                sendBroadcast(i);

                Log.i(TAG, line);
            }
        }
    }

    private String getType(int type){
        if(type == DetectedActivity.IN_VEHICLE) {
            return "InVehicle";
        } else if(type == DetectedActivity.ON_BICYCLE) {
            return "OnBicycle";
        } else if(type == DetectedActivity.ON_FOOT) {
            return "OnFoot";
        } else if(type == DetectedActivity.RUNNING) {
            return "Running";
        } else if(type == DetectedActivity.STILL) {
            return "Still";
        } else if(type == DetectedActivity.TILTING) {
            return "Tilting";
        } else if(type == DetectedActivity.WALKING) {
            return "Walking";
        } else if(type == DetectedActivity.UNKNOWN) {
            return "Unknown";
        } else {
            return "???";
        }
    }
}
