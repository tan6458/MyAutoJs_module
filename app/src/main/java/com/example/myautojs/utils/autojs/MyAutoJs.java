package com.example.myautojs.utils.autojs;

import android.app.Application;

import com.example.myautojs.App;
import com.example.myautojs.MyActivity;
import com.example.myautojs.R;
import com.example.myautojs.utils.Pref;
import com.stardust.app.GlobalAppContext;
import com.stardust.autojs.AutoJs;
import com.stardust.autojs.engine.ScriptEngine;
import com.stardust.autojs.runtime.ScriptRuntime;
import com.stardust.autojs.runtime.exception.ScriptInterruptedException;
import com.stardust.autojs.script.JavaScriptSource;
import com.stardust.util.Supplier;
import com.stardust.view.accessibility.AccessibilityService;
import com.stardust.view.accessibility.AccessibilityServiceUtils;

public class MyAutoJs extends AutoJs {
    private Application application;

    public MyAutoJs(Application application) {
        super(application);
        this.application = application;
    }

    @Override
    protected ScriptRuntime createRuntime() {
        ScriptRuntime runtime = super.createRuntime();
        runtime.putProperty("class.settings", MyActivity.class);
        runtime.putProperty("class.console", MyActivity.class);
        return runtime;
    }

    @Override
    public void ensureAccessibilityServiceEnabled() {

    }

    @Override
    public void waitForAccessibilityServiceEnabled() {
        if(App.accessibilityService != null) {
            return;
        }
        String errorMessage = null;
        if(AccessibilityServiceUtils.isAccessibilityServiceEnabled(application, AccessibilityService.class)) {
            errorMessage = GlobalAppContext.getString(R.string.text_auto_operate_service_enabled_but_not_running);
        } else {
            if(Pref.shouldEnableAccessibilityServiceByRoot()) {
                if(!AccessibilityServiceTool.enableAccessibilityServiceByRootAndWaitFor(application, 2000)) {
                    errorMessage = GlobalAppContext.getString(R.string.text_enable_accessibility_service_by_root_timeout);
                }
            } else {
                errorMessage = GlobalAppContext.getString(R.string.text_no_accessibility_permission);
            }
        }
        if(errorMessage != null) {
            AccessibilityServiceTool.goToAccessibilitySetting();
            AccessibilityService accessibilityService = new AccessibilityService();
            if(!AccessibilityService.Companion.waitForEnabled(-1)) {
                throw new ScriptInterruptedException();
            }
        }
    }

    @Override
    protected void initScriptEngineManager() {
        super.initScriptEngineManager();
        getScriptEngineManager().registerEngine(JavaScriptSource.ENGINE, new Supplier<ScriptEngine>() {
            @Override
            public ScriptEngine get() {
                XJavaScriptEngine engine = new XJavaScriptEngine(application);
                engine.setRuntime(createRuntime());
                return engine;
            }
        });
    }
}
