package com.example.p1402690.tp7;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        String URL = "http://api.openweathermap.org/data/2.5/forecast?q=Lyon,fr&units=metric&appid=aa198781c56c4f18a0dfcdd709abbec3";

        ConnectivityManager cm = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();

        List meteoList = new ArrayList<>();
        MeteoAdapter meteoAdapter = new MeteoAdapter(this, meteoList);

        if(isConnected)
        {
            new MeteoAsyncTask().execute(this, URL, null, meteoAdapter, meteoList);
        }
        else
        {
            Toast.makeText(this, "Aucune connexion disponible", Toast.LENGTH_SHORT).show();
            try {
                ObjectInputStream in = new ObjectInputStream(new FileInputStream("meteoDatas.dat"));

                meteoList = (List) in.readObject();
                String cityName = (String) in.readObject();
                Intent i = new Intent(this, MainActivity.class);
                i.putExtra("meteoList", (Serializable) meteoList);
                i.putExtra("cityName", cityName);
                in.close();

                startActivity(i);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}


