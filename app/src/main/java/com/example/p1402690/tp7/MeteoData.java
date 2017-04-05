package com.example.p1402690.tp7;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by p1402690 on 17/03/2017.
 */
public class MeteoData {

    private Date time;

    private double temp;
    private double temp_min;
    private double temp_max;
    private double humidity;

    private String weather_name;
    private String weather_icon;


    public MeteoData(Date time, double temp, double temp_min, double temp_max, double humidity, String weather_name, String weather_icon) {
        this.weather_icon = weather_icon;
        this.time = time;
        this.temp = temp;
        this.temp_min = temp_min;
        this.temp_max = temp_max;
        this.humidity = humidity;
        this.weather_name = weather_name;
    }

    public Date getTime() {
        return time;
    }

    public double getTemp() {
        return temp;
    }

    public double getTemp_min() {
        return temp_min;
    }

    public double getTemp_max() {
        return temp_max;
    }

    public double getHumidity() {
        return humidity;
    }

    public String getWeather_name() {
        return weather_name;
    }

    public String getWeather_icon() {
        return weather_icon;
    }
}
