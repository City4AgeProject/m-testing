package com.city4age.mobile.city4age.Notifications;

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
        // Honor
        // dkh-URnMgYQ:APA91bHXJY0dxdfaKwT8jfJrH0NuaO8I0fFi72N5I-6RT7Ne217jIeSWtG7eGq0QFOQv95hd4hXPiYt1KMMn0TP6NCqqiNfZQcunQfB6xH4yuJ5ULMLjipUgbZqRpYEKmrVO2Hx58BbE

        // Samsung
        //ecnPOHES8Mc:APA91bHZ7hSY1UFuukv8PGv3mQAGkoqqvEsSnt31YDzffoKIabjUV8ZVjOrUtUIhhWPzmYQ_HRrjDTqe-nc6pr3RG7HxdIC9CIaJZvgHd_vFhs9n1PNC3cmn3wLd25C54eYmt-XKdec3

        SharedPreferences prefs = getSharedPreferences("LOCAL_DATA", 0);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("firebase_token", refreshedToken);
        editor.apply();
    }
}
