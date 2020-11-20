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
import org.allex.task.syngatdemo.R;
import org.allex.task.syngatdemo.Utils.Util;

public class MessagingService extends FirebaseMessagingService {

    @Override
    public void onNewToken(@NonNull String s) {
        super.onNewToken(s);
        // Get updated InstanceID token.
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d("firebase", "Refreshed token: " + refreshedToken);
    }

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        Log.i("firebase", remoteMessage.getFrom());
        Log.i("firebase", "Message data payload: " + remoteMessage.getData());
        String channelId = remoteMessage.getNotification().getChannelId() == null ? this.getString(R.string.default_notification_channel) :
                remoteMessage.getNotification().getChannelId();
        Log.i("firebase", channelId);
        Util.createNotification(this, 679383850, Activity_Home.class,
                remoteMessage.getNotification().getTitle(), remoteMessage.getNotification().getBody(),
                channelId);

    }
}
