package org.allex.task.syngatdemo.Services;

import android.app.Activity;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import org.allex.task.syngatdemo.Activity_Home;
import org.allex.task.syngatdemo.Utils.Util;

public class MessagingService extends FirebaseMessagingService {

    @Override
    public void onNewToken(@NonNull String s) {
        super.onNewToken(s);
        // Get updated InstanceID token.
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d("firebase", "Refreshed token: " + refreshedToken);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        Log.d("firebase", remoteMessage.getFrom());
        Log.d("firebase", "Message data payload: " + remoteMessage.getData());
        Util.createNotification(this, 679383850, Activity_Home.class,
                remoteMessage.getNotification().getTitle(), remoteMessage.getNotification().getBody(),
                remoteMessage.getNotification().getChannelId());
    }
}
