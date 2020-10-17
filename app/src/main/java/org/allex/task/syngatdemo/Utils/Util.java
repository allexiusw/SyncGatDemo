package org.allex.task.syngatdemo.Utils;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.widget.EditText;
import android.widget.Toast;

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

    public void createNotificationChannel(Context context){
       if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
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
