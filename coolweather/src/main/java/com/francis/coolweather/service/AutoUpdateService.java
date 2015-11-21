package com.francis.coolweather.service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.os.SystemClock;
import android.preference.PreferenceManager;

import com.francis.coolweather.interfaces.HttpCallbackListener;
import com.francis.coolweather.receiver.AutoUpdateReceiver;
import com.francis.coolweather.util.HttpUtil;
import com.francis.coolweather.util.KeyUtil;
import com.francis.coolweather.util.Utility;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AutoUpdateService extends Service {
    public AutoUpdateService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                updateWeather();
            }
        }).start();
        AlarmManager manager = (AlarmManager)getSystemService(ALARM_SERVICE);
        int anHour = 8*60*60*1000;//8个小时的毫秒数
        long triggerAtTime = SystemClock.elapsedRealtime()+anHour;
        Intent i = new Intent(this,AutoUpdateReceiver.class);
        PendingIntent pi = PendingIntent.getBroadcast(this,0,i,0);
        manager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,triggerAtTime,pi);
        return super.onStartCommand(intent, flags, startId);
    }

    /**
     * 更新天气信息
     */
    private void updateWeather(){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String weatherCode = prefs.getString("cityId", "");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd", Locale.CHINA);
        String publicKey = "http://open.weather.com.cn/data/?areaid=" + weatherCode + "&type=forecast_v&date=" + sdf.format(new Date()) + "&appid=1efaf3f0c28ec437";
        String privateKey = "a55ee5_SmartWeatherAPI_cd82770";
        String key = KeyUtil.standardURLEncoder(publicKey, privateKey);

        String address = "http://open.weather.com.cn/data/?areaid=" + weatherCode + "&type=forecast_v&date=" + sdf.format(new Date()) + "&appid=1efaf3&key=" + key;
        update(address,false);


        String addressByMi = "http://weatherapi.market.xiaomi.com/wtr-v2/weather?cityId="+weatherCode;
        update(addressByMi,true);
    }

    private void update(final String address,final boolean isToday){
        HttpUtil.sendHttpRequest(address, new HttpCallbackListener() {
            @Override
            public void onFinish(String response) {
                Utility.handlerWeatherResponse(AutoUpdateService.this,response,isToday);
            }

            @Override
            public void onError(Exception e) {
                e.printStackTrace();
            }
        });
    }

}
