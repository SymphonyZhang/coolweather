package com.francis.coolweather.util;

import android.content.Context;

import com.francis.coolweather.R;

/**
 * Created by Francis on 2015/11/19.
 */
public class Common {

    public final static int WEATHER_SUNNY = 0;
    public final static int WEATHER_CLOUDY = 1;
    public final static int WEATHER_OVERCAST = 2;
    public final static int WEATHER_SHOWER = 3;
    public final static int WEATHER_THUNDERSHOWER = 4;
    public final static int WEATHER_THUNDERSHOWER_WITH_HAIL = 5;
    public final static int WEATHER_SLEET = 6;
    public final static int WEATHER_LIGHT_RAIN = 7;
    public final static int WEATHER_MODERATE_RAIN = 8;
    public final static int WEATHER_HEAVY_RAIN = 9;
    public final static int WEATHER_STORM = 10;
    public final static int WEATHER_HEAVY_STORM = 11;
    public final static int WEATHER_SEVERE_STORM = 12;
    public final static int WEATHER_SNOW_FLURRY = 13;
    public final static int WEATHER_LIGHT_SNOW = 14;
    public final static int WEATHER_MODERATE_SNOW = 15;
    public final static int WEATHER_HEAVY_SNOW = 16;
    public final static int WEATHER_SNOWSTORM = 17;
    public final static int WEATHER_FOGGY = 18;
    public final static int WEATHER_ICE_RAIN = 19;
    public final static int WEATHER_DUSTSTORM = 20;
    public final static int WEATHER_LIGHT_TO_MODERATE_RAIN = 21;
    public final static int WEATHER_MODERATE_TO_HEAVY_RAIN = 22;
    public final static int WEATHER_HEAVY_RAIN_TO_STORM = 23;
    public final static int WEATHER_STORM_TO_HEAVY_STORM = 24;
    public final static int WEATHER_HEAVY_TO_SEVERE_STORM = 25;
    public final static int WEATHER_LIGHT_TO_MODERATE_SNOW = 26;
    public final static int WEATHER_MODERATE_TO_HEAVY_SNOW = 27;
    public final static int WEATHER_HEAVY_SNOW_TO_SNOWSTORM = 28;
    public final static int WEATHER_DUST = 29;
    public final static int WEATHER_SAND = 30;
    public final static int WEATHER_SANDSTORM = 31;
    public final static int WEATHER_HAZE = 53;
    public final static int WEATHER_UNKNOWN = 99;

    public static String weatherType(String weather_type,Context context){
        String weather = "";
        switch (Integer.parseInt(weather_type)){
            case WEATHER_SUNNY:
                weather = context.getResources().getString(R.string.sunny);
                break;
            case WEATHER_CLOUDY:
                weather = context.getResources().getString(R.string.cloudy);
                break;
            case WEATHER_OVERCAST:
                weather = context.getResources().getString(R.string.overcast);
                break;
            case WEATHER_SHOWER:
                weather = context.getResources().getString(R.string.shower);
                break;
            case WEATHER_THUNDERSHOWER:
                weather = context.getResources().getString(R.string.thundershower);
                break;
            case WEATHER_THUNDERSHOWER_WITH_HAIL:
                weather = context.getResources().getString(R.string.thundershower_with_hail);
                break;
            case WEATHER_SLEET:
                weather = context.getResources().getString(R.string.sleet);
                break;
            case WEATHER_LIGHT_RAIN:
                weather = context.getResources().getString(R.string.light_rain);
                break;
            case WEATHER_MODERATE_RAIN:
                weather = context.getResources().getString(R.string.moderate_rain);
                break;
            case WEATHER_HEAVY_RAIN:
                weather = context.getResources().getString(R.string.heavy_rain);
                break;
            case WEATHER_STORM:
                weather = context.getResources().getString(R.string.storm);
                break;
            case WEATHER_HEAVY_STORM:
                weather = context.getResources().getString(R.string.heavy_storm);
                break;
            case WEATHER_SEVERE_STORM:
                weather = context.getResources().getString(R.string.severe_storm);
                break;
            case WEATHER_SNOW_FLURRY:
                weather = context.getResources().getString(R.string.snow_flurry);
                break;
            case WEATHER_LIGHT_SNOW:
                weather = context.getResources().getString(R.string.light_snow);
                break;
            case WEATHER_MODERATE_SNOW:
                weather = context.getResources().getString(R.string.moderate_snow);
                break;
            case WEATHER_HEAVY_SNOW:
                weather = context.getResources().getString(R.string.heavy_snow);
                break;
            case WEATHER_SNOWSTORM:
                weather = context.getResources().getString(R.string.snowstorm);
                break;
            case WEATHER_FOGGY:
                weather = context.getResources().getString(R.string.foggy);
                break;
            case WEATHER_ICE_RAIN:
                weather = context.getResources().getString(R.string.ice_rain);
                break;
            case WEATHER_DUSTSTORM:
                weather = context.getResources().getString(R.string.duststorm);
                break;
            case WEATHER_LIGHT_TO_MODERATE_RAIN:
                weather = context.getResources().getString(R.string.light_to_moderate_rain);
                break;
            case WEATHER_MODERATE_TO_HEAVY_RAIN:
                weather = context.getResources().getString(R.string.moderate_to_heavy_rain);
                break;
            case WEATHER_HEAVY_RAIN_TO_STORM:
                weather = context.getResources().getString(R.string.heavy_rain_to_storm);
                break;
            case WEATHER_STORM_TO_HEAVY_STORM:
                weather = context.getResources().getString(R.string.storm_to_heavy_storm);
                break;
            case WEATHER_HEAVY_TO_SEVERE_STORM:
                weather = context.getResources().getString(R.string.heavy_to_severe_storm);
                break;
            case WEATHER_LIGHT_TO_MODERATE_SNOW:
                weather = context.getResources().getString(R.string.light_to_moderate_snow);
                break;
            case WEATHER_MODERATE_TO_HEAVY_SNOW:
                weather = context.getResources().getString(R.string.moderate_to_heavy_snow);
                break;
            case WEATHER_HEAVY_SNOW_TO_SNOWSTORM:
                weather = context.getResources().getString(R.string.heavy_snow_to_snowstorm);
                break;
            case WEATHER_DUST:
                weather = context.getResources().getString(R.string.dust);
                break;
            case WEATHER_SAND:
                weather = context.getResources().getString(R.string.sand);
                break;
            case WEATHER_SANDSTORM:
                weather = context.getResources().getString(R.string.sandstorm);
                break;
            case WEATHER_HAZE:
                weather = context.getResources().getString(R.string.haze);
                break;
            case WEATHER_UNKNOWN:
                weather = context.getResources().getString(R.string.unknown);
                break;
        }
        return  weather;
    }

}
