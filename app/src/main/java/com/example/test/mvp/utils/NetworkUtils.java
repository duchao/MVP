package com.example.test.mvp.utils;

import android.content.Context;
import android.net.ConnectivityManager;

import com.example.test.mvp.app.App;

/**
 * Created by duchao on 2017/3/12.
 */

public class NetworkUtils {
    public static boolean isNetworkConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) App.getInstance().getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        return connectivityManager.getActiveNetworkInfo() != null;
    }
}
