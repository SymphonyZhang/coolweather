package com.francis.coolweather.activity;

import android.app.Activity;
import android.os.Bundle;

import com.francis.coolweather.util.ActivityCollector;
import com.francis.coolweather.util.LogUtil;

/**
 * Created by Francis on 2015/11/12.
 */
public class BaseActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtil.d("BaseActivity", getClass().getSimpleName());
        ActivityCollector.addActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }
}
