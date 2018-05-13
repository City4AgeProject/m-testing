package com.city4age.mobile.city4age;

import android.Manifest;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.city4age.mobile.city4age.Helpers.JSONToFileHelper;
import com.city4age.mobile.city4age.Model.ActivityData;
import com.city4age.mobile.city4age.Model.BluetoothData;
import com.city4age.mobile.city4age.Model.GPSData;
import com.city4age.mobile.city4age.Model.RecognitionData;
import com.city4age.mobile.city4age.Model.WifiData;
import com.city4age.mobile.city4age.Sensors.BluetoothSensor;
import com.city4age.mobile.city4age.Sensors.GPSSensor;
import com.city4age.mobile.city4age.Sensors.RecognitionSensor;
import com.city4age.mobile.city4age.Sensors.WifiSensor;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by srdjan.milakovic on 08/07/2017.
 */
public class TrackActivity extends AppCompatActivity {

    public static final String TAG = TrackActivity.class.getSimpleName();
    public static final String ACTIVITY_DATA = "ACTIVITY_DATA";
    
    private String mActivityJSON;
    private ActivityData mActivityData;
    private BroadcastReceiver mBroadcastReceiver;

    //Stopwatch variables
    private Button butnstart, butnCancel, btnFinnish;
    private TextView time;
    private long starttime = 0L;
    private long timeInMilliseconds = 0L;
    private long timeSwapBuff = 0L;
    private long updatedtime = 0L;
    private int t = 1;
    private int secs = 0;
    private int mins = 0;
    private int milliseconds = 0;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        Drawable drawable = ContextCompat.getDrawable(getApplicationContext(),R.drawable.c4a_menu_icon);
        myToolbar.setOverflowIcon(drawable);

        // Read type of activity from Intent that invoked screen
        final Gson gson = new Gson();
        Bundle extras = getIntent().getExtras();
        mActivityJSON = extras.getString(ACTIVITY_DATA);
        mActivityData = gson.fromJson(mActivityJSON, ActivityData.class);

        Integer id = mActivityData.getId();
        Map<Integer,Integer> map = JSONToFileHelper.loadMap(getApplicationContext());
        Integer val = map.containsKey(id)?map.get(id) : Integer.valueOf(0);
        map.put(id, val + 1);
        JSONToFileHelper.saveMap(getApplicationContext(), map);

        // Display activity info
        TextView activityDescription = (TextView) findViewById(R.id.activity_track_description);
        activityDescription.setText(mActivityData.getActivity_description());
        TextView activityName = (TextView) findViewById(R.id.activity_track_name);
        activityName.setText(mActivityData.getActivity_name());

        // Stopwatch controls
        butnstart = (Button) findViewById(R.id.start);
        butnCancel = (Button) findViewById(R.id.btn_cancel);
        time = (TextView) findViewById(R.id.timer);

        // Save to file control
        btnFinnish = (Button) findViewById(R.id.finish);

        // Btn start click listener
        butnstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (t == 1) {
                    // timer will start
                    mActivityData.setActivity_start_date(new Date());
                    butnstart.setText("In progress...");
                    starttime = SystemClock.uptimeMillis();
                    handler.postDelayed(updateTimer, 0);
                    t = 0;
                    butnstart.setAlpha(0.5f);
                    checkPermisionsAndActivateSensors();
                }
            }
        });

        // Btn cancel click listener
        butnCancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

