package com.city4age.mobile.city4age;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.city4age.mobile.city4age.Helpers.JSONToFileHelper;
import com.city4age.mobile.city4age.Model.ActivityC4A;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TrackActivity extends AppCompatActivity {
    String activityJSON;
    ActivityC4A activity;
    static final String ACTIVITY_DATA = "ACTIVITY_DATA";

    //Stopwatch variables
    Button butnstart, butnCancel;
    TextView time;
    long starttime = 0L;
    long timeInMilliseconds = 0L;
    long timeSwapBuff = 0L;
    long updatedtime = 0L;
    int t = 1;
    int secs = 0;
    int mins = 0;
    int milliseconds = 0;
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        Drawable drawable = ContextCompat.getDrawable(getApplicationContext(),R.drawable.c4a_menu_icon);
        myToolbar.setOverflowIcon(drawable);

        final Gson gson = new Gson();
        Bundle extras = getIntent().getExtras();
        activityJSON = extras.getString(ACTIVITY_DATA);
        activity = gson.fromJson(activityJSON, ActivityC4A.class);

//        int duration = Toast.LENGTH_SHORT;
//        Toast toast = Toast.makeText(this, activityJSON, duration);
//        toast.show();

        TextView activityDescription = (TextView) findViewById(R.id.activity_track_description);
        activityDescription.setText(activity.getActivity_description());
        TextView activityName = (TextView) findViewById(R.id.activity_track_name);
        activityName.setText(activity.getActivity_name());

        //Stopwatch controls
        butnstart = (Button) findViewById(R.id.start);
        butnCancel = (Button) findViewById(R.id.btn_cancel);
        time = (TextView) findViewById(R.id.timer);

        butnstart.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                activity.setActivity_start_date(new Date());

                if (t == 1) {

                    //timer will start
                    butnstart.setText("Pause");
                    starttime = SystemClock.uptimeMillis();
                    handler.postDelayed(updateTimer, 0);
                    t = 0;
                } else {
                    //timer will pause
                    butnstart.setText("Start");
                    time.setTextColor(Color.GRAY);
                    timeSwapBuff += timeInMilliseconds;
                    handler.removeCallbacks(updateTimer);
                    t = 1;
                }

            }
        });

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

        //Save to file control
        Button btnFinnish = (Button) findViewById(R.id.finish);

        btnFinnish.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                JSONToFileHelper dataManipulation = new JSONToFileHelper();
                String savedData = dataManipulation.getData(getApplicationContext());
                activity.setActivity_end_date(new Date());

                if(savedData != null && !savedData.isEmpty()) {
                    Type listType = new TypeToken<ArrayList<ActivityC4A>>(){}.getType();
                    List<ActivityC4A> listOfActivities = new Gson().fromJson(savedData, listType);
                    listOfActivities.add(activity);

                    String updatedListOfActivities = gson.toJson(listOfActivities);
                    dataManipulation.saveData(getApplicationContext(), updatedListOfActivities);

                } else {
                    List<ActivityC4A> newListOfActivites = new ArrayList<ActivityC4A>();
                    newListOfActivites.add(activity);

                    String newListToSaveToFile = gson.toJson(newListOfActivites);
                    dataManipulation.saveData(getApplicationContext(), newListToSaveToFile);
                }

//                int duration = Toast.LENGTH_SHORT;
//                Toast toast = Toast.makeText(getApplicationContext(), savedData, duration);
//                toast.show();

                finish();
                Intent intent = new Intent(getApplicationContext(), ListActivity.class);
                startActivity(intent);

            }
        });

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
