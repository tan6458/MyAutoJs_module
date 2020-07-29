package com.example.myautojs.myAutoJs;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.stardust.app.GlobalAppContext;

import org.jetbrains.annotations.NotNull;

import kotlin.jvm.internal.Intrinsics;

/**
 * Created by Stardust on 2017/12/8.
 */

public final class Pref {
    private static final String KEY_FIRST_USING = "key_first_using";
    private static SharedPreferences sPreferences;
    public static final Pref INSTANCE;

    @NotNull
    public static final SharedPreferences getPreferences() {
        SharedPreferences var10000 = sPreferences;
        if(var10000 == null) {
            var10000 = PreferenceManager.getDefaultSharedPreferences(GlobalAppContext.get());
        }

        return var10000;
    }

    public static final boolean isFirstUsing() {
        boolean firstUsing = getPreferences().getBoolean("key_first_using", true);
        if(firstUsing) {
            getPreferences().edit().putBoolean("key_first_using", false).apply();
        }

        return firstUsing;
    }

    private static final String getString(int res) {
        String var10000 = GlobalAppContext.getString(res);
        Intrinsics.checkExpressionValueIsNotNull(var10000, "GlobalAppContext.getString(res)");
        return var10000;
    }

    public static final boolean shouldEnableAccessibilityServiceByRoot() {
        return getPreferences().getBoolean(getString(-1900208), false);
    }

    public static final boolean shouldHideLogs() {
        return getPreferences().getBoolean(getString(-1900036), false);
    }

    public static final boolean shouldStopAllScriptsWhenVolumeUp() {
        return getPreferences().getBoolean(getString(-1900124), true);
    }

    private Pref() {
    }

    static {
        Pref var0 = new Pref();
        INSTANCE = var0;
    }

    // $FF: synthetic method
    public static final SharedPreferences access$getSPreferences$p(Pref $this) {
        return sPreferences;
    }

    // $FF: synthetic method
    public static final void access$setSPreferences$p(Pref $this, SharedPreferences var1) {
        sPreferences = var1;
    }
}
