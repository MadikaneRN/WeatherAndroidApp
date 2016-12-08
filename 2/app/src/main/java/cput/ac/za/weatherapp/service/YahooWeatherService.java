package cput.ac.za.weatherapp.service;

import android.net.Uri;
import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import cput.ac.za.weatherapp.data.Channel;

/**
 * Created by Bonga on 12/7/2016.
 */
public class YahooWeatherService {

    private WeatherServiceCallback callback;
    private String location;
    private Exception error;


    public YahooWeatherService(WeatherServiceCallback callback) {
        this.callback = callback;
    }

    public String getLocation() {
        return location;
    }

    public void refreshWeather(final String locaton)
    {
        new AsyncTask<String,Void,String>()
        {
            @Override
            protected String doInBackground(String... strings) {

                String YQL = String.format("select * from weather.forecast where woeid in (select woeid from geo.places(1) where text=\"%s\")",locaton);

                String endpoint = String.format("https://query.yahooapis.com/v1/public/yql?q=%sformat=json", Uri.encode(YQL));

                try
                {
                    URL url = new URL(endpoint);

                    URLConnection connection = url.openConnection();

                    InputStream inputStream = connection.getInputStream();

                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                    StringBuilder result = new StringBuilder();
                    String line;

                    while((line = reader.readLine()) != null)
                    {
                        result.append(line);
                    }

                    return result.toString();
                }
                catch (Exception e)
                {
                    error = e;

                }

                return null;
            }

            @Override
            protected void onPostExecute(String s) {
                if (s == null && error != null)
                {
                    callback.serviceFailure(error);
                    return;
                }

                try
                {
                    JSONObject data = new JSONObject(s);

                    JSONObject queryResults = data.optJSONObject("query");

                    int count = queryResults.optInt("count");

                    if(count == 0)
                    {
                        callback.serviceFailure(new LocationWeatherException("No Weather info found" + locaton));
                        return;
                    }

                    Channel channel = new Channel();
                    channel.populate(queryResults.optJSONObject("results").optJSONObject("channel"));

                    callback.serviceSuccess(channel);

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }.execute(locaton);
    }


    public class LocationWeatherException extends Exception
    {

        public LocationWeatherException(String detailMessage)
        {
            super(detailMessage);
        }

    }



}
