package com.city4age.mobile.city4age;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.city4age.mobile.city4age.Helpers.HttpHelper;
import com.city4age.mobile.city4age.Helpers.JSONToFileHelper;
import com.city4age.mobile.city4age.Model.ActivityData;
import com.city4age.mobile.city4age.Model.BluetoothData;
import com.city4age.mobile.city4age.Model.GPSData;
import com.city4age.mobile.city4age.Model.WifiData;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by srdjan.milakovic on 08/07/2017.
 */

public class SelectActivity extends AppCompatActivity {

    Button btnSelectActivity;
    Button btnSyncToServer;
    private SyncTask mSyncTask = null;
    List<ActivityData> listOfActivities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Reference XML layout
        setContentView(R.layout.activity_select);

        // Add toolbar and icons
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        Drawable drawable = ContextCompat.getDrawable(getApplicationContext(),R.drawable.c4a_menu_icon);
        myToolbar.setOverflowIcon(drawable);

        // Display user name
        SharedPreferences prefs = getSharedPreferences("LOCAL_DATA", 0);
        TextView userName = (TextView) findViewById(R.id.user_full_name);
        userName.setText(prefs.getString("user_name", ""));

        // Reference select activity btn and add onClick listener
        btnSelectActivity = (Button)findViewById(R.id.btn_select_activity);
        btnSelectActivity.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                goToActivityList();
            }
        });

        btnSyncToServer = (Button)findViewById(R.id.btn_sync);
        btnSyncToServer.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String savedData = JSONToFileHelper.getData(getApplicationContext());

                if (savedData != null && !savedData.isEmpty()) {
                    Type listType = new TypeToken<ArrayList<ActivityData>>(){}.getType();
                    listOfActivities = new Gson().fromJson(savedData, listType);
                }
                else{
                    listOfActivities = new ArrayList<ActivityData>();
                }

                mSyncTask = new SyncTask((ArrayList<ActivityData>) listOfActivities);
                mSyncTask.execute((Void) null);
            }
        });
    }

    public void goToActivityList() {
        Intent intentToSelect = new Intent(this, ListActivity.class);
        startActivity(intentToSelect);
    }

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

    private class SyncTask extends AsyncTask<Void, Void, Boolean> {

        private final ArrayList<ActivityData> activities;

        SyncTask(ArrayList<ActivityData> activities) {
            this.activities = activities;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            JSONObject result = null;
            Boolean status = false;

            JSONObject jsonParam = new JSONObject();

            /*
            {
              "ID": 1000003,
              "date": "Sat Sep 23 00:53:47 GMT+02:00 2017",
              "activities": [
                {
                  "type": "Walking",
                  "start": "Sat Sep 23 00:39:29 GMT+02:00 2017",
                  "end": "Sat Sep 23 00:43:30 GMT+02:00 2017",
                  "gps": [
                    {
                      "date": "Sat Sep 23 00:40:36 GMT+02:00 2017",
                      "longitude": 20.404110533433894,
                      "latitude": 44.84603834112401
                    },
                    {
                      "date": "Sat Sep 23 00:40:39 GMT+02:00 2017",
                      "longitude": 20.404035630554926,
                      "latitude": 44.84603711446953
                    }
                  ],
                  "bluetooth": [],
                  "wifi": [
                    {
                      "date": "Sat Sep 23 00:39:43 GMT+02:00 2017",
                      "devices": "[guest.caenazzo.com, Spile10, cvelezemun, anaradmilo, TP-LINK_A75AFC, , bd132c]"
                    },
                    {
                      "date": "Sat Sep 23 00:39:48 GMT+02:00 2017",
                      "devices": "[guest.caenazzo.com, Spile10, cvelezemun, anaradmilo, TP-LINK_A75AFC, , bd132c]"
                    },
                    {
                      "date": "Sat Sep 23 00:40:49 GMT+02:00 2017",
                      "devices": "[guest.caenazzo.com, Spile10, cvelezemun, anaradmilo, , MilaT]"
                    },
                    {
                      "date": "Sat Sep 23 00:41:34 GMT+02:00 2017",
                      "devices": "[guest.caenazzo.com, Spile10, cvelezemun, anaradmilo, , MilaT]"
                    },
                    {
                      "date": "Sat Sep 23 00:41:49 GMT+02:00 2017",
                      "devices": "[guest.caenazzo.com, Spile10, cvelezemun, anaradmilo, ]"
                    }
                  ]
                },
                {
                  "type": "Walking",
                  "start": "Sat Sep 23 00:51:59 GMT+02:00 2017",
                  "end": "Sat Sep 23 00:53:40 GMT+02:00 2017",
                  "gps": [
                    {
                      "date": "Sat Sep 23 00:52:13 GMT+02:00 2017",
                      "longitude": 20.404162768344822,
                      "latitude": 44.846115213222596
                    },
                    {
                      "date": "Sat Sep 23 00:53:10 GMT+02:00 2017",
                      "longitude": 20.403976028010643,
                      "latitude": 44.84580095619349
                    },
                    {
                      "date": "Sat Sep 23 00:53:13 GMT+02:00 2017",
                      "longitude": 20.404014163408664,
                      "latitude": 44.845842049947656
                    }
                  ],
                  "bluetooth": [
                    {
                      "date": "Sat Sep 23 00:53:23 GMT+02:00 2017",
                      "device": "Galaxy J5"
                    },
                    {
                      "date": "Sat Sep 23 00:53:25 GMT+02:00 2017",
                      "device": "Milak J5"
                    }
                  ],
                  "wifi": [
                    {
                      "date": "Sat Sep 23 00:52:08 GMT+02:00 2017",
                      "devices": "[guest.caenazzo.com, Spile10, cvelezemun, , bd132c, anaradmilo, TP-LINK_A75AFC, ASUS_Home]"
                    },
                    {
                      "date": "Sat Sep 23 00:52:09 GMT+02:00 2017",
                      "devices": "[guest.caenazzo.com, Spile10, cvelezemun, , bd132c, anaradmilo, TP-LINK_A75AFC, HG532e-1D6FDB]"
                    },
                    {
                      "date": "Sat Sep 23 00:53:37 GMT+02:00 2017",
                      "devices": "[guest.caenazzo.com, Spile10, cvelezemun, , bd132c, anaradmilo, HG532e-1D6FDB, TP-LINK_A75AFC, ASUS_Home]"
                    }
                  ]
                }
              ]
            }
             */
            try {
                SharedPreferences prefs = getSharedPreferences("LOCAL_DATA", 0);
                jsonParam.put("ID", Integer.valueOf(prefs.getString("user_id", "0")));
                //jsonParam.put("uirId", Integer.valueOf(prefs.getString("user_id", "0")));

                Date now = new Date();
                jsonParam.put("date", now.toString());
                JSONArray activitiesJson = new JSONArray();
                for (ActivityData act : this.activities) {
                    // Global activity data
                    JSONObject jsonAct = new JSONObject();
                    jsonAct.put("type", act.getActivityEnum());
                    jsonAct.put("start", act.getActivity_start_date().toString());
                    jsonAct.put("end", act.getActivity_end_date().toString());

                    // GPS activity data
                    JSONArray gpsJson = new JSONArray();
                    for (GPSData sens : act.getGpsData()) {
                        JSONObject gpsLocJson = new JSONObject();
                        gpsLocJson.put("date", sens.getTimestamp());
                        gpsLocJson.put("longitude", sens.getLongitude());
                        gpsLocJson.put("latitude", sens.getLatitude());
                        gpsJson.put(gpsLocJson);
                    }
                    jsonAct.put("gpss", gpsJson);

                    // Bluetooth activity data
                    JSONArray blueJson = new JSONArray();
                    for (BluetoothData sens : act.getBluetoothData()) {
                        JSONObject blueDeviceJson = new JSONObject();
                        blueDeviceJson.put("date", sens.getTimestamp());
                        blueDeviceJson.put("device", sens.getDevice());
                        blueJson.put(blueDeviceJson);
                    }
                    jsonAct.put("bluetooths", blueJson);

                    // Wifi data
                    JSONArray wifiJson = new JSONArray();
                    for (WifiData sens : act.getWifiData()) {
                        JSONObject wifiDeviceJson = new JSONObject();
                        wifiDeviceJson.put("date", sens.getTimestamp());
                        //wifiDeviceJson.put("devices", sens.getDevices());
                        wifiDeviceJson.put("device", sens.getDevices());
                        wifiJson.put(wifiDeviceJson);
                    }
                    jsonAct.put("wifis", wifiJson);

                    activitiesJson.put(jsonAct);
                }
                jsonParam.put("activities", activitiesJson);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            //String json = jsonParam.toString();

            try {
                //local Wampserver test
                //result = HttpHelper.postJSONToUrl("http://10.0.2.2:80/c4a.php", jsonParam);
                //Andrija local machine test
                //result = HttpHelper.postJSONToUrl("http://172.28.43.159:8080/C4A-dashboard/rest/android/postFromAndroid", jsonParam);
                //server Madrid
                result = HttpHelper.postJSONToUrl("http://138.4.10.230:8000/C4A-dashboard/rest/android/postFromAndroid", jsonParam);
            } catch (JSONException e) {
                return false;
            } catch (IOException e) {
                return false;
            }

            try {
                status = result.getString("result").equals("1");
            } catch (JSONException e) {
                return false;
            }

            return status;
        }

        protected void onPostExecute(final Boolean result) {
            Context context = getApplicationContext();
            CharSequence textSuccess = "Sync successful!";
            CharSequence textError = "Sync failed!";

            if (result) {
                Toast toast = Toast.makeText(context, textSuccess, Toast.LENGTH_SHORT);
                toast.show();
                JSONToFileHelper.saveData(context, "");
            } else {
                Toast toast = Toast.makeText(context, textError, Toast.LENGTH_SHORT);
                toast.show();
            }
        }
    }
}
