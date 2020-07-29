package com.tan.app.base;

import android.util.Log;

public class Logger {
    public static boolean isDegub = true;

    public Logger() {
    }

    public static void i(String var0, String var1) {
        if(isDegub) {
            Log.i(var0, var1+"");
        }

    }

    public static void e(String var0, String var1) {
        if(isDegub) {
            Log.e(var0, var1+"");
        }

    }

    public static void d(String var0, String var1) {
        if(isDegub) {
            Log.d(var0, var1+"");
        }

    }

    public static void w(String var0, String var1) {
        if(isDegub) {
            Log.w(var0, var1+"");
        }

    }

    public static void v(String var0, String var1) {
        if(isDegub) {
            Log.v(var0, var1+"");
        }

    }

    public static void s(String var0) {
        if(isDegub) {
            System.out.println(var0+"");
        }

    }

    public static void setDebug(boolean var0) {
        isDegub = var0;
    }
}
