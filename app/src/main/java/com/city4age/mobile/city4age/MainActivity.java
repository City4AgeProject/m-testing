package com.city4age.mobile.city4age;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.city4age.mobile.city4age.Helpers.HttpHelper;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;

/**
 * Created by srdjan.milakovic on 08/07/2017.
 */
public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();

    private Button mLoginBtn;
    private UserLoginTask mAuthTask = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Reference XML layout
        setContentView(R.layout.activity_main);

        // Add toolbar
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        // Reference login btn and set onClick listener
        mLoginBtn = (Button) findViewById(R.id.login_button);
        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText usernameView = (EditText) findViewById(R.id.username);
                EditText passwordView = (EditText) findViewById(R.id.password);
                mAuthTask = new UserLoginTask(usernameView.getText().toString().trim(), passwordView.getText().toString().trim());
                mAuthTask.execute((Void) null);
            }
        });
    }

    private class UserLoginTask extends AsyncTask<Void, Void, Boolean> {

        private final String mUserName;
        private final String mPass;

        UserLoginTask(String userName, String pass) {
            mUserName = userName;
            mPass = pass;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            JSONObject result = null;
            Boolean status = false;
            //letizia
            //lventurin1
            try {
                //real server test
                //result = HttpHelper.getJSONFromUrl("http://c4adashboard.atc.gr/C4A-dashboard/rest/users/login/username/" + mEmail + "/password/" + mPass);
                //local Glassfish server test
                //result = HttpHelper.getJSONFromUrl("http://10.0.2.2:8080/C4A-dashboard/rest/users/login/username/" + mEmail + "/password/" + mPass);
                //server Madrid
                result = HttpHelper.getJSONFromUrl("http://138.4.10.230:8000/C4A-dashboard/rest/users/login/username/" + mUserName + "/password/" + mPass);
                //nas server Aca
                //result = HttpHelper.getJSONFromUrl("http://109.111.225.84:8082/C4A-dashboard/rest/users/login/username/" + mEmail + "/password/" + mPass);
                //nas server Aca2
                //result = HttpHelper.getJSONFromUrl("http://109.111.225.84:8080/C4A-dashboard/rest/users/login/username/" + mEmail + "/password/" + mPass);
                /*
                    {
                    "message": "success",
                    "responseCode": 10,
                    "displayName": "Letizia Venturini",
                    "roleId": 8,
                    "pilotName": "Lecce",
                    "pilotCode": "LCC",
                    "ID": 3
                    }
                */
            } catch (JSONException e) {
                return false;
            } catch (IOException e) {
                return false;
            }

            try {
                // status = result.getString("message").equals("success") && result.getString("responseCode").equals("10");
                status = result.getString("responseCode").equals("200");

                if (status) {
                    SharedPreferences prefs = getSharedPreferences("LOCAL_DATA", 0);
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putString("user_name", result.getString("displayName"));
                    //nope on mobile
                    //editor.putString("user_id", result.getString("ID"));
                    editor.putString("user_id", result.getString("uirId"));
                    editor.apply();

                    // TODO send firebase token to server
                    String firebaseToken = prefs.getString("firebase_token", "");
                }
            } catch (JSONException e) {
                return false;
            }

            return status;
        }

        protected void onPostExecute(final Boolean result) {
            Context context = getApplicationContext();
            CharSequence textSuccess = "Success!";
            CharSequence textError = "Wrong username or password";

            if (result) {
                Toast toast = Toast.makeText(context, textSuccess, Toast.LENGTH_SHORT);
                toast.show();

                goToSelectActivity();
            } else {
                Toast toast = Toast.makeText(context, textError, Toast.LENGTH_SHORT);
                toast.show();
            }
        }
    }

    public void goToSelectActivity() {
        Intent intentToSelect = new Intent(this, SelectActivity.class);
        finish();
        startActivity(intentToSelect);
    }
}
