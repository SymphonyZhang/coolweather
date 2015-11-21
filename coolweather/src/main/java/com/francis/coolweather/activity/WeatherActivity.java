package com.francis.coolweather.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.francis.coolweather.R;
import com.francis.coolweather.interfaces.HttpCallbackListener;
import com.francis.coolweather.service.AutoUpdateService;
import com.francis.coolweather.util.HttpUtil;
import com.francis.coolweather.util.KeyUtil;
import com.francis.coolweather.util.Utility;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class WeatherActivity extends BaseActivity implements View.OnClickListener {

    private LinearLayout weatherInfoLayout;
    private LinearLayout tomorrowInfoLayout;
    private LinearLayout afterTomorrowInfoLayout;
    //用于显示城市名
    private TextView cityNameText;
    //用于显示发布时间
    private TextView publishText;
    //用于显示当前日期
    private TextView currentDateText;
    //用于显示当前温度
    private TextView tempNow;
    //用于显示今天当前天气描述信息
    private TextView weatherDespText;
    //用于显示今天最低气温
    private TextView tempMin;
    //用于显示今天最高气温
    private TextView tempMax;

    //用于显示明天的日期
    private TextView tomorrowDate;
    //用于显示明天最低气温
    private TextView tomorrowTempMin;
    //用于显示明天最高气温
    private TextView tomorrowTempMax;
    //用于显示明天的天气    18时前显示明天白天的天气 18时后显示明天夜间天气
    private TextView tomorrow_Weather;

    //用于显示后天的日期
    private TextView afterTomorrowDate;
    //用于显示后天最低气温
    private TextView afterTomorrowTempMin;
    //用于显示后天最高气温
    private TextView afterTomorrowTempMax;
    //用于显示后天的天气    18时前显示后天白天的天气 18时后显示后天夜间天气
    private TextView afterTomorrow_Weather;

    //切换城市按钮
    private Button switchCity;
    //更新天气按钮
    private Button refreshWeather;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.weather_layout);
        //初始化控件
        init();

        String countryCode = getIntent().getStringExtra("country_code");
        if (!TextUtils.isEmpty(countryCode)) {
            //有县级代号时就去查询天气
            publishText.setText("同步中...");
            weatherInfoLayout.setVisibility(View.INVISIBLE);
            tomorrowInfoLayout.setVisibility(View.INVISIBLE);
            afterTomorrowInfoLayout.setVisibility(View.INVISIBLE);
            cityNameText.setVisibility(View.INVISIBLE);
            queryWeatherCode(countryCode);


        } else {
            //没有县级代号时就直接显示本地天气
            showWeather();
        }
    }

    /**
     * 查询县级地啊哈哦所对应的天气代号
     */
    private void queryWeatherCode(String countryCode) {
        String address = "http://www.weather.com.cn/data/list3/city" + countryCode + ".xml";
        queryFromServer(address, "countryCode", true);
    }

    /**
     * 查询天气代号所对应的天气信息
     */
    private void queryWeatherInfo(String weatherCode) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd", Locale.CHINA);
        String publicKey = "http://open.weather.com.cn/data/?areaid=" + weatherCode + "&type=forecast_v&date=" + sdf.format(new Date()) + "&appid=1efaf3f0c28ec437";
        String privateKey = "a55ee5_SmartWeatherAPI_cd82770";
        String key = KeyUtil.standardURLEncoder(publicKey, privateKey);

        String address = "http://open.weather.com.cn/data/?areaid=" + weatherCode + "&type=forecast_v&date=" + sdf.format(new Date()) + "&appid=1efaf3&key=" + key;
        queryFromServer(address, "weatherCode", false);
        //LogUtil.d("WeatherActivity",address);
        String addressByMi = "http://weatherapi.market.xiaomi.com/wtr-v2/weather?cityId=" + weatherCode;
        queryFromServer(addressByMi, "weatherCode", true);
    }

    /**
     * 根据传入的地址和类型去向服务器查询天气代号或者天气信息
     */
    private void queryFromServer(final String address, final String type, final boolean isToday) {
        HttpUtil.sendHttpRequest(address, new HttpCallbackListener() {
            @Override
            public void onFinish(String response) {
                if ("countryCode".equals(type)) {
                    if (!TextUtils.isEmpty(response)) {
                        //从服务器返回的数据中解析出天气代号
                        String[] array = response.split("\\|");
                        if (array != null && array.length == 2) {
                            String weatherCode = array[1];
                            queryWeatherInfo(weatherCode);
                        }
                    }
                } else if ("weatherCode".equals(type)) {
                    //处理服务器返回的天气信息
                    Utility.handlerWeatherResponse(WeatherActivity.this, response, isToday);
                    if (isToday) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                showWeather();
                            }
                        });
                    }
                }
            }

            @Override
            public void onError(Exception e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        publishText.setText("同步失败");
                    }
                });
            }
        });
    }

    /**
     * 从SharePreferences文件中读取存储的天气信息，并显示到界面上
     */
    private void showWeather() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        cityNameText.setText(prefs.getString("cityName", ""));
        tempNow.setText(prefs.getString("temp", ""));
        tempMin.setText(prefs.getString("tempMin", ""));
        tempMax.setText(prefs.getString("tempMax", ""));
        weatherDespText.setText(prefs.getString("weather", ""));
        publishText.setText("今天" + prefs.getString("publishTime", "") + "发布");
        currentDateText.setText(prefs.getString("current_date", ""));

        tomorrowDate.setText(prefs.getString("tomorrowDate", ""));
        tomorrowTempMin.setText(prefs.getString("tomorrowTempMin", ""));
        tomorrowTempMax.setText(prefs.getString("tomorrowTempMax", ""));
        tomorrow_Weather.setText(prefs.getString("tomorrowDay", ""));


        afterTomorrowDate.setText(prefs.getString("afterTomorrowDate", ""));
        afterTomorrowTempMin.setText(prefs.getString("afterTomorrowTempMin", ""));
        afterTomorrowTempMax.setText(prefs.getString("afterTomorrowTempMax", ""));
        afterTomorrow_Weather.setText(prefs.getString("afterTomorrowDay", ""));

        /*if("1800".equals(prefs.getString("updateTime",""))){
            tomorrow_Weather.setText(prefs.getString("tomorrowNight", ""));
            afterTomorrow_Weather.setText(prefs.getString("afterTomorrowNight",""));
        }else {
        tomorrow_Weather.setText(prefs.getString("tomorrowDay", ""));
        afterTomorrow_Weather.setText(prefs.getString("afterTomorrowDay", ""));
        }*/

        weatherInfoLayout.setVisibility(View.VISIBLE);
        tomorrowInfoLayout.setVisibility(View.VISIBLE);
        afterTomorrowInfoLayout.setVisibility(View.VISIBLE);
        cityNameText.setVisibility(View.VISIBLE);
    }

    private void init() {
        Intent intent = new Intent(this, AutoUpdateService.class);
        startService(intent);
        weatherInfoLayout = (LinearLayout) findViewById(R.id.weather_info_layout);
        tomorrowInfoLayout = (LinearLayout) findViewById(R.id.tomorrowInfoLayout);
        afterTomorrowInfoLayout = (LinearLayout) findViewById(R.id.afterTomorrowInfoLayout);

        cityNameText = (TextView) findViewById(R.id.city_name);
        publishText = (TextView) findViewById(R.id.publish_text);
        currentDateText = (TextView) findViewById(R.id.current_date);
        weatherDespText = (TextView) findViewById(R.id.weather_desp);
        tempNow = (TextView) findViewById(R.id.tempNow);
        tempMin = (TextView) findViewById(R.id.tempMin);
        tempMax = (TextView) findViewById(R.id.tempMax);

        tomorrowDate = (TextView) findViewById(R.id.tomorrowDate);
        tomorrowTempMin = (TextView) findViewById(R.id.tomorrow_tempMin);
        tomorrowTempMax = (TextView) findViewById(R.id.tomorrow_tempMax);
        tomorrow_Weather = (TextView) findViewById(R.id.tomorrow_weather);

        afterTomorrowDate = (TextView) findViewById(R.id.after_tomorrowDate);
        afterTomorrowTempMin = (TextView) findViewById(R.id.after_tomorrow_tempMin);
        afterTomorrowTempMax = (TextView) findViewById(R.id.after_tomorrow_tempMax);
        afterTomorrow_Weather = (TextView) findViewById(R.id.after_tomorrow_weather);

        switchCity = (Button) findViewById(R.id.switch_city);
        refreshWeather = (Button) findViewById(R.id.refresh_weather);
        switchCity.setOnClickListener(this);
        refreshWeather.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.switch_city:
                Intent intent = new Intent(WeatherActivity.this, ChooseAreaActivity.class);
                intent.putExtra("from_weather_activity", true);
                startActivity(intent);
                finish();
                break;
            case R.id.refresh_weather:
                publishText.setText("同步中...");
                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
                String weatherCode = prefs.getString("cityId", "");
                if (!TextUtils.isEmpty(weatherCode)) {
                    queryWeatherInfo(weatherCode);
                }
                break;
            default:
                break;
        }
    }
}
