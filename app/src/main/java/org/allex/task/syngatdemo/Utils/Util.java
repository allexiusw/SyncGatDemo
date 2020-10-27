package org.allex.task.syngatdemo.Utils;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.os.Build;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import org.allex.task.syngatdemo.R;

public class Util {


   public static void limpiarEditText(EditText[] editTexts){
        for (EditText editText : editTexts){
            editText.getText().clear();
        }
   }

    public static boolean validarCampos(Context context, String[] nombresRequeridos){
        for (String nombre : nombresRequeridos){
            if (nombre.isEmpty() || nombre.trim().length() <1){
                Toast.makeText(context, "El primer nombre y primer apellido son requeridos", Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        return true;
    }

    public static void createNotification(Context context, int notificationId, Class activityClass,
                                          String notificationTitle, String notificationContent, String channelId){
       //TODO: Restringir canal de admin para dispositivos mayores a API 26
        Intent intent = new Intent(context, activityClass);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);

       if(channelId.equals(context.getString(R.string.admin_notification_channel))){
           createAdminNotificationChannel(context);
           createNotificationForHigherApi(context, notificationId, activityClass, notificationTitle,
                   notificationContent, channelId, pendingIntent);
       }else{
           createDefaultNotificationChannel(context);
           createNotificationForHigherApi(context, notificationId, activityClass, notificationTitle,
                   notificationContent, channelId, pendingIntent);
           if(Build.VERSION.SDK_INT < Build.VERSION_CODES.O)
               createNotificationForLowerApi(context, notificationId, activityClass, notificationTitle,
                       notificationContent, pendingIntent);
       }
   }

   private static void createNotificationForHigherApi(Context context, int notificationId, Class activityClass,
                                                      String notificationTitle, String notificationContent,
                                                      String channelId, PendingIntent pendingIntent){
       if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
           NotificationCompat.Builder builder = new NotificationCompat.Builder(
                   context, channelId)
                   .setSmallIcon(R.drawable.ic_launcher_foreground)
                   .setContentTitle(notificationTitle)
                   .setContentText(notificationContent)
                   .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                   .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                   .setAutoCancel(true)
                   .setContentIntent(pendingIntent);
           NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
           notificationManager.notify(notificationId, builder.build());
       }
   }

   private static void createNotificationForLowerApi(Context context, int notificationId, Class activityClass,
                                                     String notificationTitle, String notificationContent, PendingIntent pendingIntent){
       NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
       Notification.Builder builder = new Notification.Builder(context)
               .setSmallIcon(R.drawable.ic_launcher_foreground)
               .setContentTitle(notificationTitle)
               .setContentText(notificationContent)
               .setAutoCancel(true)
               .setContentIntent(pendingIntent);
       notificationManager.notify(notificationId, builder.build());
   }

    private static void createDefaultNotificationChannel(Context context){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            CharSequence name = "default";
            String description = "default_channel";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(context.getString(R.string.default_notification_channel), name, importance);
            channel.setDescription(description);
            channel.setLightColor(Color.GRAY);
            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    private static void createAdminNotificationChannel(Context context){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            CharSequence name = "admin";
            String description = "admin_channel";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel(context.getString(R.string.admin_notification_channel), name, importance);
            channel.setDescription(description);
            channel.setLightColor(Color.GREEN);
            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

}
