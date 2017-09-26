package com.city4age.mobile.city4age.Sensors;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcelable;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Srle on 9/22/2017.
 */
public class WifiSensor extends Service {

    private static final int MY_PERMISSIONS_REQUEST_CHANGE_WIFI_STATE = 123;
    WifiManager mWifiManager;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private final BroadcastReceiver mWifiScanReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION)) {
                List<ScanResult> wifiList = mWifiManager.getScanResults();
                String[] devices = new String[wifiList.size()];
                for (int i = 0; i < wifiList.size(); i++) {
                    devices[i] = wifiList.get(i).SSID;
                }
                Intent i = new Intent("wifi_update");
                Bundle b = new Bundle();
                b.putStringArray("devices", devices);
                i.putExtras(b);
                sendBroadcast(i);
            }
        }
    };

    @Override
    public void onCreate() {
        mWifiManager = (WifiManager) this.getApplicationContext().getSystemService(WIFI_SERVICE);
        this.registerReceiver(mWifiScanReceiver, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
        mWifiManager.startScan();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
