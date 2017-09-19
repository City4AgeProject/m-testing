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
import android.widget.TextView;
import android.widget.Toast;

import com.city4age.mobile.city4age.Helpers.HttpHelper;
import com.city4age.mobile.city4age.Helpers.JSONToFileHelper;
import com.city4age.mobile.city4age.Model.ActivityC4A;
import com.city4age.mobile.city4age.Model.SensorDataC4A;
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
    List<ActivityC4A> listOfActivities;

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
                    Type listType = new TypeToken<ArrayList<ActivityC4A>>(){}.getType();
                    listOfActivities = new Gson().fromJson(savedData, listType);
                }
                else{
                    listOfActivities = new ArrayList<ActivityC4A>();
                }

                mSyncTask = new SyncTask((ArrayList<ActivityC4A>) listOfActivities);
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

        private final ArrayList<ActivityC4A> activities;

        SyncTask(ArrayList<ActivityC4A> activities) {
            this.activities = activities;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            JSONObject result = null;
            Boolean status = false;

            JSONObject jsonParam = new JSONObject();

            /*
            {
              "ID": "3",
              "date": "Tue Aug 01 22:11:31 GMT+00:00 2017",
              "activities": [
                {
                  "type": "Walking",
                  "start": "Tue Aug 01 22:04:50 GMT+00:00 2017",
                  "end": "Tue Aug 01 22:05:02 GMT+00:00 2017",
                  "gps": [
                    {
                      "longitude": -122.084,
                      "latitude": 37.421998333333,
                      "date": "Tue Aug 01 22:04:50 GMT+00:00 2017"
                    },
                    {
                      "longitude": -122.084,
                      "latitude": 37.421998333333,
                      "date": "Tue Aug 01 22:04:54 GMT+00:00 2017"
                    },
                    {
                      "longitude": -122.084,
                      "latitude": 12,
                      "date": "Tue Aug 01 22:04:58 GMT+00:00 2017"
                    }
                  ]
                },
                {
                  "type": "Climbing stairs",
                  "start": "Tue Aug 01 22:07:06 GMT+00:00 2017",
                  "end": "Tue Aug 01 22:07:15 GMT+00:00 2017",
                  "gps": [
                    {
                      "longitude": -122.084,
                      "latitude": 43,
                      "date": "Tue Aug 01 22:07:09 GMT+00:00 2017"
                    },
                    {
                      "longitude": -122.084,
                      "latitude": 3,
                      "date": "Tue Aug 01 22:07:13 GMT+00:00 2017"
                    }
                  ]
                }
              ]
            }
             */
            try {
                SharedPreferences prefs = getSharedPreferences("LOCAL_DATA", 0);
                jsonParam.put("ID", prefs.getString("user_id", "0"));

                Date now = new Date();
                jsonParam.put("date", now.toString());
                JSONArray activitiesJson = new JSONArray();
                for (ActivityC4A act : this.activities) {
                    JSONObject jsonAct = new JSONObject();
                    jsonAct.put("type", act.getActivity_name());
                    jsonAct.put("start", act.getActivity_start_date().toString());
                    jsonAct.put("end", act.getActivity_end_date().toString());

                    JSONArray gpsJson = new JSONArray();
                    for (SensorDataC4A sens : act.getSensor_data()) {
                        JSONObject gpsLocJson = new JSONObject();
                        gpsLocJson.put("longitude", sens.getLongitude());
                        gpsLocJson.put("latitude", sens.getLatitude());
                        gpsLocJson.put("date", sens.getTimestamp());
                        gpsJson.put(gpsLocJson);
                    }
                    jsonAct.put("gps", gpsJson);

                    activitiesJson.put(jsonAct);
                }
                jsonParam.put("activities", activitiesJson);
            } catch (JSONException e) {
                e.printStackTrace();
            }

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
            } else {
                Toast toast = Toast.makeText(context, textError, Toast.LENGTH_SHORT);
                toast.show();
            }
        }
    }
}
