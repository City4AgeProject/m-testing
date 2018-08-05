package com.city4age.mobile.city4age.Helpers;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.RemoteViews;
import com.city4age.mobile.city4age.Notifications.NotificationsActions;
import com.city4age.mobile.city4age.R;
import java.util.List;

/**
 * Created by Srle on 5/13/2018.
 */
public class NotificationHelper {

    private Context mContext;

    public NotificationHelper(Context context) {
        mContext = context;
    }

    public void send(int id, String title, String msg, List<String> questions) {
        if (msg == null || msg.isEmpty()) {
            return;
        }

        // Create remote view
        RemoteViews remoteViews = new RemoteViews(mContext.getPackageName(), R.layout.notifications_buttons);
        remoteViews.setTextViewText(R.id.text_message, msg);

        if (questions.size() > 0) {
            Intent btn1 = new Intent(mContext, NotificationsActions.class);
            btn1.setAction("notification_action");
            btn1.putExtra("answer", 1);
            btn1.putExtra("id", id);
            remoteViews.setTextViewText(R.id.button1, questions.get(0));
            remoteViews.setInt(R.id.button1, "setVisibility", View.VISIBLE);
            remoteViews.setOnClickPendingIntent(
                    R.id.button1,
                    PendingIntent.getBroadcast(mContext, 1, btn1, PendingIntent.FLAG_UPDATE_CURRENT)
            );
        }

        if (questions.size() > 1) {
            Intent btn1 = new Intent(mContext, NotificationsActions.class);
            btn1.setAction("notification_action");
            btn1.putExtra("answer", 2);
            btn1.putExtra("id", id);
            remoteViews.setTextViewText(R.id.button2, questions.get(1));
            remoteViews.setInt(R.id.button2, "setVisibility", View.VISIBLE);
            remoteViews.setOnClickPendingIntent(
                    R.id.button2,
                    PendingIntent.getBroadcast(mContext, 2, btn1, PendingIntent.FLAG_UPDATE_CURRENT)
            );
        }

        if (questions.size() > 2) {
            Intent btn1 = new Intent(mContext, NotificationsActions.class);
            btn1.setAction("notification_action");
            btn1.putExtra("answer", 3);
            btn1.putExtra("id", id);
            remoteViews.setTextViewText(R.id.button3, questions.get(2));
            remoteViews.setInt(R.id.button3, "setVisibility", View.VISIBLE);
            remoteViews.setOnClickPendingIntent(
                    R.id.button3,
                    PendingIntent.getBroadcast(mContext, 3, btn1, PendingIntent.FLAG_UPDATE_CURRENT)
            );
        }

        if (questions.size() > 3) {
            Intent btn1 = new Intent(mContext, NotificationsActions.class);
            btn1.setAction("notification_action");
            btn1.putExtra("answer", 4);
            btn1.putExtra("id", id);
            remoteViews.setTextViewText(R.id.button4, questions.get(3));
            remoteViews.setInt(R.id.button4, "setVisibility", View.VISIBLE);
            remoteViews.setOnClickPendingIntent(
                    R.id.button4,
                    PendingIntent.getBroadcast(mContext, 4, btn1, PendingIntent.FLAG_UPDATE_CURRENT)
            );
        }

        // Build notification
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(mContext);

        mBuilder.setPriority(Notification.PRIORITY_MAX);
        mBuilder.setWhen(0);
        mBuilder.setSmallIcon(R.drawable.city4age_logo);
        mBuilder.setContentTitle(title);
        mBuilder.setContentText(msg);
        mBuilder.setCustomBigContentView(remoteViews);

        /*
        Intent resultIntent = new Intent(mContext, SelectActivity.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(mContext);
        stackBuilder.addParentStack(SelectActivity.class);

        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.setContentIntent(resultPendingIntent);
        */

        NotificationManager mNotificationManager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);

        // notificationID allows you to update the notification later on.
        mNotificationManager.notify(id, mBuilder.build());
    }
}
