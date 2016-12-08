package cput.ac.za.weatherapp;

import android.app.ProgressDialog;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import cput.ac.za.weatherapp.data.Channel;
import cput.ac.za.weatherapp.service.WeatherServiceCallback;
import cput.ac.za.weatherapp.service.YahooWeatherService;

public class MainActivity extends AppCompatActivity implements WeatherServiceCallback {

    private ImageView weatherIconImageView;
    private TextView temperatureTextView;
    private TextView conditionTextView;
    private TextView locationTextView;

    private YahooWeatherService service;

    private ProgressDialog dialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        weatherIconImageView = (ImageView)findViewById(R.id.imageView);
        temperatureTextView = (TextView)findViewById(R.id.temperatureTextView);
        conditionTextView = (TextView)findViewById(R.id.conditionTextView);
        locationTextView = (TextView)findViewById(R.id.locationTextView);

        service = new YahooWeatherService(this);
        dialog = new ProgressDialog(this);
        dialog.setMessage("Loading...");
        dialog.show();



        service.refreshWeather("Austin TX");


    }


    @Override
    public void serviceSuccess(Channel channel) {

    }

    @Override
    public void serviceFailure(Exception exception) {

        Toast.makeText(this,exception.getMessage(),Toast.LENGTH_LONG).show();
    }
}
