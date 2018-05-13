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
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import com.city4age.mobile.city4age.Adapters.*;
import com.city4age.mobile.city4age.Model.*;

/**
 * Created by srdjan.milakovic on 08/07/2017.
 */
public class ListActivity extends AppCompatActivity {

    public static final String TAG = ListActivity.class.getSimpleName();

    private ImageButton mLogoButton;
    private ExpandableListAdapter mListAdapter;
    private ExpandableListView mExpListView;
    
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

        // logo link to home
        mLogoButton = (ImageButton) findViewById(R.id.my_toolbar_logo);
        mLogoButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intentLoadSelectActivity = new Intent(ListActivity.this, SelectActivity.class);
                startActivity(intentLoadSelectActivity);
            }
        });

        // Get the list view
        mExpListView = (ExpandableListView) findViewById(R.id.activity_exp_list);

        // Preparing list data
        ActivityTypes types = new ActivityTypes();

        // Setting list adapter
        mListAdapter = new CustomExpandableListAdapter(this, types.getListDataHeader(), types.getListDataChild());
        mExpListView.setAdapter(mListAdapter);
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
