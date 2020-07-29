package com.example.myautojs.myAutoJs.autojs;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

import com.stardust.app.GlobalAppContext;
import com.stardust.autojs.core.util.ProcessShell;
import com.stardust.view.accessibility.AccessibilityService;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Locale;

import kotlin.jvm.internal.Intrinsics;

public final class AccessibilityServiceTool {
    private static String cmd = "enabled=$(settings get secure enabled_accessibility_services)\npkg=%s\nif [[ $enabled == *$pkg* ]]\nthen\necho already_enabled\nelse\nenabled=$pkg:$enabled\nsettings put secure enabled_accessibility_services $enabled\nfi";
    public static final AccessibilityServiceTool INSTANCE;

    public static final boolean enableAccessibilityServiceByRoot(@NotNull Context context, @NotNull Class accessibilityService) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(accessibilityService, "accessibilityService");
        String serviceName = context.getPackageName()+"/"+accessibilityService.getName();

        boolean var4;
        try {
            Locale var10000 = Locale.getDefault();
            Intrinsics.checkExpressionValueIsNotNull(var10000, "Locale.getDefault()");
            Locale var5 = var10000;
            String var6 = cmd;
            Object[] var7 = new Object[]{serviceName};
            String var10 = String.format(var5, var6, Arrays.copyOf(var7, var7.length));
            Intrinsics.checkExpressionValueIsNotNull(var10, "java.lang.String.format(locale, format, *args)");
            var4 = TextUtils.isEmpty((CharSequence) ProcessShell.execCommand(var10, true).error);
        } catch(Exception var8) {
            var4 = false;
        }

        return var4;
    }

    public static final boolean enableAccessibilityServiceByRootAndWaitFor(@NotNull Context context, long timeOut) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        if(enableAccessibilityServiceByRoot(context, com.stardust.view.accessibility.AccessibilityService.class)) {
            AccessibilityService.Companion.waitForEnabled(timeOut);
            return true;
        } else {
            return false;
        }
    }

    public final static void goToAccessibilitySetting() {
        GlobalAppContext.get().startActivity((new Intent("android.settings.ACCESSIBILITY_SETTINGS")).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
    }

    private AccessibilityServiceTool() {
    }

    static {
        AccessibilityServiceTool var0 = new AccessibilityServiceTool();
        INSTANCE = var0;
        cmd = "enabled=$(settings get secure enabled_accessibility_services)\npkg=%s\nif [[ $enabled == *$pkg* ]]\nthen\necho already_enabled\nelse\nenabled=$pkg:$enabled\nsettings put secure enabled_accessibility_services $enabled\nfi";
    }
}
