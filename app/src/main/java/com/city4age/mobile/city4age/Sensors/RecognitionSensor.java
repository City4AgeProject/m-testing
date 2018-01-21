package com.city4age.mobile.city4age.Sensors;

import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.ActivityRecognition;

public class RecognitionSensor extends Service implements GoogleApiClient.OnConnectionFailedListener, GoogleApiClient.ConnectionCallbacks  {

    private static final long INTERVAL_RECOGNITION = 1 * 60 * 1000; // every 1 minutes

    private GoogleApiClient mGoogleApiClient;
    private PendingIntent mPendingIntentServiceAR;

    @Override
    public void onCreate() {
        connectGoogleAPIAndRequestActivityRecognition();
    }

    @Override
    public void onDestroy() {
        if (mGoogleApiClient != null) {
            removeActivityRecognition();
            mGoogleApiClient.disconnect();
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    // ==========================================================
    //
    //                GOOGLE SERVICES METHODS
    //
    // ==========================================================

    private void connectGoogleAPIAndRequestActivityRecognition() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(ActivityRecognition.API)
                .build();
        mGoogleApiClient.connect();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        if(mGoogleApiClient == null || !mGoogleApiClient.isConnected()) {
            Log.i("onConnected()", "");
            return;
        }

        Log.i("onConnected()", "");
        requestActivityRecognition();
    }

    @Override
    public void onConnectionSuspended(int cause) {
        Log.i("onConnectionSuspended", String.valueOf(cause));
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.i("onConnectionFailed()", connectionResult.getErrorMessage());
    }

    private void requestActivityRecognition() {
        Log.i("startActivityRec", "");

        // prepare pending intent with service which will handle the callbacks
        Intent intent = new Intent(this, ServiceActivityRecognition.class);
        mPendingIntentServiceAR = PendingIntent.getService(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        // start to receive activity recognition
        ActivityRecognition.ActivityRecognitionApi
                .requestActivityUpdates(mGoogleApiClient, INTERVAL_RECOGNITION, mPendingIntentServiceAR);
    }

    private void removeActivityRecognition() {
        Log.i("stopActivityRec", "");

        // prepare pending intent with service which will handle the callbacks
        Intent intent = new Intent(this, ServiceActivityRecognition.class);
        mPendingIntentServiceAR = PendingIntent.getService(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        // stop to receive activity recognition callbacks
        ActivityRecognition.ActivityRecognitionApi
                .removeActivityUpdates(mGoogleApiClient, mPendingIntentServiceAR);

    }
}

