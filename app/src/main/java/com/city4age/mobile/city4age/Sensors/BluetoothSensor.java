package com.city4age.mobile.city4age.Sensors;

import android.app.Service;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by srdjan.milakovic on 20/09/2017.
 */
public class BluetoothSensor extends Service {

    private static final String TAG = "BluetoothSensor";

    BluetoothAdapter mBluetoothAdapter;

    // Create a BroadcastReceiver for ACTION_FOUND.
    private final BroadcastReceiver mBluetoothEnableReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(BluetoothAdapter.ACTION_STATE_CHANGED)) {
                final int state = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, BluetoothAdapter.ERROR);

                switch (state){
                    case BluetoothAdapter.STATE_OFF:
                        Log.d(TAG, "BLUETOOTH: STATE OFF");
                        break;
                    case BluetoothAdapter.STATE_TURNING_OFF:
                        Log.d(TAG, "BLUETOOTH: STATE TURNING OFF");
                        break;
                    case BluetoothAdapter.STATE_ON:
                        Log.d(TAG, "BLUETOOTH: STATE ON");
                        startDiscovery();
                        break;
                    case BluetoothAdapter.STATE_TURNING_ON:
                        Log.d(TAG, "BLUETOOTH: STATE TURNING ON");
                        break;
                }
            }
        }
    };
    /*
     * Broadcast Receiver for listing devices that are not yet paired
     * - Executed by btnDiscover() method.
     */
    private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d(TAG, "==BLUETOOTH: ACTION_FOUND.");

            if (BluetoothAdapter.ACTION_DISCOVERY_STARTED.equals(intent.getAction())) {
                Log.d(TAG, "==BLUETOOTH: STARTED.");
            } else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(intent.getAction())) {
                //discovery finishes, dismiss progress dialog
                Log.d(TAG, "==BLUETOOTH: DONE.");
                startDiscovery();
            } else if (BluetoothDevice.ACTION_FOUND.equals(intent.getAction())) {
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                Log.d(TAG, "==BLUETOOTH: " + device.getName() + ": " + device.getAddress());

                Intent i = new Intent("bluetooth_update");
                Bundle b = new Bundle();
                b.putParcelable("device", device);
                i.putExtras(b);
                sendBroadcast(i);
            }
        }
    };

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public void startDiscovery() {
        Log.d(TAG, "==BLUETOOTH: Looking for unpaired devices.");

        if (mBluetoothAdapter.isDiscovering()) {
            Log.d(TAG, "==BLUETOOTH: Canceling discovery.");

            mBluetoothAdapter.cancelDiscovery();
        }

        mBluetoothAdapter.startDiscovery();

        IntentFilter filter = new IntentFilter();
        filter.addAction(BluetoothDevice.ACTION_FOUND);
        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_STARTED);
        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
        registerReceiver(mBroadcastReceiver, filter);
    }

    @Override
    public void onCreate() {
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        if (mBluetoothAdapter == null) {
            Log.d(TAG, "==BLUETOOTH:: Does not have BT capabilities.");
        } else if (!mBluetoothAdapter.isEnabled()) {
            IntentFilter BTIntent = new IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED);
            registerReceiver(mBluetoothEnableReceiver, BTIntent);
            Log.d(TAG, "enableDisableBT: enabling BT.");
            mBluetoothAdapter.enable();
        } else if (mBluetoothAdapter.isEnabled()) {
            startDiscovery();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mBluetoothAdapter.isEnabled()){
            Log.d(TAG, "==BLUETOOTH: disabling BT.");
            mBluetoothAdapter.disable();
        }

        unregisterReceiver(mBroadcastReceiver);
    }
}
