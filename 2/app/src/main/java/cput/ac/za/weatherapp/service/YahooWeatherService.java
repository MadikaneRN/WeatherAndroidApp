package cput.ac.za.weatherapp.service;

import android.os.AsyncTask;

/**
 * Created by Bonga on 12/7/2016.
 */
public class YahooWeatherService {

    private WeatherServiceCallback callback;
    private String location;


    public YahooWeatherService(WeatherServiceCallback callback) {
        this.callback = callback;
    }

    public String getLocation() {
        return location;
    }

    public void refreshWeather(String locaton)
    {
        new AsyncTask<String,Void,String>()
        {
            @Override
            protected String doInBackground(String... strings) {
                return null;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
            }
        }.execute(locaton);
    }

}
