package com.tan.myAutoJs;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.myautojs.R;
import com.tan.base.permission.EasyPermission;
import com.tan.base.permission.GrantResult;
import com.tan.base.permission.Permission;
import com.tan.base.permission.PermissionRequestListener;
import com.tan.base.permission.PermissionUtils;
import com.stardust.autojs.core.console.ConsoleView;
import com.stardust.autojs.execution.ExecutionConfig;
import com.stardust.autojs.execution.ScriptExecution;
import com.stardust.autojs.script.JavaScriptFileSource;

import java.io.File;
import java.util.Map;

import androidx.appcompat.app.AppCompatActivity;

import static com.tan.base.permission.GrantResult.GRANT;

public class MyActivity extends AppCompatActivity {
    public static final String Js_autoSkip = "自动解锁";
//    public static final String Js_autoSkip = "心跳";
//    public static final String Js_autoSkip = "微信抢红包";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my2);
        ConsoleView consoleView = findViewById(R.id.view);
        consoleView.setConsole(App.autoJs.getGlobalConsole());
        initPermission();
    }

    private void initPermission() {
        //普通权限
        EasyPermission.with(this)
                .addPermissions(Permission.Group.STORAGE)
                .request(new PermissionRequestListener() {
                    @Override
                    public void onGrant(Map<String, GrantResult> result) {
                        Log.i("tan6458", "权限申请情况:"+result.toString());
                        boolean b = true;
                        for(GrantResult item : result.values()) {
                            if(item != GRANT) {
                                b = false;
                                break;
                            }
                        }
                        if(b) {
                            Log.i("tan6458", "已获取全部权限:");
                        } else {
                            Log.i("tan6458", "未获取全部权限:");
                            Toast.makeText(getApplication(),"请赋予全部权限", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        //无障碍权限,监听通知和点击
        PermissionUtils.checkAccAccessibilityOpen(this,"com.tan.myautojs/com.tan.myautojs.GlobalAccessibilityService");
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
                        String path = Environment.getExternalStorageDirectory()+"/脚本/彪奔工具箱/"+name+".js";//脚本文件
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
        App.autoJs.getGlobalConsole().success(str);
    }


    private void stopJs(String name) {
        if(name.equals(App.autoJs.getScriptEngineService().getScriptExecutions().iterator().next().getSource().getName())) {
            Log.i("tan6458", "停止脚本:"+name);

            App.autoJs.getScriptEngineService().getScriptExecutions().iterator().next().getEngine().forceStop();
        }
//        App.autoJs.getScriptEngineService().stopAllAndToast();
    }
}
