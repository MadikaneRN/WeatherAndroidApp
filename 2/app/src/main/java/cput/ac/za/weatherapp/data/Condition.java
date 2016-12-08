package cput.ac.za.weatherapp.data;

import org.json.JSONObject;

/**
 * Created by Bonga on 12/7/2016.
 */
public class Condition implements JSONPopulator{

    private  int code;
    private int temperature;
    private String description;

    @Override
    public void populate(JSONObject data) {

        code = data.optInt("code");
        temperature = data.optInt("temp");
        description = data.optString("text");



    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public int getTemperature() {
        return temperature;
    }
}
