package com.example.myautojs;

import android.app.Application;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.example.myautojs.base.permission.EasyPermission;
import com.example.myautojs.base.permission.GrantResult;
import com.example.myautojs.base.permission.Permission;
import com.example.myautojs.base.permission.PermissionRequestListener;
import com.example.myautojs.utils.autojs.MyAutoJs;
import com.stardust.app.GlobalAppContext;
import com.stardust.view.accessibility.AccessibilityService;

import java.util.Map;

import static com.example.myautojs.base.permission.GrantResult.GRANT;

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
