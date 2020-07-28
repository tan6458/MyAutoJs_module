package com.example.myautojs.base.permission;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

/**
 * @className: PermissionUtils
 * @classDescription:
 * @author: Pan_
 * @createTime: 2018/10/25
 */

public  class PermissionUtils {


    //检查无障碍服务
    public static boolean checkAccAccessibilityOpen(Activity context) {
        boolean b = isAccessibilityOpen(context);
        Log.i("tan6458", b ? "当前无障碍服务已打开" : "当前无障碍服务已关闭");
        if(!b) {
            Toast.makeText(context, "请启动无障碍服务 ", Toast.LENGTH_LONG).show();
            context.startActivityForResult(new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS), 0);
        }
        return b;
    }

    private static boolean isAccessibilityOpen(Context context) {
        TextUtils.SimpleStringSplitter mStringColonSplitter = new TextUtils.SimpleStringSplitter(':');
        String curServer = Settings.Secure.getString(context.getApplicationContext().getContentResolver(), Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES);
        if(curServer != null) {
//            Log.i("tan6458", "curServer:"+curServer.replace(":", " | "));
            mStringColonSplitter.setString(curServer);
            while (mStringColonSplitter.hasNext()) {
                String accessibilityService = mStringColonSplitter.next();
                if(accessibilityService.contains("com.tan.app.toolbox/com.tan.app.toolbox.listener.GlobalAccessibilityService")) {
                    return true;
                }
            }
        }
        return false;
    }
    /**
     * 是否是6.0以上版本
     */
    static boolean isOverMarshmallow() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M;
    }

    /**
     * 是否是8.0以上版本
     */
    static boolean isOverOreo() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.O;
    }

    /**
     * 返回应用程序在清单文件中注册的权限
     */
    static List<String> getManifestPermissions(Context context) {
        PackageManager pm = context.getPackageManager();
        try {
            return Arrays.asList(pm.getPackageInfo(context.getPackageName(), PackageManager.GET_PERMISSIONS).requestedPermissions);
        } catch (Exception e) {
            return null;
        }
    }



    /**
     * 检测权限有没有在清单文件中注册
     *
     * @param activity              Activity对象
     * @param requestPermissions    请求的权限组
     */
    static void checkPermissions(Activity activity, List<String> requestPermissions) {
        List<String> manifest = PermissionUtils.getManifestPermissions(activity);
        if (manifest != null && manifest.size() != 0) {
            for (String permission : requestPermissions) {
                if (!manifest.contains(permission)) {
                    throw new RuntimeException("you must add this permission:"+permission+" to AndroidManifest");
                }
            }
        }
    }

    /**
     * 是否有安装权限
     */
    static boolean isHasInstallPermission(Context context) {
        if (isOverOreo()) {
            return context.getPackageManager().canRequestPackageInstalls();
        }
        return true;
    }

    /**
     * 是否有悬浮窗权限
     */
    static boolean isHasOverlaysPermission(Context context) {
        if (isOverMarshmallow()) {
            return Settings.canDrawOverlays(context);
        }
        return true;
    }


}