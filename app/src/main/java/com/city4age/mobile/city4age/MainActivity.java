package com.city4age.mobile.city4age;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //TODO:Set api for user info retrieval and authorization
    public static String userFullName = "Mary Watson";
    public static int userPassword = 1234;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        TextView userFullName = (TextView) findViewById(R.id.user_full_name);

        //Setting user full name to welcome screen
        userFullName.setText(MainActivity.userFullName);

        EditText enterPIN = (EditText) findViewById(R.id.pin_input);

        enterPIN.setOnEditorActionListener(
                new EditText.OnEditorActionListener() {
                    @Override
                    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                        EditText enterPIN = (EditText) findViewById(R.id.pin_input);
                        int PIN = Integer.parseInt(enterPIN.getText().toString().trim());

                        if (actionId == EditorInfo.IME_ACTION_DONE) {

                            Context context = getApplicationContext();
                            CharSequence textSuccess = "Success!";
                            CharSequence textError = "Wrong PIN!";
                            int duration = Toast.LENGTH_SHORT;

                            if (userPassword == PIN) {
                                Toast toast = Toast.makeText(context, textSuccess, duration);
                                toast.show();

                                goToSelectActivity();

                                return true;
                            } else {
                                Toast toast = Toast.makeText(context, textError, duration);
                                toast.show();
                                return false;
                            }

                        } else {
                            return false;
                        }
                    }
                });
    }

    public void goToSelectActivity() {
        Intent intentToSelect = new Intent(this, SelectActivity.class);
        finish();
        startActivity(intentToSelect);
    }


}
