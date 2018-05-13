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
        // dkh-URnMgYQ:APA91bEnAnm6iWjSb8mcAZTt3uYgjiTmJ3wEfr_Wen87aERR2iqkNP5yYk41rFL8_lToRXnJ2qxSI1pRpkQ7Gg0mOHpBdbmAZuqrOfmHmXgVFB-EnRd3pjqN--97LKA-vqOI7qQwgQlt

        // Samsung
        // eDWtsYJ3RoQ:APA91bE0qTpq5la2iIB9xNZWm_-FiniBVjmP_MBGDYhm7XvOAT5pY3MklNlmTt2k_Pqe3b264kYKltms5prm9p3yAdDfeCKHjQOg5m3O5HWjYTtZaN3Kk6g5AGAWSzShmI9_QrWi3Y3k

        SharedPreferences prefs = getSharedPreferences("LOCAL_DATA", 0);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("firebase_token", refreshedToken);
        editor.apply();
    }
}
