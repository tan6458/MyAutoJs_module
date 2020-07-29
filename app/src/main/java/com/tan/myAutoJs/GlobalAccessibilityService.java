package com.tan.myAutoJs;

import android.app.KeyguardManager;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.PowerManager;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.widget.Toast;

import com.stardust.view.accessibility.AccessibilityService;

import androidx.annotation.RequiresApi;

/**
 * @author baker
 * @date 2020年7月28日17:02:47
 * @Description: 全局的android无障碍监听
 */
public class GlobalAccessibilityService extends AccessibilityService {
    static final String TAG = "GlobalAccessibilityService";
    private KeyguardManager.KeyguardLock keyguardLock;
    private KeyguardManager keyguardManager;
    private PowerManager.WakeLock wakeLock;
    private Handler handler = new Handler();
    private boolean isFilter = false;//是否符合过滤

    //监听微信消息在脚本实现
    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        super.onAccessibilityEvent(event);
    }


    public Handler getHandler() {
        return handler;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private boolean isKeyguardLocked() {
        keyguardManager = (KeyguardManager) getSystemService(Context.KEYGUARD_SERVICE);
        return keyguardManager.isKeyguardLocked();
    }

    @Override
    public void onInterrupt() {
        super.onInterrupt();
        Log.i("tan6458", "无障碍服务已关闭:");
        Toast.makeText(this, "无障碍服务已关闭", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onServiceConnected() {
        super.onServiceConnected();
        Log.i("tan6458", "无障碍服务已手动打开:");
    }
}