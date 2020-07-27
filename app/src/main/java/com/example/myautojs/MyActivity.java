package com.example.myautojs;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;

import com.stardust.autojs.core.console.ConsoleView;
import com.stardust.autojs.engine.JavaScriptEngine;
import com.stardust.autojs.execution.ExecutionConfig;
import com.stardust.autojs.execution.ScriptExecution;
import com.stardust.autojs.script.JavaScriptFileSource;

import java.io.File;

import androidx.appcompat.app.AppCompatActivity;

public class MyActivity extends AppCompatActivity {
    public static final String Js_autoSkip = "自动跳过1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my2);
        ConsoleView consoleView = findViewById(R.id.view);
        consoleView.setConsole(App.autoJs.getGlobalConsole());
    }


    public void onClick1(View view) {
        runJs(Js_autoSkip);
//        runJs("自动跳过2");
    }

    public void onClick2(View view) {
        stopJs(Js_autoSkip);
    }

    private void runJs(String name) {
        new Thread() {
            @Override
            public void run() {
                try {
                    try {
                        String path = Environment.getExternalStorageDirectory()+"/脚本/"+name+".js";//脚本文件
                        JavaScriptFileSource source = new JavaScriptFileSource(name, new File(path));//脚本别名
                        ExecutionConfig config = new ExecutionConfig();
                        //execute是脚本执行对象
                        ScriptExecution execute = App.autoJs.getScriptEngineService().execute(source, config);
                        log("运行"+name+"成功");
                    } catch(Exception e) {
                        Log.e("tan6458", "e:"+e);

                        App.autoJs.getGlobalConsole().error(e);
                    }
                } catch(Exception e) {
                    e.printStackTrace();
                    Log.e("tan6458", "运行脚本异常:");

                }
            }
        }.start();
    }

    private void log(String str) {
        App.autoJs.getGlobalConsole().info(str);
    }


    private void stopJs(String name) {
        if(name.equals(App.autoJs.getScriptEngineService().getScriptExecutions().iterator().next().getSource().getName())) {
            Log.i("tan6458", "停止脚本:"+name);

            App.autoJs.getScriptEngineService().getScriptExecutions().iterator().next().getEngine().forceStop();
        }
//        App.autoJs.getScriptEngineService().stopAllAndToast();
    }
}
