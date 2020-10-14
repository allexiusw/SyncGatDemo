package org.allex.task.syngatdemo.Utils;

import android.content.Context;
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
}
