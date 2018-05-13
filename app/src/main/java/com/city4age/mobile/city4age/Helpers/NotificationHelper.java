package com.city4age.mobile.city4age.Helpers;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.NotificationCompat;

import com.city4age.mobile.city4age.Notifications.NotificationsActions;
import com.city4age.mobile.city4age.R;
import com.city4age.mobile.city4age.SelectActivity;

/**
 * Created by Srle on 5/13/2018.
 */
public class NotificationHelper {

    private Context mContext;

    public NotificationHelper(Context context) {
        mContext = context;
    }

    public void send(int id, String title, String msg, int type) {
        if (msg == null || msg.isEmpty()) {
            return;
        }

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(mContext);

        mBuilder.setSmallIcon(R.drawable.city4age_logo);
        mBuilder.setContentTitle(title);
        mBuilder.setContentText(msg);

        /*
        Intent resultIntent = new Intent(mContext, SelectActivity.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(mContext);
        stackBuilder.addParentStack(SelectActivity.class);

        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.setContentIntent(resultPendingIntent);
        */

        switch (type) {
            case 1:
                Intent yesBtn = new Intent(mContext, NotificationsActions.class);
                yesBtn.setAction("notification_action");
                yesBtn.putExtra("answer", true);
                yesBtn.putExtra("id", id);
                mBuilder.addAction(0, "Yes", PendingIntent.getBroadcast(mContext, 1, yesBtn, PendingIntent.FLAG_UPDATE_CURRENT));

                Intent noBtn = new Intent(mContext, NotificationsActions.class);
                noBtn.setAction("notification_action");
                noBtn.putExtra("answer", false);
                noBtn.putExtra("id", id);
                mBuilder.addAction(0, "NO", PendingIntent.getBroadcast(mContext, 1, noBtn, PendingIntent.FLAG_UPDATE_CURRENT));
                break;
            case 2:
                break;
            default:
        }

        NotificationManager mNotificationManager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);

        // notificationID allows you to update the notification later on.
        mNotificationManager.notify(id, mBuilder.build());
    }
}
