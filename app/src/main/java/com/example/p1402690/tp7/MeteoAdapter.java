package com.example.p1402690.tp7;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by p1402690 on 17/03/2017.
 */
public class MeteoAdapter extends BaseAdapter implements AdapterView.OnItemClickListener {
    private ArrayList<MeteoData> myList = new ArrayList<MeteoData>();
    private Context context;

    DateFormat formatFR = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM, new Locale("FR"));

    // on passe le context afin d'obtenir un LayoutInflater pour utiliser notre
    // row_layout.xml
    // on passe les valeurs de notre à l'adapter
    public MeteoAdapter(Context context, ArrayList<MeteoData> myList) {
        this.myList = myList;
        this.context = context;
    }

    // retourne le nombre d'objet présent dans notre liste
    @Override
    public int getCount() {
        return myList.size();
    }

    // retourne un élément de notre liste en fonction de sa position
    @Override
    public MeteoData getItem(int position) {
        return myList.get(position);
    }

    // retourne l'id d'un élément de notre liste en fonction de sa position
    @Override
    public long getItemId(int position) {
        return myList.indexOf(getItem(position));
    }

    public void setList(ArrayList<MeteoData> meteos) { myList = meteos; }

    // retourne la vue d'un élément de la liste
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyViewHolder mViewHolder = null;

        // au premier appel ConvertView est null, on inflate notre layout
        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

            convertView = mInflater.inflate(R.layout.meteo_layout, parent, false);

            // nous plaçons dans notre MyViewHolder les vues de notre layout
            mViewHolder = new MyViewHolder();
            mViewHolder.tv_date = (TextView) convertView.findViewById(R.id.date);
            mViewHolder.iv_icon = (ImageView) convertView.findViewById(R.id.weather_icon);
            mViewHolder.tv_temp = (TextView) convertView.findViewById(R.id.temp);
            mViewHolder.tv_temp_min = (TextView) convertView.findViewById(R.id.temp_min);
            mViewHolder.tv_temp_max = (TextView) convertView.findViewById(R.id.temp_max);
            mViewHolder.tv_weather = (TextView) convertView.findViewById(R.id.weather);
            mViewHolder.tv_humidity = (TextView) convertView.findViewById(R.id.humidity);

            // nous attribuons comme tag notre MyViewHolder à convertView
            convertView.setTag(mViewHolder);
        } else {
            // convertView n'est pas null, nous récupérons notre objet MyViewHolder
            // et évitons ainsi de devoir retrouver les vues à chaque appel de getView
            mViewHolder = (MyViewHolder) convertView.getTag();
        }

        // nous récupérons l'item de la liste demandé par getView
        MeteoData meteoData = (MeteoData) getItem(position);

       Resources res = context.getResources();
        // Should error check this, returns 0 on error.
        final int iconId = res.getIdentifier("i"+meteoData.getWeather_icon(), "mipmap", context.getPackageName());

        // nous pouvons attribuer à nos vues les valeurs de l'élément de la liste
        mViewHolder.tv_date.setText(formatFR.format(meteoData.getTime()));
        //mViewHolder.iv_icon.setImageResource(Integer.parseInt(meteoDate.getWeather_icon()));
        mViewHolder.iv_icon.setImageResource(iconId);
        mViewHolder.tv_temp.setText((meteoData.getTemp())+"°C");
        mViewHolder.tv_temp_min.setText("Min : "+(meteoData.getTemp_min())+"°C");
        mViewHolder.tv_temp_max.setText("Max : "+(meteoData.getTemp_max())+"°C");
        mViewHolder.tv_weather.setText("Temps : "+meteoData.getWeather_name());
        mViewHolder.tv_humidity.setText("Humidité : "+meteoData.getHumidity()+"%");

        return convertView;
    }

    // MyViewHolder permet de ne pas devoir rechercher
    // les vues à chaque appel de getView
    private class MyViewHolder {
        TextView tv_date;
        ImageView iv_icon;
        TextView tv_temp;
        TextView tv_temp_min;
        TextView tv_temp_max;
        TextView tv_weather;
        TextView tv_humidity;
    }

    // nous affichons un Toast à chaque clic sur un item de la liste
    // nous récupérons l'objet grâce à sa position
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast toast = Toast.makeText(context, formatFR.format(this.myList.get(position).getTime()) + " : " + this.myList.get(position).getTemp() + "°C", Toast.LENGTH_SHORT);
        toast.show();
    }
}
