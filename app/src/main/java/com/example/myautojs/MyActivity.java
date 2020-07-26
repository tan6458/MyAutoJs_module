package com.example.myautojs;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;

import com.stardust.autojs.core.console.ConsoleView;
import com.stardust.autojs.execution.ExecutionConfig;
import com.stardust.autojs.execution.ScriptExecution;
import com.stardust.autojs.script.JavaScriptFileSource;

import java.io.File;

import androidx.appcompat.app.AppCompatActivity;

public class MyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my2);
        ConsoleView consoleView = findViewById(R.id.view);
        consoleView.setConsole(App.autoJs.getGlobalConsole());
    }


    public void onClick1(View view) {
        runJs("hello");
    }

    public void onClick2(View view) {
        stopJs();
    }

    private void runJs(String name) {
        new Thread() {
            @Override
            public void run() {
                try {
                    try {
                        String path = Environment.getExternalStorageDirectory()+"/脚本/"+name+".js";//脚本文件
                        JavaScriptFileSource source = new JavaScriptFileSource("我的", new File(path));//脚本别名
                        ExecutionConfig config = new ExecutionConfig();
                        ScriptExecution mScriptExecution = App.autoJs.getScriptEngineService().execute(source, config);
                        Log.i("tan6458", "mScriptExecution:"+mScriptExecution);

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


    private void stopJs() {
        App.autoJs.getScriptEngineService().stopAllAndToast();
    }
}
