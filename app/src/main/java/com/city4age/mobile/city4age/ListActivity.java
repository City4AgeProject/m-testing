package com.city4age.mobile.city4age;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import java.util.List;
import com.city4age.mobile.city4age.Adapters.*;
import com.city4age.mobile.city4age.Model.*;

/**
 * Created by srdjan.milakovic on 08/07/2017.
 */

public class ListActivity extends AppCompatActivity {

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<ActivityData>> listDataChild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Reference XML layout
        setContentView(R.layout.activity_list);

        // Add toolbar and icons
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        Drawable drawable = ContextCompat.getDrawable(getApplicationContext(),R.drawable.c4a_menu_icon);
        myToolbar.setOverflowIcon(drawable);

        // Get the list view
        expListView = (ExpandableListView) findViewById(R.id.activity_exp_list);

        // Preparing list data
        prepareListData();

        // Setting list adapter
        listAdapter = new CustomExpandableListAdapter(this, listDataHeader, listDataChild);
        expListView.setAdapter(listAdapter);
    }

    /*
     * Preparing the list data
     */
    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<ActivityData>>();

        // Adding child data
        listDataHeader.add("Motility");
        listDataHeader.add("Physical Activity");
        listDataHeader.add("Basic Activities of Daily Living");
        listDataHeader.add("Instrumental Activities of Daily Living (IADL)");
        listDataHeader.add("Socialization");
        listDataHeader.add("Cultural");

        // Adding child data
        List<ActivityData> Motility = new ArrayList<ActivityData>();

        ActivityData item0101 = new ActivityData();
        item0101.setId(101);
        item0101.setActivity_name("Walking");
        item0101.setActivity_start_date(new Date());
        item0101.setActivity_end_date(new Date());
        item0101.setActivity_description("WALKING AT SLOW OR NORMAL PACE");
        Motility.add(item0101);

        ActivityData item0102 = new ActivityData();
        item0102.setId(102);
        item0102.setActivity_name("Climbing stairs");
        item0102.setActivity_start_date(new Date());
        item0102.setActivity_end_date(new Date());
        item0102.setActivity_description("");
        Motility.add(item0102);

        ActivityData item0103 = new ActivityData();
        item0103.setId(103);
        item0103.setActivity_name("Still/Moving");
        item0103.setActivity_start_date(new Date());
        item0103.setActivity_end_date(new Date());
        item0103.setActivity_description("");
        Motility.add(item0103);

        ActivityData item0104 = new ActivityData();
        item0104.setId(104);
        item0104.setActivity_name("Moving across rooms");
        item0104.setActivity_start_date(new Date());
        item0104.setActivity_end_date(new Date());
        item0104.setActivity_description("");
        Motility.add(item0104);

        listDataChild.put(listDataHeader.get(0), Motility);

        List<ActivityData> PhysicalActivity = new ArrayList<ActivityData>();
        ActivityData item0201 = new ActivityData();
        item0201.setId(201);
        item0201.setActivity_name("Test activity");
        item0201.setActivity_start_date(new Date());
        item0201.setActivity_end_date(new Date());
        item0201.setActivity_description("");
        PhysicalActivity.add(item0201);
        listDataChild.put(listDataHeader.get(1), PhysicalActivity);

        List<ActivityData> BasicActivitiesOfDailyLiving = new ArrayList<ActivityData>();

        ActivityData item0301 = new ActivityData();
        item0301.setId(301);
        item0301.setActivity_name("Bathing and showering");
        item0301.setActivity_start_date(new Date());
        item0301.setActivity_end_date(new Date());
        item0301.setActivity_description("");
        BasicActivitiesOfDailyLiving.add(item0301);

        ActivityData item0302 = new ActivityData();
        item0302.setId(302);
        item0302.setActivity_name("Dressing");
        item0302.setActivity_start_date(new Date());
        item0302.setActivity_end_date(new Date());
        item0302.setActivity_description("");
        BasicActivitiesOfDailyLiving.add(item0302);

        ActivityData item0303 = new ActivityData();
        item0303.setId(303);
        item0303.setActivity_name("Self-feeding");
        item0303.setActivity_start_date(new Date());
        item0303.setActivity_end_date(new Date());
        item0303.setActivity_description("");
        BasicActivitiesOfDailyLiving.add(item0303);

        ActivityData item0304 = new ActivityData();
        item0304.setId(304);
        item0304.setActivity_name("Personal hygiene and grooming");
        item0304.setActivity_start_date(new Date());
        item0304.setActivity_end_date(new Date());
        item0304.setActivity_description("");
        BasicActivitiesOfDailyLiving.add(item0304);

        ActivityData item0305 = new ActivityData();
        item0305.setId(305);
        item0305.setActivity_name("Toilet hygiene");
        item0305.setActivity_start_date(new Date());
        item0305.setActivity_end_date(new Date());
        item0305.setActivity_description("");
        BasicActivitiesOfDailyLiving.add(item0305);

        ActivityData item0306 = new ActivityData();
        item0306.setId(306);
        item0306.setActivity_name("Going out");
        item0306.setActivity_start_date(new Date());
        item0306.setActivity_end_date(new Date());
        item0306.setActivity_description("");
        BasicActivitiesOfDailyLiving.add(item0306);

        listDataChild.put(listDataHeader.get(2), BasicActivitiesOfDailyLiving);

        List<ActivityData> InstrumentalActivitiesOfDailyLiving = new ArrayList<ActivityData>();

        ActivityData item0401 = new ActivityData();
        item0401.setId(401);
        item0401.setActivity_name("Preparing food");
        item0401.setActivity_start_date(new Date());
        item0401.setActivity_end_date(new Date());
        item0401.setActivity_description("");
        InstrumentalActivitiesOfDailyLiving.add(item0401);

        ActivityData item0402 = new ActivityData();
        item0402.setId(402);
        item0402.setActivity_name("Housekeeping");
        item0402.setActivity_start_date(new Date());
        item0402.setActivity_end_date(new Date());
        item0402.setActivity_description("");
        InstrumentalActivitiesOfDailyLiving.add(item0402);

        ActivityData item0403 = new ActivityData();
        item0403.setId(403);
        item0403.setActivity_name("Doing laundry");
        item0403.setActivity_start_date(new Date());
        item0403.setActivity_end_date(new Date());
        item0403.setActivity_description("");
        InstrumentalActivitiesOfDailyLiving.add(item0403);

        ActivityData item0404 = new ActivityData();
        item0404.setId(404);
        item0404.setActivity_name("New media communication (skype, messenger, facebook, whatsapp...)");
        item0404.setActivity_start_date(new Date());
        item0404.setActivity_end_date(new Date());
        item0404.setActivity_description("");
        InstrumentalActivitiesOfDailyLiving.add(item0404);

        ActivityData item0405 = new ActivityData();
        item0405.setId(405);
        item0405.setActivity_name("Shopping groceries");
        item0405.setActivity_start_date(new Date());
        item0405.setActivity_end_date(new Date());
        item0405.setActivity_description("");
        InstrumentalActivitiesOfDailyLiving.add(item0405);

        ActivityData item0406 = new ActivityData();
        item0406.setId(406);
        item0406.setActivity_name("Shopping other");
        item0406.setActivity_start_date(new Date());
        item0406.setActivity_end_date(new Date());
        item0406.setActivity_description("");
        InstrumentalActivitiesOfDailyLiving.add(item0406);

        ActivityData item0407 = new ActivityData();
        item0407.setId(407);
        item0407.setActivity_name("Using public transport");
        item0407.setActivity_start_date(new Date());
        item0407.setActivity_end_date(new Date());
        item0407.setActivity_description("");
        InstrumentalActivitiesOfDailyLiving.add(item0407);

        ActivityData item0408 = new ActivityData();
        item0408.setId(408);
        item0408.setActivity_name("Using own transport");
        item0408.setActivity_start_date(new Date());
        item0408.setActivity_end_date(new Date());
        item0408.setActivity_description("");
        InstrumentalActivitiesOfDailyLiving.add(item0408);

        ActivityData item0409 = new ActivityData();
        item0409.setId(409);
        item0409.setActivity_name("Taking medication");
        item0409.setActivity_start_date(new Date());
        item0409.setActivity_end_date(new Date());
        item0409.setActivity_description("");
        InstrumentalActivitiesOfDailyLiving.add(item0409);

        listDataChild.put(listDataHeader.get(3), InstrumentalActivitiesOfDailyLiving);

        List<ActivityData> Socialization = new ArrayList<ActivityData>();

        ActivityData item0501 = new ActivityData();
        item0501.setId(501);
        item0501.setActivity_name("Visiting family");
        item0501.setActivity_start_date(new Date());
        item0501.setActivity_end_date(new Date());
        item0501.setActivity_description("");
        Socialization.add(item0501);

        ActivityData item0502 = new ActivityData();
        item0502.setId(502);
        item0502.setActivity_name("Visiting friends");
        item0502.setActivity_start_date(new Date());
        item0502.setActivity_end_date(new Date());
        item0502.setActivity_description("");
        Socialization.add(item0502);

        ActivityData item0503 = new ActivityData();
        item0503.setId(503);
        item0503.setActivity_name("Attending senior centers");
        item0503.setActivity_start_date(new Date());
        item0503.setActivity_end_date(new Date());
        item0503.setActivity_description("");
        Socialization.add(item0503);

        ActivityData item0504 = new ActivityData();
        item0504.setId(504);
        item0504.setActivity_name("Attending other social places");
        item0504.setActivity_start_date(new Date());
        item0504.setActivity_end_date(new Date());
        item0504.setActivity_description("");
        Socialization.add(item0504);

        listDataChild.put(listDataHeader.get(4), Socialization);

        List<ActivityData> Cultural = new ArrayList<ActivityData>();

        ActivityData item0601 = new ActivityData();
        item0601.setId(601);
        item0601.setActivity_name("Visiting culture/entertainment places");
        item0601.setActivity_start_date(new Date());
        item0601.setActivity_end_date(new Date());
        item0601.setActivity_description("");
        Cultural.add(item0601);

        ActivityData item0602 = new ActivityData();
        item0602.setId(602);
        item0602.setActivity_name("Watching TV");
        item0602.setActivity_start_date(new Date());
        item0602.setActivity_end_date(new Date());
        item0602.setActivity_description("");
        Cultural.add(item0602);

        ActivityData item0603 = new ActivityData();
        item0603.setId(603);
        item0603.setActivity_name("Reading");
        item0603.setActivity_start_date(new Date());
        item0603.setActivity_end_date(new Date());
        item0603.setActivity_description("");
        Cultural.add(item0603);

        listDataChild.put(listDataHeader.get(5), Cultural);
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
}
