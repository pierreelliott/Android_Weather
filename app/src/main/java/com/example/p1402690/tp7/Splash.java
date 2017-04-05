package com.example.p1402690.tp7;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

public class Splash extends AppCompatActivity {

    // Splash screen timer
    private static int SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        String URL = "http://api.openweathermap.org/data/2.5/forecast?q=Lyon,fr&units=metric&appid=aa198781c56c4f18a0dfcdd709abbec3";
        ListView lv = (ListView) findViewById(R.id.listView);
        TextView tv_cityName = (TextView) findViewById(R.id.city);

        MeteoAdapter meteo = new MeteoAdapter(this, null);

        new MeteoAsyncTask().execute(URL, lv, meteo, tv_cityName);

        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                Intent i = new Intent(Splash.this, MainActivity.class);
                startActivity(i);

                // close this activity
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}


