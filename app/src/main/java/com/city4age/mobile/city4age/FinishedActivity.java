package com.city4age.mobile.city4age;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import com.city4age.mobile.city4age.Adapters.FinishedActivitiesListViewAdapter;
import com.city4age.mobile.city4age.Helpers.JSONToFileHelper;
import com.city4age.mobile.city4age.Model.ActivityData;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by srdjan.milakovic on 08/07/2017.
 */
public class FinishedActivity extends AppCompatActivity {

    public static final String TAG = FinishedActivity.class.getSimpleName();

    private ImageButton mLogoButton;
    private List<ActivityData> mListOfActivities;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finished);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        // logo link to home
                mLogoButton = (ImageButton) findViewById(R.id.my_toolbar_logo);
                mLogoButton.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        Intent intentLoadSelectActivity = new Intent(FinishedActivity.this, SelectActivity.class);
                        startActivity(intentLoadSelectActivity);
            }
        });

        Drawable drawable = ContextCompat.getDrawable(getApplicationContext(),R.drawable.c4a_menu_icon);
        myToolbar.setOverflowIcon(drawable);

        String savedData = JSONToFileHelper.getData(getApplicationContext());

        if (savedData != null && !savedData.isEmpty()) {
            Type listType = new TypeToken<ArrayList<ActivityData>>(){}.getType();
            mListOfActivities = new Gson().fromJson(savedData, listType);
        }
        else{
            mListOfActivities = new ArrayList<>();
        }

        // initialize listview
        ListView lv = (ListView) findViewById(R.id.lv_finished_activities);
        // set the custom adapter to listview
        lv.setAdapter(new FinishedActivitiesListViewAdapter(this,mListOfActivities));
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
