package com.tan.myAutoJs;

import android.app.Application;
import android.util.Log;

import com.stardust.autojs.AutoJs;
import com.tan.myAutoJs.autojs.MyAutoJs;
import com.stardust.app.GlobalAppContext;
import com.stardust.view.accessibility.AccessibilityService;

public class App extends Application {
    public static MyAutoJs autoJs;//全局js
    public static AccessibilityService accessibilityService;

    @Override
    public void onCreate() {
        super.onCreate();
        GlobalAppContext.set(this);
        autoJs = new MyAutoJs(this);
        accessibilityService = new AccessibilityService();
        //全局aj日志监听
        autoJs.setLogCallBack((level, msg) -> {
//            Log.i("tan6458", "收到日志:"+level+" "+msg);
            if(msg.contains("和app通信")) {//在js中"log("和app通信.....")"这一句来和app进行通信
                Log.i("tan6458", "收到js通信:"+msg);
            }
        });
    }
}
