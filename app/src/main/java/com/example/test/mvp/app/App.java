package com.example.test.mvp.app;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

import com.example.test.mvp.service.InitializeService;
import com.example.test.mvp.di.component.DaggerIAppComponent;
import com.example.test.mvp.di.component.IAppComponent;
import com.example.test.mvp.di.module.AppModule;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by duchao on 2017/3/11.
 */

public class App extends Application {
    private static App sInstance;
    private Set<Activity> mActivities;
    private static IAppComponent sAppComponent;

    public static int SCREEN_WIDTH = -1;
    public static int SCREEN_HEIGHT = -1;
    public static float DIMEN_RATE = -1.0F;
    public static int DIMEN_DPI = -1;


    public static App getInstance() {
        return sInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = new App();
        // 开始初始化相关的工作
        InitializeService.startInitial(this);

    }

    public void addActivity(Activity act) {
        if (mActivities == null) {
            mActivities = new HashSet<>();
        }
        mActivities.add(act);
    }

    public void removeActivity(Activity act) {
        if (mActivities != null) {
            mActivities.remove(act);
        }
    }

    public void exitApp() {
        if (mActivities != null) {
            synchronized (mActivities) {
                for (Activity act : mActivities) {
                    act.finish();
                }
            }
        }
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }

    public void getScreenSize() {
        WindowManager windowManager = (WindowManager)this.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        Display display = windowManager.getDefaultDisplay();
        display.getMetrics(dm);
        DIMEN_RATE = dm.density / 1.0F;
        DIMEN_DPI = dm.densityDpi;
        SCREEN_WIDTH = dm.widthPixels;
        SCREEN_HEIGHT = dm.heightPixels;
        if(SCREEN_WIDTH > SCREEN_HEIGHT) {
            int t = SCREEN_HEIGHT;
            SCREEN_HEIGHT = SCREEN_WIDTH;
            SCREEN_WIDTH = t;
        }
    }

    public static IAppComponent getAppComponent() {
        if (sAppComponent == null) {
            sAppComponent = DaggerIAppComponent.builder()
                    .appModule(new AppModule(sInstance))
                    .build();
        }
        return sAppComponent;
    }
}
