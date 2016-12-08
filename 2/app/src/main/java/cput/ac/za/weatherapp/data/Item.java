package cput.ac.za.weatherapp.data;

import org.json.JSONObject;

/**
 * Created by Bonga on 12/7/2016.
 */
public class Item implements JSONPopulator {

    private Condition condition;

    public Condition getCondition() {
        return condition;
    }

    @Override
    public void populate(JSONObject data) {

        condition = new Condition();
        condition.populate(data.optJSONObject("condition"));

    }



}
