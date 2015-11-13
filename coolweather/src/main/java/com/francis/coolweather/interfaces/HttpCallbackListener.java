package com.francis.coolweather.interfaces;

/**
 * Created by Francis on 2015/11/12.
 */
public interface HttpCallbackListener {
    void onFinish(String response);

    void onError(Exception e);
}
