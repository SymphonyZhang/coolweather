package com.francis.coolweather.model;

/**
 * Created by Francis on 2015/11/19.
 */
public class WeatherInfo {

    public String cityId;//城市天气编号
    public String cityName;//城市名称
    public String publishTime;//发布时间
    public String weather;//现在天气
    public String tempNow;//现在温度
    public String tempMax;//今天最高温度
    public String tempMin;//今天最低温度
    public String tomorrowDay;//明天白天天气
    public String tomorrowNight;//明天夜间天气
    public String tomorrowTempMax;//明天最高气温
    public String tomorrowTempMin;//明天最低气温
    public String afterTomorrowDay;//后天白天天气
    public String afterTomorrowNight;//后天夜间天气
    public String afterTomorrowTempMax;//后天最高气温
    public String afterTomorrowTempMin;//后天最低气温
    public String updateTime;//判断是否过了18时

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getTempNow() {
        return tempNow;
    }

    public void setTempNow(String tempNow) {
        this.tempNow = tempNow;
    }

    public String getTempMax() {
        return tempMax;
    }

    public void setTempMax(String tempMax) {
        this.tempMax = tempMax;
    }

    public String getTempMin() {
        return tempMin;
    }

    public void setTempMin(String tempMin) {
        this.tempMin = tempMin;
    }

    public String getTomorrowDay() {
        return tomorrowDay;
    }

    public void setTomorrowDay(String tomorrowDay) {
        this.tomorrowDay = tomorrowDay;
    }

    public String getTomorrowNight() {
        return tomorrowNight;
    }

    public void setTomorrowNight(String tomorrowNight) {
        this.tomorrowNight = tomorrowNight;
    }

    public String getTomorrowTempMax() {
        return tomorrowTempMax;
    }

    public void setTomorrowTempMax(String tomorrowTempMax) {
        this.tomorrowTempMax = tomorrowTempMax;
    }

    public String getTomorrowTempMin() {
        return tomorrowTempMin;
    }

    public void setTomorrowTempMin(String tomorrowTempMin) {
        this.tomorrowTempMin = tomorrowTempMin;
    }

    public String getAfterTomorrowDay() {
        return afterTomorrowDay;
    }

    public void setAfterTomorrowDay(String afterTomorrowDay) {
        this.afterTomorrowDay = afterTomorrowDay;
    }

    public String getAfterTomorrowNight() {
        return afterTomorrowNight;
    }

    public void setAfterTomorrowNight(String afterTomorrowNight) {
        this.afterTomorrowNight = afterTomorrowNight;
    }

    public String getAfterTomorrowTempMax() {
        return afterTomorrowTempMax;
    }

    public void setAfterTomorrowTempMax(String afterTomorrowTempMax) {
        this.afterTomorrowTempMax = afterTomorrowTempMax;
    }

    public String getAfterTomorrowTempMin() {
        return afterTomorrowTempMin;
    }

    public void setAfterTomorrowTempMin(String afterTomorrowTempMin) {
        this.afterTomorrowTempMin = afterTomorrowTempMin;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}
