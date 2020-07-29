package com.tan.myAutoJs;

import android.app.Application;

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
        accessibilityService = new AccessibilityService();
        autoJs = new MyAutoJs(this);

    }
}
