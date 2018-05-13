package com.city4age.mobile.city4age.Notifications;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Srle on 5/13/2018.
 */
public class NotificationsActions extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Boolean answer = intent.getBooleanExtra("answer", false);
        int id = intent.getIntExtra("id", 0);

        // TODO Make API call

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.cancel(id);
    }
}
