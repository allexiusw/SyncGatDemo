package org.allex.task.syngatdemo.Utils;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import org.allex.task.syngatdemo.R;

public class Util {

    private static final Boolean boolVersionCode = Build.VERSION.SDK_INT >= Build.VERSION_CODES.O;

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

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static void createNotification(Context context, int notificationId, Class activityClass, String notificationTitle, String notificationContent){
        createNotificationChannel(context);
        Intent intent = new Intent(context, activityClass);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);

        if(boolVersionCode){
            NotificationCompat.Builder builder = new NotificationCompat.Builder(
                    context, "local")
                    .setSmallIcon(R.drawable.ic_launcher_foreground)
                    .setContentTitle(notificationTitle)
                    .setContentText(notificationContent)
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    .setAutoCancel(true)
                    .setContentIntent(pendingIntent);
            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
            notificationManager.notify(notificationId, builder.build());
        }else{
            NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            Notification.Builder builder = new Notification.Builder(context)
                    .setSmallIcon(R.drawable.ic_launcher_foreground)
                    .setContentTitle(notificationTitle)
                    .setContentText(notificationContent)
                    .setAutoCancel(true)
                    .setContentIntent(pendingIntent);
            notificationManager.notify(notificationId, builder.build());
        }
   }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private static void createNotificationChannel(Context context){
        if(boolVersionCode){
            CharSequence name = "local";
            String description = "local_channel";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("local", name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

}
