package com.stardust.autojs.runtime.api;

import com.stardust.autojs.annotation.ScriptInterface;

import androidx.annotation.Nullable;

/**
 * Created by Stardust on 2017/4/2.
 */

public interface Console {

    @ScriptInterface
    void log(@Nullable Object data, Object... options);

    @ScriptInterface
    void logB(@Nullable Object data, Object... options);

    @ScriptInterface
    void print(int level, Object data, Object... options);

    @ScriptInterface
    void success(@Nullable Object data, Object... options);

    @ScriptInterface
    void warn(@Nullable Object data, Object... options);

    @ScriptInterface
    void error(@Nullable Object data, Object... options);

    @ScriptInterface
    void assertTrue(boolean value, @Nullable Object data, Object... options);

    @ScriptInterface
    void clear();

    @ScriptInterface
    void show();

    @ScriptInterface
    void hide();

    String println(int level, CharSequence charSequence);

    void setTitle(CharSequence title);

}
