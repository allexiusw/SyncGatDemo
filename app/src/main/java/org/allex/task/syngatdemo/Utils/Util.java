package org.allex.task.syngatdemo.Utils;

import android.widget.EditText;

public class Util {

   public static void limpiarEditText(EditText[] editTexts){
        for (EditText editText : editTexts){
            editText.getText().clear();
        }
    }
}
