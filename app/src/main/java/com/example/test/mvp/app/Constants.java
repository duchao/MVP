package com.example.test.mvp.app;

import android.os.Environment;

import java.io.File;

/**
 * Created by duchao on 2017/3/11.
 */

public class Constants {

    //================= PATH ====================

    public static final String PATH_DATA = App.getInstance().getCacheDir().getAbsolutePath() + File.separator + "data";

    public static final String PATH_CACHE = PATH_DATA + "/NetCache";

    public static final String PATH_SDCARD = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "example" + File.separator + "MVP";

}
