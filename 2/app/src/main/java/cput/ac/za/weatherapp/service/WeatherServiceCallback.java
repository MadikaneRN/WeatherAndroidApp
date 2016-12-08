package cput.ac.za.weatherapp.service;

import cput.ac.za.weatherapp.data.Channel;

/**
 * Created by Bonga on 12/7/2016.
 */
public interface WeatherServiceCallback {

    void serviceSuccess(Channel channel);
    void serviceFailure(Exception exception);
}
