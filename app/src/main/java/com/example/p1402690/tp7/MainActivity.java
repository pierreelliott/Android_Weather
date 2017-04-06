package com.example.p1402690.tp7;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView lv;
    private TextView tv_cityName;
    private String URL = "http://api.openweathermap.org/data/2.5/forecast?q=Lyon,fr&units=metric&appid=aa198781c56c4f18a0dfcdd709abbec3";

    private MeteoAdapter meteoAdapter;
    private List meteoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv = (ListView) findViewById(R.id.listView);
        tv_cityName = (TextView) findViewById(R.id.city);

        meteoList = new ArrayList<MeteoData>();
        meteoAdapter = new MeteoAdapter(this, meteoList);
        lv.setAdapter(meteoAdapter);

        new MeteoAsyncTask().execute(URL, lv, tv_cityName, meteoAdapter, meteoList);
    }

    public void updateWeather(View view) {
        new MeteoAsyncTask().execute(URL, lv, tv_cityName, meteoAdapter, meteoList);
    }
}
