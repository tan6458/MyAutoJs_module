package com.stardust.view.accessibility;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.provider.Settings.Secure;
import android.text.TextUtils.SimpleStringSplitter;

import org.jetbrains.annotations.NotNull;

import kotlin.jvm.internal.Intrinsics;

public final class AccessibilityServiceUtils {
    public static final AccessibilityServiceUtils INSTANCE;

    public final void goToAccessibilitySetting(@NotNull Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        context.startActivity((new Intent("android.settings.ACCESSIBILITY_SETTINGS")).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
    }

    public static final boolean isAccessibilityServiceEnabled(@NotNull Context context, @NotNull Class accessibilityService) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(accessibilityService, "accessibilityService");
        ComponentName expectedComponentName = new ComponentName(context, accessibilityService);
        String var10000 = Secure.getString(context.getContentResolver(), "enabled_accessibility_services");
        if(var10000 != null) {
            String enabledServicesSetting = var10000;
            SimpleStringSplitter colonSplitter = new SimpleStringSplitter(':');
            colonSplitter.setString(enabledServicesSetting);

            ComponentName enabledService;
            do {
                if(!colonSplitter.hasNext()) {
                    return false;
                }

                String componentNameString = colonSplitter.next();
                enabledService = ComponentName.unflattenFromString(componentNameString);
            } while (enabledService == null || !Intrinsics.areEqual(enabledService, expectedComponentName));

            return true;
        } else {
            return false;
        }
    }

    private AccessibilityServiceUtils() {
    }

    static {
        AccessibilityServiceUtils var0 = new AccessibilityServiceUtils();
        INSTANCE = var0;
    }
}
