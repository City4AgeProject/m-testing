package com.city4age.mobile.city4age.Notifications;

import android.util.Log;

import com.city4age.mobile.city4age.Helpers.NotificationHelper;
import com.city4age.mobile.city4age.R;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

/**
 * Created by Srle on 5/13/2018.
 */
public class FirebaseMessageManager extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        int type = 0;
        String title = getString(R.string.app_name);
        String msg = "";

        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
        Log.d("FIREBASE", "From: " + remoteMessage.getFrom());

        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            Log.d("FIREBASE", "Message data payload: " + remoteMessage.getData());

            Map<String, String> dataMap = remoteMessage.getData();
            type = Integer.parseInt(dataMap.get("type"));
        }

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            Log.d("FIREBASE", "Message Notification Body: " + remoteMessage.getNotification().getBody());

            title = remoteMessage.getNotification().getTitle();
            msg = remoteMessage.getNotification().getBody();
        }

        NotificationHelper notificationHelper = new NotificationHelper(getApplicationContext());
        notificationHelper.send(1, title, msg, type);
    }
}
