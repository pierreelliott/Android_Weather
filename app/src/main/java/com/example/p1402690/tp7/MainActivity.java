package com.example.p1402690.tp7;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
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

        meteoList = (List) getIntent().getSerializableExtra("meteoList");
        tv_cityName.setText(getIntent().getStringExtra("cityName"));
        if(meteoList == null)
        {
            meteoList = new ArrayList<>();
        }
        meteoAdapter = new MeteoAdapter(this, meteoList);
        lv.setAdapter(meteoAdapter);

        //new MeteoAsyncTask().execute(this, URL, tv_cityName, meteoAdapter, meteoList);
    }

    @Override
    protected void onStop() {
        ObjectOutputStream out = null;

        try {
            out = new ObjectOutputStream(new FileOutputStream("meteoDatas.dat"));

            out.writeObject(meteoList);
            out.writeObject(tv_cityName.getText());

            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        super.onStop();
    }

    public void updateWeather(View view) {
        new MeteoAsyncTask().execute(this, URL, tv_cityName, meteoAdapter, meteoList);
    }
}
