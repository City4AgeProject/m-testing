package com.city4age.mobile.city4age.Notifications;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import com.city4age.mobile.city4age.API.ApiUtils;
import com.city4age.mobile.city4age.API.MTestingService;
import com.city4age.mobile.city4age.API.Model.AnswerRequest;
import com.city4age.mobile.city4age.API.Model.MTestingResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Srle on 5/13/2018.
 */
public class NotificationsActions extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        int answer = intent.getIntExtra("answer", 0);
        int id = intent.getIntExtra("id", 0);
        String user = context.getSharedPreferences("LOCAL_DATA", 0).getString("user_id", "");

        MTestingService mTestingService = ApiUtils.getAPIService();
        mTestingService.sendAnswer(new AnswerRequest(user, id, answer)).enqueue(new Callback<MTestingResponse>() {
            @Override
            public void onResponse(@NonNull Call<MTestingResponse> call, @NonNull Response<MTestingResponse> response) {
                if (response.isSuccessful()) {

                }
            }

            @Override
            public void onFailure(@NonNull Call<MTestingResponse> call, @NonNull Throwable t) {

            }
        });

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.cancel(id);
    }
}
