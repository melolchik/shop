package com.melolchik.common.util;

import android.util.Log;


/**
 * Created by Olga Melekhina on 02.06.2016.
 */
public class AppLogger {
    /**
     * The constant TAG.
     */
    public static String TAG = "LOG";
    public static String TAG_FCM = "LOG";

    public static void init(String tag, String fcm_tag){
        TAG = tag;
        TAG_FCM = fcm_tag;
    }


    /**
     * Log.
     *
     * @param text the text
     */
    public  static void log(String text){
        Log.d(TAG,"" + text);
    }

    public  static void log_fcm(String text){
         Log.d(TAG_FCM,"" + text);
    }

   /*public static void log_instabug(String text){
        if(TextUtils.isEmpty(text)) return;
        if (CarpoApplication.allowIntabugAndHockeyAppUpdate) {
            Instabug.log(text);
        }
    }*/

}
