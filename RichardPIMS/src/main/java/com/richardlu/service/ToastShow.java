package com.richardlu.service;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Richard on 14-1-21.
 */
public class ToastShow extends Toast {

    public ToastShow(Context context) {
        super(context);
    }

    public static void ShowLongToast(Context context,String content)
    {
        Toast toast = Toast.makeText(context,content,Toast.LENGTH_LONG);
        toast.show();
    }

    public static void ShowShortToast(Context context,String content)
    {
        Toast toast = Toast.makeText(context,content,Toast.LENGTH_SHORT);
        toast.show();
    }
}
