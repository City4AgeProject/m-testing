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
    HashMap<String, List<ActivityC4A>> listDataChild;

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
        listDataChild = new HashMap<String, List<ActivityC4A>>();

        // Adding child data
        listDataHeader.add("Motility");
        listDataHeader.add("Physical Activity");
        listDataHeader.add("Basic Activities of Daily Living");
        listDataHeader.add("Instrumental Activities of Daily Living (IADL)");
        listDataHeader.add("Socialization");
        listDataHeader.add("Cultural");

        // Adding child data
        List<ActivityC4A> Motility = new ArrayList<ActivityC4A>();

        ActivityC4A item0101 = new ActivityC4A();
        item0101.setId(101);
        item0101.setActivity_name("Walking");
        item0101.setActivity_start_date(new Date());
        item0101.setActivity_end_date(new Date());
        item0101.setCreation_date(new Date());
        item0101.setSince(new Date());
        item0101.setHouse_number(101);
        item0101.setLocation_id(101);
        item0101.setActivity_description("WALKING AT SLOW OR NORMAL PACE");
        Motility.add(item0101);

        ActivityC4A item0102 = new ActivityC4A();
        item0102.setId(102);
        item0102.setActivity_name("Climbing stairs");
        item0102.setActivity_start_date(new Date());
        item0102.setActivity_end_date(new Date());
        item0102.setCreation_date(new Date());
        item0102.setSince(new Date());
        item0102.setHouse_number(102);
        item0102.setLocation_id(102);
        item0102.setActivity_description("");
        Motility.add(item0102);

        ActivityC4A item0103 = new ActivityC4A();
        item0103.setId(103);
        item0103.setActivity_name("Still/Moving");
        item0103.setActivity_start_date(new Date());
        item0103.setActivity_end_date(new Date());
        item0103.setCreation_date(new Date());
        item0103.setSince(new Date());
        item0103.setHouse_number(103);
        item0103.setLocation_id(103);
        item0103.setActivity_description("");
        Motility.add(item0103);

        ActivityC4A item0104 = new ActivityC4A();
        item0104.setId(104);
        item0104.setActivity_name("Moving across rooms");
        item0104.setActivity_start_date(new Date());
        item0104.setActivity_end_date(new Date());
        item0104.setCreation_date(new Date());
        item0104.setSince(new Date());
        item0104.setHouse_number(104);
        item0104.setLocation_id(104);
        item0104.setActivity_description("");
        Motility.add(item0104);

        listDataChild.put(listDataHeader.get(0), Motility);

        List<ActivityC4A> PhysicalActivity = new ArrayList<ActivityC4A>();
        listDataChild.put(listDataHeader.get(1), PhysicalActivity);

        List<ActivityC4A> BasicActivitiesOfDailyLiving = new ArrayList<ActivityC4A>();

        ActivityC4A item0301 = new ActivityC4A();
        item0301.setId(301);
        item0301.setActivity_name("Bathing and showering");
        item0301.setActivity_start_date(new Date());
        item0301.setActivity_end_date(new Date());
        item0301.setCreation_date(new Date());
        item0301.setSince(new Date());
        item0301.setHouse_number(301);
        item0301.setLocation_id(301);
        item0301.setActivity_description("");
        BasicActivitiesOfDailyLiving.add(item0301);

        ActivityC4A item0302 = new ActivityC4A();
        item0302.setId(302);
        item0302.setActivity_name("Dressing");
        item0302.setActivity_start_date(new Date());
        item0302.setActivity_end_date(new Date());
        item0302.setCreation_date(new Date());
        item0302.setSince(new Date());
        item0302.setHouse_number(302);
        item0302.setLocation_id(302);
        item0302.setActivity_description("");
        BasicActivitiesOfDailyLiving.add(item0302);

        ActivityC4A item0303 = new ActivityC4A();
        item0303.setId(303);
        item0303.setActivity_name("Self-feeding");
        item0303.setActivity_start_date(new Date());
        item0303.setActivity_end_date(new Date());
        item0303.setCreation_date(new Date());
        item0303.setSince(new Date());
        item0303.setHouse_number(303);
        item0303.setLocation_id(303);
        item0303.setActivity_description("");
        BasicActivitiesOfDailyLiving.add(item0303);

        ActivityC4A item0304 = new ActivityC4A();
        item0304.setId(304);
        item0304.setActivity_name("Personal hygiene and grooming");
        item0304.setActivity_start_date(new Date());
        item0304.setActivity_end_date(new Date());
        item0304.setCreation_date(new Date());
        item0304.setSince(new Date());
        item0304.setHouse_number(304);
        item0304.setLocation_id(304);
        item0304.setActivity_description("");
        BasicActivitiesOfDailyLiving.add(item0304);

        ActivityC4A item0305 = new ActivityC4A();
        item0305.setId(305);
        item0305.setActivity_name("Toilet hygiene");
        item0305.setActivity_start_date(new Date());
        item0305.setActivity_end_date(new Date());
        item0305.setCreation_date(new Date());
        item0305.setSince(new Date());
        item0305.setHouse_number(305);
        item0305.setLocation_id(305);
        item0305.setActivity_description("");
        BasicActivitiesOfDailyLiving.add(item0305);

        ActivityC4A item0306 = new ActivityC4A();
        item0306.setId(306);
        item0306.setActivity_name("Going out");
        item0306.setActivity_start_date(new Date());
        item0306.setActivity_end_date(new Date());
        item0306.setCreation_date(new Date());
        item0306.setSince(new Date());
        item0306.setHouse_number(306);
        item0306.setLocation_id(306);
        item0306.setActivity_description("");
        BasicActivitiesOfDailyLiving.add(item0306);

        listDataChild.put(listDataHeader.get(2), BasicActivitiesOfDailyLiving);

        List<ActivityC4A> InstrumentalActivitiesOfDailyLiving = new ArrayList<ActivityC4A>();

        ActivityC4A item0401 = new ActivityC4A();
        item0401.setId(401);
        item0401.setActivity_name("Preparing food");
        item0401.setActivity_start_date(new Date());
        item0401.setActivity_end_date(new Date());
        item0401.setCreation_date(new Date());
        item0401.setSince(new Date());
        item0401.setHouse_number(401);
        item0401.setLocation_id(401);
        item0401.setActivity_description("");
        InstrumentalActivitiesOfDailyLiving.add(item0401);

        ActivityC4A item0402 = new ActivityC4A();
        item0402.setId(402);
        item0402.setActivity_name("Housekeeping");
        item0402.setActivity_start_date(new Date());
        item0402.setActivity_end_date(new Date());
        item0402.setCreation_date(new Date());
        item0402.setSince(new Date());
        item0402.setHouse_number(402);
        item0402.setLocation_id(402);
        item0402.setActivity_description("");
        InstrumentalActivitiesOfDailyLiving.add(item0402);

        ActivityC4A item0403 = new ActivityC4A();
        item0403.setId(403);
        item0403.setActivity_name("Doing laundry");
        item0403.setActivity_start_date(new Date());
        item0403.setActivity_end_date(new Date());
        item0403.setCreation_date(new Date());
        item0403.setSince(new Date());
        item0403.setHouse_number(403);
        item0403.setLocation_id(403);
        item0403.setActivity_description("");
        InstrumentalActivitiesOfDailyLiving.add(item0403);

        ActivityC4A item0404 = new ActivityC4A();
        item0404.setId(404);
        item0404.setActivity_name("New media communication (skype, messenger, facebook, whatsapp...)");
        item0404.setActivity_start_date(new Date());
        item0404.setActivity_end_date(new Date());
        item0404.setCreation_date(new Date());
        item0404.setSince(new Date());
        item0404.setHouse_number(404);
        item0404.setLocation_id(404);
        item0404.setActivity_description("");
        InstrumentalActivitiesOfDailyLiving.add(item0404);

        ActivityC4A item0405 = new ActivityC4A();
        item0405.setId(405);
        item0405.setActivity_name("Shopping groceries");
        item0405.setActivity_start_date(new Date());
        item0405.setActivity_end_date(new Date());
        item0405.setCreation_date(new Date());
        item0405.setSince(new Date());
        item0405.setHouse_number(405);
        item0405.setLocation_id(405);
        item0405.setActivity_description("");
        InstrumentalActivitiesOfDailyLiving.add(item0405);

        ActivityC4A item0406 = new ActivityC4A();
        item0406.setId(406);
        item0406.setActivity_name("Shopping other");
        item0406.setActivity_start_date(new Date());
        item0406.setActivity_end_date(new Date());
        item0406.setCreation_date(new Date());
        item0406.setSince(new Date());
        item0406.setHouse_number(406);
        item0406.setLocation_id(406);
        item0406.setActivity_description("");
        InstrumentalActivitiesOfDailyLiving.add(item0406);

        ActivityC4A item0407 = new ActivityC4A();
        item0407.setId(407);
        item0407.setActivity_name("Using public transport");
        item0407.setActivity_start_date(new Date());
        item0407.setActivity_end_date(new Date());
        item0407.setCreation_date(new Date());
        item0407.setSince(new Date());
        item0407.setHouse_number(407);
        item0407.setLocation_id(407);
        item0407.setActivity_description("");
        InstrumentalActivitiesOfDailyLiving.add(item0407);

        ActivityC4A item0408 = new ActivityC4A();
        item0408.setId(408);
        item0408.setActivity_name("Using own transport");
        item0408.setActivity_start_date(new Date());
        item0408.setActivity_end_date(new Date());
        item0408.setCreation_date(new Date());
        item0408.setSince(new Date());
        item0408.setHouse_number(408);
        item0408.setLocation_id(408);
        item0408.setActivity_description("");
        InstrumentalActivitiesOfDailyLiving.add(item0408);

        ActivityC4A item0409 = new ActivityC4A();
        item0409.setId(409);
        item0409.setActivity_name("Taking medication");
        item0409.setActivity_start_date(new Date());
        item0409.setActivity_end_date(new Date());
        item0409.setCreation_date(new Date());
        item0409.setSince(new Date());
        item0409.setHouse_number(409);
        item0409.setLocation_id(409);
        item0409.setActivity_description("");
        InstrumentalActivitiesOfDailyLiving.add(item0409);

        listDataChild.put(listDataHeader.get(3), InstrumentalActivitiesOfDailyLiving);

        List<ActivityC4A> Socialization = new ArrayList<ActivityC4A>();

        ActivityC4A item0501 = new ActivityC4A();
        item0501.setId(501);
        item0501.setActivity_name("Visiting family");
        item0501.setActivity_start_date(new Date());
        item0501.setActivity_end_date(new Date());
        item0501.setCreation_date(new Date());
        item0501.setSince(new Date());
        item0501.setHouse_number(501);
        item0501.setLocation_id(501);
        item0501.setActivity_description("");
        Socialization.add(item0501);

        ActivityC4A item0502 = new ActivityC4A();
        item0502.setId(502);
        item0502.setActivity_name("Visiting friends");
        item0502.setActivity_start_date(new Date());
        item0502.setActivity_end_date(new Date());
        item0502.setCreation_date(new Date());
        item0502.setSince(new Date());
        item0502.setHouse_number(502);
        item0502.setLocation_id(502);
        item0502.setActivity_description("");
        Socialization.add(item0502);

        ActivityC4A item0503 = new ActivityC4A();
        item0503.setId(503);
        item0503.setActivity_name("Attending senior centers");
        item0503.setActivity_start_date(new Date());
        item0503.setActivity_end_date(new Date());
        item0503.setCreation_date(new Date());
        item0503.setSince(new Date());
        item0503.setHouse_number(503);
        item0503.setLocation_id(503);
        item0503.setActivity_description("");
        Socialization.add(item0503);

        ActivityC4A item0504 = new ActivityC4A();
        item0504.setId(504);
        item0504.setActivity_name("Attending other social places");
        item0504.setActivity_start_date(new Date());
        item0504.setActivity_end_date(new Date());
        item0504.setCreation_date(new Date());
        item0504.setSince(new Date());
        item0504.setHouse_number(504);
        item0504.setLocation_id(504);
        item0504.setActivity_description("");
        Socialization.add(item0504);

        listDataChild.put(listDataHeader.get(4), Socialization);

        List<ActivityC4A> Cultural = new ArrayList<ActivityC4A>();

        ActivityC4A item0601 = new ActivityC4A();
        item0601.setId(601);
        item0601.setActivity_name("Visiting culture/entertainment places");
        item0601.setActivity_start_date(new Date());
        item0601.setActivity_end_date(new Date());
        item0601.setCreation_date(new Date());
        item0601.setSince(new Date());
        item0601.setHouse_number(601);
        item0601.setLocation_id(601);
        item0601.setActivity_description("");
        Cultural.add(item0601);

        ActivityC4A item0602 = new ActivityC4A();
        item0602.setId(602);
        item0602.setActivity_name("Watching TV");
        item0602.setActivity_start_date(new Date());
        item0602.setActivity_end_date(new Date());
        item0602.setCreation_date(new Date());
        item0602.setSince(new Date());
        item0602.setHouse_number(602);
        item0602.setLocation_id(602);
        item0602.setActivity_description("");
        Cultural.add(item0602);

        ActivityC4A item0603 = new ActivityC4A();
        item0603.setId(603);
        item0603.setActivity_name("Reading");
        item0603.setActivity_start_date(new Date());
        item0603.setActivity_end_date(new Date());
        item0603.setCreation_date(new Date());
        item0603.setSince(new Date());
        item0603.setHouse_number(603);
        item0603.setLocation_id(603);
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
