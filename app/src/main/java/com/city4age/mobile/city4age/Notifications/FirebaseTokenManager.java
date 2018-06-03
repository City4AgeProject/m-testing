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
        // e282RhJQ9ec:APA91bHU88p0gWvTYo8bzdCSFWo5HdNEFWrSZT3ydf0gvXfzNdwgiNklnaRRN-fg_6lfEqBttnTDge04sZ3nwhhf5DcbdAHcJnemvoY8YCK6QbszwDVcGekk5PktDArHfAUADBygDNs_

        SharedPreferences prefs = getSharedPreferences("LOCAL_DATA", 0);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("firebase_token", refreshedToken);
        editor.apply();
    }
}