//                starttime = 0L;
//                timeInMilliseconds = 0L;
//                timeSwapBuff = 0L;
//                updatedtime = 0L;
//                t = 1;
//                secs = 0;
//                mins = 0;
//                milliseconds = 0;
//                butnstart.setText("Start");
//                handler.removeCallbacks(updateTimer);
//                time.setText("00:00:00");

                Intent intent = new Intent(getApplicationContext(), ListActivity.class);
                finish();
                startActivity(intent);
            }
        });

        // Btn finish click listener
        btnFinnish.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String savedData = JSONToFileHelper.getData(getApplicationContext());
                mActivityData.setActivity_end_date(new Date());

                if (savedData != null && !savedData.isEmpty()) {
                    Type listType = new TypeToken<ArrayList<ActivityData>>(){}.getType();
                    List<ActivityData> listOfActivities = new Gson().fromJson(savedData, listType);
                    listOfActivities.add(mActivityData);

                    String updatedListOfActivities = gson.toJson(listOfActivities);
                    JSONToFileHelper.saveData(getApplicationContext(), updatedListOfActivities);
                } else {
                    List<ActivityData> newListOfActivities = new ArrayList<>();
                    newListOfActivities.add(mActivityData);

                    String newListToSaveToFile = gson.toJson(newListOfActivities);
                    JSONToFileHelper.saveData(getApplicationContext(), newListToSaveToFile);
                }

                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(getApplicationContext(), "Activity saved", duration);
                toast.show();

                deactivateSensors();
                finish();
                Intent intent = new Intent(getApplicationContext(), ListActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mBroadcastReceiver == null){
            mBroadcastReceiver = new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {
                    String action = intent.getAction();
                    Date date = new Date();
                    if (action.compareTo("location_update") == 0) {
                        Log.d("==Receiver", "location_update");
                        Bundle b = intent.getExtras();
                        GPSData gpsData = new GPSData(date,
                                b.getDouble("latitude"),
                                b.getDouble("longitude")
                        );
                        mActivityData.addSensorData(gpsData);
                    } else if (action.compareTo("bluetooth_update") == 0) {
                        BluetoothDevice device = intent.getExtras().getParcelable("device");
                        assert device != null;
                        Log.d("==Receiver", "bluetooth_update" + device.getName());
                        BluetoothData btData = new BluetoothData(date, device.getName());
                        mActivityData.addBluetoothData(btData);
                    } else if (action.compareTo("wifi_update") == 0) {
                        String[] devices = intent.getExtras().getStringArray("devices");
                        assert devices != null;
                        WifiData wifiData = new WifiData(date, new ArrayList<>(Arrays.asList(devices)));
                        mActivityData.addWifiData(wifiData);
                    } else if (action.compareTo("recognition_update") == 0) {
                        RecognitionData recData = new RecognitionData(date, intent.getExtras().getString("type"));
                        mActivityData.addRecognitionData(recData);
                    }
                }
            };
        }

        IntentFilter sensorFilterIntent = new IntentFilter();
        sensorFilterIntent.addAction("location_update");
        sensorFilterIntent.addAction("bluetooth_update");
        sensorFilterIntent.addAction("wifi_update");
        sensorFilterIntent.addAction("recognition_update");
        registerReceiver(mBroadcastReceiver, sensorFilterIntent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mBroadcastReceiver != null){
            unregisterReceiver(mBroadcastReceiver);
        }
    }

    private void checkPermisionsAndActivateSensors() {
        if (Build.VERSION.SDK_INT >= 23
                //&& ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                //&& ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
        ) {
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 123 );
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 123) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                activateSensors();
            }
        }
    }

    public void activateSensors() {

        startService(new Intent(getApplicationContext(), GPSSensor.class));
        startService(new Intent(getApplicationContext(), BluetoothSensor.class));
        startService(new Intent(getApplicationContext(), WifiSensor.class));
        startService(new Intent(getApplicationContext(), RecognitionSensor.class));
    }

    public void deactivateSensors() {
        stopService(new Intent(getApplicationContext(), GPSSensor.class));
        stopService(new Intent(getApplicationContext(), BluetoothSensor.class));
        stopService(new Intent(getApplicationContext(), WifiSensor.class));
        stopService(new Intent(getApplicationContext(), RecognitionSensor.class));
    }

    public Runnable updateTimer = new Runnable() {
        public void run() {
            timeInMilliseconds = SystemClock.uptimeMillis() - starttime;
            updatedtime = timeSwapBuff + timeInMilliseconds;
            secs = (int) (updatedtime / 1000);
            mins = secs / 60;
            secs = secs % 60;
            milliseconds = (int) (updatedtime % 1000);
            time.setText("" + mins + ":" + String.format("%02d", secs) + ":"
                    + String.format("%03d", milliseconds));
            time.setTextColor(Color.GRAY);
            handler.postDelayed(this, 0);
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_list_activities) {
            Intent intentToSelect = new Intent(this, FinishedActivity.class);
            finish();
            startActivity(intentToSelect);
            return true;
        }

        if (id == R.id.action_sign_out) {
            Intent intentToSelect = new Intent(this, MainActivity.class);
            finish();
            startActivity(intentToSelect);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
