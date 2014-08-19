package com.richardlu.service;

import android.content.Context;

/**
 * Created by Richard on 14-1-21.
 */
public class DebugHelper extends ToastShow{

    private static boolean sRect = true;
    public DebugHelper(Context context) {
        super(context);
    }



    public static void DebugLog(Context context,String content)
    {
        if(sRect)
        ToastShow.ShowShortToast(context,content);
        else
        ;
    }
}
