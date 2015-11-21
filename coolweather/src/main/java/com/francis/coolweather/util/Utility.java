package com.francis.coolweather.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;

import com.francis.coolweather.R;
import com.francis.coolweather.db.CoolWeatherDB;
import com.francis.coolweather.model.City;
import com.francis.coolweather.model.Country;
import com.francis.coolweather.model.Province;
import com.francis.coolweather.model.WeatherInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Francis on 2015/11/12.
 */
public class Utility {

    private static final String TAG = "Utility";
    private static int count = 0;
    /**
     * 解析和处理服务器返回的省级数据
     */
    public synchronized static boolean handlerProvinceResponse(CoolWeatherDB coolWeatherDB, String response) {
        if (!TextUtils.isEmpty(response)) {
            String[] allProvince = response.split(",");
            if (allProvince != null && allProvince.length > 0) {
                for (String p : allProvince) {
                    String[] array = p.split("\\|");
                    Province province = new Province();
                    province.setProvinceCode(array[0]);
                    province.setProvinceName(array[1]);
                    coolWeatherDB.saveProvince(province);
                }
                return true;
            }
        }
        return false;
    }

    /**
     * 解析和处理服务器返回的市级数据
     */
    public synchronized static boolean handlerCityResponse(CoolWeatherDB coolWeatherDB, String response, int provinceId) {
        if (!TextUtils.isEmpty(response)) {
            String[] allCities = response.split(",");
            if (allCities != null && allCities.length > 0) {
                for (String c : allCities) {
                    String[] array = c.split("\\|");
                    City city = new City();
                    city.setCityCode(array[0]);
                    city.setCityName(array[1]);
                    city.setProvinceId(provinceId);
                    coolWeatherDB.saveCity(city);
                }
                return true;
            }
        }
        return false;
    }


    /**
     * 解析和处理服务器返回的县级数据
     */
    public synchronized static boolean handlerCountryResponse(CoolWeatherDB coolWeatherDB, String response, int cityId) {
        if (!TextUtils.isEmpty(response)) {
            String[] allCounties = response.split(",");
            if (allCounties != null && allCounties.length > 0) {
                for (String c : allCounties) {
                    String[] array = c.split("\\|");
                    Country country = new Country();
                    country.setCountryCode(array[0]);
                    country.setCountryName(array[1]);
                    country.setCityId(cityId);
                    coolWeatherDB.saveCountry(country);
                }
                return true;
            }
        }
        return false;
    }

    /**
     * 解析服务器返回的JSON数据，并将解析出的数据存储到本地
     */
    public static void handlerWeatherResponse(Context context, String response,boolean isToday) {

        WeatherInfo weatherInfo = new WeatherInfo();

        if(isToday){
            try {
                JSONObject jsonObject = new JSONObject(response);
                JSONObject cityInfo = jsonObject.getJSONObject("realtime");
                String temp = cityInfo.getString("temp")+ "℃";
                String weather = cityInfo.getString("weather");
                String publishTime = cityInfo.getString("time");
                JSONObject today = jsonObject.getJSONObject("today");
                String tempMax = today.getString("tempMax")+ "℃";
                String tempMin = today.getString("tempMin")+ "℃";
                weatherInfo.setTempNow(temp);
                weatherInfo.setWeather(weather);
                weatherInfo.setPublishTime(publishTime);
                weatherInfo.setTempMax(tempMax);
                weatherInfo.setTempMin(tempMin);


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }else {
            try {
                JSONObject jsonObject = new JSONObject(response);

                JSONObject cityInfo = jsonObject.getJSONObject("c");
                String cityId = cityInfo.getString("c1");
                String cityName = cityInfo.getString("c3");
                weatherInfo.setCityId(cityId);
                weatherInfo.setCityName(cityName);
                JSONObject otherInfo = jsonObject.getJSONObject("f");
                String updateTime = otherInfo.getString("f0");
                LogUtil.d(TAG,updateTime.substring(8));
                weatherInfo.setUpdateTime(updateTime);
                JSONArray jsonArray = otherInfo.getJSONArray("f1");
                for (int i = 1; i < jsonArray.length(); i++) {
                    JSONObject jsonObjectSon = (JSONObject) jsonArray.opt(i);
                    String day = Common.weatherType(jsonObjectSon.getString("fa"), context);
                    String high = jsonObjectSon.getString("fc") + "℃";
                    String night = Common.weatherType(jsonObjectSon.getString("fb"), context);
                    String low = jsonObjectSon.getString("fd") + "℃";
                    switch (i) {
                        case 1:
                            weatherInfo.setTomorrowDay(day);
                            weatherInfo.setTomorrowNight(night);
                            weatherInfo.setTomorrowTempMax(high);
                            weatherInfo.setTomorrowTempMin(low);
                            break;
                        case 2:
                            weatherInfo.setAfterTomorrowDay(day);
                            weatherInfo.setAfterTomorrowNight(night);
                            weatherInfo.setAfterTomorrowTempMax(high);
                            weatherInfo.setAfterTomorrowTempMin(low);
                            break;
                    }

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        saveWeatherInfo(context,weatherInfo,isToday);
    }

    /**
     * 将服务器返回的所有天启信息存储到SharedPreferences文件中
     */
    public static void saveWeatherInfo(Context context, WeatherInfo weatherInfo,boolean isToday) {
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年M月d日", Locale.CHINA);

        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, 2);
        date = calendar.getTime();
        SimpleDateFormat spd = new SimpleDateFormat("MM/dd");

        editor.putBoolean("city_selected", true);
        editor.putString("afterTomorrowDate", spd.format(date));
        editor.putString("tomorrowDate",context.getResources().getString(R.string.tomorrow));
        if(isToday){
            editor.putString("temp", weatherInfo.getTempNow());
            editor.putString("weather", weatherInfo.getWeather());
            editor.putString("publishTime",weatherInfo.getPublishTime());
            editor.putString("tempMax",weatherInfo.getTempMax());
            editor.putString("tempMin",weatherInfo.getTempMin());
            editor.putString("current_date", sdf.format(new Date()));
        }else {
            editor.putString("cityId",weatherInfo.getCityId());
            editor.putString("cityName",weatherInfo.getCityName());
            editor.putString("tomorrowDay",weatherInfo.getTomorrowDay());
            editor.putString("tomorrowNight",weatherInfo.getTomorrowNight());
            editor.putString("tomorrowTempMax",weatherInfo.getTomorrowTempMax());
            editor.putString("tomorrowTempMin",weatherInfo.getTomorrowTempMin());
            editor.putString("afterTomorrowDay",weatherInfo.getAfterTomorrowDay());
            editor.putString("afterTomorrowNight",weatherInfo.getAfterTomorrowNight());
            editor.putString("afterTomorrowTempMax",weatherInfo.getAfterTomorrowTempMax());
            editor.putString("afterTomorrowTempMin",weatherInfo.getAfterTomorrowTempMin());
            editor.putString("updateTime",weatherInfo.getUpdateTime());
        }

        editor.commit();
    }
}
