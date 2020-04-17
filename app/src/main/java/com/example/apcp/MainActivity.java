package com.example.apcp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;


public class MainActivity extends AppCompatActivity implements Callback<RSSFeed> {
    ListView listView;
    ArrayAdapter<String> adapter;
    ArrayList<String> array;
    ImageView galerey;
    TextView nameCoin;
    TextView nominalCoin;
    TextView maniCoin;
    String[] idCoin = {"R01035","R01235","R01239","R01375",""};

    static final String BASE_URL = "https://www.cbr-xml-daily.ru/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_main_coin);
//        setContentView(R.layout.probnik);
//        listView = findViewById(R.id.list_item);
//        array = new ArrayList<>();
//
//        adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_single_choice,array);
//        listView.setAdapter(adapter);
        Date currentDate = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
        String dateText = dateFormat.format(currentDate);

        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(SimpleXmlConverterFactory.create()).build();

        VogellaAPI vogellaAPI = retrofit.create(VogellaAPI.class);

        Call<RSSFeed> call = vogellaAPI.loadRSSFeed();
        call.enqueue(this);

    }

    @Override
    public void onResponse(Call<RSSFeed> call, Response<RSSFeed> response) {

        if (response.isSuccessful()) {

            RSSFeed rss = response.body();
            LinearLayout linLayout = (LinearLayout) findViewById(R.id.linLayout);

            LayoutInflater ltInflater = getLayoutInflater();

                int k = 0;
            for (Article article : rss.getArticleList()) {
                if (article.getId().equals(idCoin[k])) {

                        View item = ltInflater.inflate(R.layout.activity_main, linLayout, false);
                        galerey = item.findViewById(R.id.image);
                        nameCoin = item.findViewById(R.id.name);
                        nominalCoin = item.findViewById(R.id.nominal);
                        maniCoin = item.findViewById(R.id.mani);
                        nameCoin.setText(article.getName());
                        nominalCoin.append(" " + article.getNominal());
                        maniCoin.append(" " + article.getValue());
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

//                    array.add(article.getName() + " " + article.getValue() + "      " + article.getNominal());
//                    adapter.notifyDataSetChanged();
                      k++;
                }
            }
        }
    }

    @Override
    public void onFailure(Call<RSSFeed> call, Throwable t) {
        t.printStackTrace();
    }
}
