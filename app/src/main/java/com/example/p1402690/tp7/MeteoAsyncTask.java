package com.example.p1402690.tp7;

/**
 * Created by p1402690 on 17/03/2017.
 */

import android.os.AsyncTask;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by p1402690 on 10/03/2017.
 */
public class MeteoAsyncTask extends AsyncTask<Object, Void, List> {

    private View v;
    private TextView tv_cityName;
    private MeteoAdapter adapter;
    private List listMeteoData;

    @Override
    protected List doInBackground(Object... objects) {
        String urlName = (String) objects[0];
        if(objects[1] instanceof ListView)
        {
            v  = (ListView) objects[1];
        }
        else
        {
            return null;
        }

        tv_cityName = (TextView) objects[2];
        adapter = (MeteoAdapter) objects[3];
        listMeteoData = (ArrayList<MeteoData>) objects[4];
        listMeteoData.clear();

        JSONObject meteoJson;
        URL url = null;
        try {
            url = new URL(urlName);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            if (urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK)
            {
                BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream() )  );

                String meteoData = "";
                String line;
                while((line = in.readLine()) != null)
                {
                    meteoData += line;
                }

                meteoJson = new JSONObject(meteoData);

                MeteoData.setCity(meteoJson.getJSONObject("city").getString("name"));

                JSONArray jsonList = meteoJson.getJSONArray("list");
                for(int i  = 0; i < jsonList.length(); i++)
                {
                    JSONObject jsonObject = jsonList.getJSONObject(i);
                    Date dt = new Date(jsonObject.getLong("dt")*1000);

                    JSONObject main = jsonObject.getJSONObject("main");

                    double temp = main.getDouble("temp");
                    double temp_min = main.getDouble("temp_min");
                    double temp_max = main.getDouble("temp_max");
                    double humidity = main.getDouble("humidity");

                    JSONObject weather = jsonObject.getJSONArray("weather").getJSONObject(0);

                    String weather_main = weather.getString("main");
                    String weather_icon = weather.getString("icon");

                    listMeteoData.add(new MeteoData(dt, temp, temp_min, temp_max, humidity, weather_main, weather_icon));
                }

                in.close();   // et on ferme le flux
            }
        }
        catch (IOException | JSONException e)
        {
            e.printStackTrace();
        }

        return listMeteoData;
    }

    @Override
    public void onPostExecute(List result) {
        if(v == null) {
            System.out.println("Erreur : View is null");
        } else if(v instanceof ListView) {
            tv_cityName.setText(MeteoData.getCity());
            adapter.notifyDataSetChanged();
        }
    }
}
