package com.example.test.mvp.service;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;

/**
 * Created by duchao on 2017/3/11.
 */

public class InitializeService extends IntentService {

    private static final String ACTION_INIT = "initApplication";

    public InitializeService() {
        super("Initialize");
    }

    public static void  startInitial(Context context) {
        Intent intent = new Intent(context, InitializeService.class);
        intent.setAction(ACTION_INIT);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_INIT.equals(action)) {
                initApplication();
            }
        }
    }

    private void initApplication() {

        // 初始化统计参数

        // 初始化错误收集

        // 初始化内存泄漏检测

        // 初始化过度绘制检测

        // 初始化tbs x5 webview
    }




}
