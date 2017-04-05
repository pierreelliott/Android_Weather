package com.example.p1402690.tp7;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ListView lv;
    private TextView tv_cityName;
    private String URL = "http://api.openweathermap.org/data/2.5/forecast?q=Lyon,fr&units=metric&appid=aa198781c56c4f18a0dfcdd709abbec3";

    private MeteoAdapter meteo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv = (ListView) findViewById(R.id.listView);
        tv_cityName = (TextView) findViewById(R.id.city);

        meteo = new MeteoAdapter(this, null);

        new MeteoAsyncTask().execute(URL, lv, meteo, tv_cityName);
    }

    public void updateWeather(View view) {
        new MeteoAsyncTask().execute(URL, lv, meteo, tv_cityName);
    }
}
