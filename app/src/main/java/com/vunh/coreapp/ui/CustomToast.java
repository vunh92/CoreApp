package com.vunh.coreapp.ui;

import android.content.Context;
import android.widget.Toast;

public class CustomToast {
    private static CustomToast customToast;
    private static Toast toast;

    public CustomToast getInstance(Context context, String message) {
        if (customToast == null) {
            customToast = new CustomToast();
        }
        if (toast != null) {
            toast.cancel();
        }
        toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);

        return customToast;
    }

    public CustomToast() {
    }

    public void show() {
        toast.show();
    }
}
