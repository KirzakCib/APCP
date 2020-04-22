package com.example.apcp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

public class MainActivity extends AppCompatActivity implements Callback<Parser> {
    ImageView galerey;
    TextView text;
    TextView nameCoin;
    TextView nominalCoin;
    TextView maniCoin;
    TextView mani24h;
    TextView mani7d;
    String[] mani_1 = new String[4];
    String[] mani_2 = new String[4];
    String[] idCoin = {"R01035","R01235","R01239","R01375",""};

    static final String BASE_URL = "http://www.cbr.ru/scripts/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_coin);

        Date currentDate = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        String dateText = dateFormat.format(currentDate);
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            calendar.setTime(sdf.parse(dateText));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(SimpleXmlConverterFactory.create()).build();

        ParserXML parserXML = retrofit.create(ParserXML.class);

        calendar.add(Calendar.DAY_OF_MONTH, -1 );

        Call<Parser> call_1 = parserXML.loadParser(sdf.format(calendar.getTime()));

        calendar.add(Calendar.WEEK_OF_MONTH, -1 );

        Call<Parser> call_2 = parserXML.loadParser(sdf.format(calendar.getTime()));

        try {
            makeGetRequest(call_1);
            Thread.sleep(1000);
            makeGet(call_2);
            Thread.sleep(1000);
        }catch (Exception e){ }

        Call<Parser> call = parserXML.loadParser(dateText);
        call.enqueue(this);

    }

    public void makeGetRequest(Call<Parser> call_1) {
        
        call_1.enqueue(new Callback<Parser>() {
            @SuppressLint("StaticFieldLeak")

            @Override
            public void onResponse(Call<Parser> call_1, Response<Parser> response) {
                if (response.isSuccessful()) {

            Parser rss = response.body();

                int k = 0;
            for (ValuteCBR valuteCBR : rss.getValuteCBRList()) {
                if (valuteCBR.getId().equals(idCoin[k])) {

                        mani_1[k] = valuteCBR.getValue();
                      k++;
                        }
                    }
                }
            }
            
            @Override
            public void onFailure(Call<Parser> call_1, Throwable t) { }
        });

    }

    public void makeGet(Call<Parser> call_2) {
        
        call_2.enqueue(new Callback<Parser>() {
            @SuppressLint("StaticFieldLeak")

            @Override
            public void onResponse(Call<Parser> call_2, Response<Parser> response) {
                if (response.isSuccessful()) {

                    Parser rss = response.body();

                    int k = 0;
                    for (ValuteCBR valuteCBR : rss.getValuteCBRList()) {
                        if (valuteCBR.getId().equals(idCoin[k])) {

                            mani_2[k] = valuteCBR.getValue();
                            k++;
                        }
                    }
                }
            }


            @Override
            public void onFailure(Call<Parser> call_2, Throwable t) { }
        });

    }

            @Override
            public void onResponse(Call<Parser> call, Response<Parser> response) {
                        if (response.isSuccessful()) {

            Parser rss = response.body();
            LinearLayout linLayout = (LinearLayout) findViewById(R.id.linLayout);
            LayoutInflater ltInflater = getLayoutInflater();

            int k = 0;
            for (ValuteCBR valuteCBR : rss.getValuteCBRList()) {
                if (valuteCBR.getId().equals(idCoin[k])) {

                    View item = ltInflater.inflate(R.layout.activity_main, linLayout, false);
                    galerey = item.findViewById(R.id.image);
                    nameCoin = item.findViewById(R.id.name);
                    nominalCoin = item.findViewById(R.id.nominal);
                    maniCoin = item.findViewById(R.id.mani);
                    mani24h = item.findViewById(R.id.day);
                    mani7d = item.findViewById(R.id.week);
                    nameCoin.setText(valuteCBR.getName());
                    nominalCoin.append(" " + valuteCBR.getNominal());
                    maniCoin.append(" " + String.valueOf(valuteCBR.getValue()));
                    double h,d;
                    h= 100 - (Double.valueOf(mani_1[k].replaceAll(",","."))/(Double.valueOf(valuteCBR.getValue().replaceAll(",","."))/100));
                    d= 100 - (Double.valueOf(mani_2[k].replaceAll(",","."))/(Double.valueOf(valuteCBR.getValue().replaceAll(",","."))/100));
                    if(h < 0) {
                        mani24h.append(" " + String.valueOf(h).substring(0, 5));
                        mani24h.setTextColor(Color.RED);
                    }
                    if(d < 0) {
                        mani7d.append(" " + String.valueOf(d).substring(0, 5));
                        mani7d.setTextColor(Color.RED);
                    }
                    if(h > 0) {
                        mani24h.append(" +" + String.valueOf(h).substring(0, 4));
                        mani24h.setTextColor(Color.GREEN);
                    }
                    if(d > 0) {
                        mani7d.append(" +" + String.valueOf(d).substring(0, 4));
                        mani7d.setTextColor(Color.GREEN);
                    }
                    if(h == 0) {
                        mani24h.append(" " + String.valueOf(h).substring(0, 4));
                        mani24h.setTextColor(Color.GRAY);
                    }
                    if(d == 0) {
                        mani7d.append(" " + String.valueOf(d).substring(0, 4));
                        mani7d.setTextColor(Color.GRAY);
                    }
                    if(k == 0)
                        galerey.setImageResource(R.drawable.sterlink);
                    if(k == 1)
                        galerey.setImageResource(R.drawable.dollar);
                    if(k == 2)
                        galerey.setImageResource(R.drawable.evro);
                    if(k == 3)
                        galerey.setImageResource(R.drawable.yuan);

                    item.getLayoutParams().width = LinearLayout.LayoutParams.MATCH_PARENT;
                    linLayout.addView(item);

                    k++;
                }
            }
        }
            }


            @Override
            public void onFailure(Call<Parser> call, Throwable t) {
        LinearLayout linLayout = (LinearLayout) findViewById(R.id.linLayout);
        LayoutInflater ltInflater = getLayoutInflater();

        View item = ltInflater.inflate(R.layout.activity_error, linLayout, false);
        text = item.findViewById(R.id.text_item);
        text.setText("Error: \n" + "Пожауйста, проверьте Ваше подключение к сети и перезапустите приложение." +
                "Если у Вас не получается решить проблему, пишите: Kirzak899@gmail.com \n" + "С уважением.");
        item.getLayoutParams().width = LinearLayout.LayoutParams.MATCH_PARENT;
        linLayout.addView(item);
    }
    
}
