package com.city4age.mobile.city4age.Notifications;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by Srle on 5/13/2018.
 */
public class FirebaseTokenManager extends FirebaseInstanceIdService {

    @Override
    public void onTokenRefresh() {
        // Get updated InstanceID token.
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();

        Log.d("FIREBASE", "Refreshed token: " + refreshedToken);

        SharedPreferences prefs = getSharedPreferences("misc", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("firebase_token", refreshedToken);
        editor.apply();
    }
}
