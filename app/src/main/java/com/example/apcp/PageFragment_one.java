package com.example.apcp;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

public class PageFragment_one extends Fragment {
    public static final String ARG_PAGE = "ARG_PAGE";
    ImageView galerey;
    TextView text;
    TextView nameCoin;
    TextView nominalCoin;
    TextView maniCoin;
    TextView mani24h;
    TextView mani7d;
    String[] mani_1 = new String[4];
    String[] mani_2 = new String[4];
    String[] nomi_1 = new String[4];
    String[] nomi_2 = new String[4];
    String[] idCoin = {"R01035", "R01235", "R01239", "R01375", ""};
    String[] idCoinDollarVan = {"R01235", "R01035", "R01239", "R01375", ""};
    Double dollar;
    Handler handler;
    ViewPager viewPager;
    TabLayout tabLayout;
    ListView listView;
    ArrayAdapter<String> adapter;
    ArrayList<String> array;
    TextView news;
    TextView dateNews;
    Spinner spinner;
    ProgressDialog progressDialog;
    final String TEXT_TITLE = "text";
    final String TEXT_PRICE = "price";
    final String TEXT_VAL24 = "val24";
    final String TEXT_VAL7d = "val7d";
    final String TEXT_NOMINAL = "nominal";
    final String TEXT_GALEREY = "galerey";
    final String ID = "id";
    final String IT = "it";
    boolean rotate = false;

    static final String BASE_URL = "http://www.cbr.ru/scripts/";

    private int mPage;

    public static PageFragment_one newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        PageFragment_one fragment = new PageFragment_one();
        fragment.setArguments(args);
        return fragment;
    }

    public PageFragment_one() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mPage = getArguments().getInt(ARG_PAGE);
        }
    }

//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//
//    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

            listView = getActivity().findViewById(R.id.list_item_1);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    if(!rotate) {
                        if (i == 0) {
                            Intent intent = new Intent(getActivity(), Sterlink.class);
                            intent.putExtra("dol1","not");
                            startActivity(intent);
                        }
                        if (i == 1) {
                            Intent intent = new Intent(getActivity(), Dollar.class);
                            intent.putExtra("dol2","not");
                            startActivity(intent);
                        }
                        if (i == 2) {
                            Intent intent = new Intent(getActivity(), Evro.class);
                            intent.putExtra("dol3","not");
                            startActivity(intent);
                        }
                        if (i == 3) {
                            Intent intent = new Intent(getActivity(), Yuan.class);
                            intent.putExtra("dol4","not");
                            startActivity(intent);
                        }
                    }else{
                        if (i == 0) {
                            Intent intent = new Intent(getActivity(), Sterlink.class);
                            intent.putExtra("dol1","yes");
                            startActivity(intent);
                        }
                        if (i == 1) {
                            Intent intent = new Intent(getActivity(), Dollar.class);
                            intent.putExtra("dol2","yes");
                            startActivity(intent);
                        }
                        if (i == 2) {
                            Intent intent = new Intent(getActivity(), Evro.class);
                            intent.putExtra("dol3","yes");
                            startActivity(intent);
                        }
                        if (i == 3) {
                            Intent intent = new Intent(getActivity(), Yuan.class);
                            intent.putExtra("dol4","yes");
                            startActivity(intent);
                        }
                    }
                }
            });
        }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        if (mPage == 2) {
            View view = inflater.inflate(R.layout.activity_pager_one, container, false);
            Date currentDate = new Date();
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
            String dateText = dateFormat.format(currentDate);
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            try {
                calendar.setTime(sdf.parse(dateText));
            } catch (
                    ParseException e) {
                e.printStackTrace();
            }
            Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                    .addConverterFactory(SimpleXmlConverterFactory.create()).build();

            ParserXML parserXML = retrofit.create(ParserXML.class);

            calendar.add(Calendar.DAY_OF_MONTH, -1);

            Call<Parser> call_1 = parserXML.loadParser(sdf.format(calendar.getTime()));

            calendar.add(Calendar.WEEK_OF_MONTH, -1);

            Call<Parser> call_2 = parserXML.loadParser(sdf.format(calendar.getTime()));

            Call<Parser> call = parserXML.loadParser(dateText);

//            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, data);
//            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//            spinner.setAdapter(adapter);
//            spinner.setPrompt("Выбирете:");
//            spinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                @Override
//                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                    if(position == 1){
//                        try {
//                            makeGetRequest(call_1);
//                            Thread.sleep(1000);
//                            makeGet(call_2);
//                            Thread.sleep(1000);
//                            make(call);
//                        } catch (Exception e) {
//                        }
//                    }
//                }
//            });
        try {
            makeGetRequest(call_1);
            Thread.sleep(1000);
            makeGet(call_2);
            Thread.sleep(1000);
            make(call);
        } catch (Exception e) {
        }

            return view;
//        } else {
//            View view = inflater.inflate(R.layout.activity_pager_two, container, false);
//
//            Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
//                    .addConverterFactory(SimpleXmlConverterFactory.create()).build();
//
//            ParserXML parserXML = retrofit.create(ParserXML.class);
//
//            Call<NewsParser> call_3 = parserXML.newsParser();
//
//            progressDialog = new ProgressDialog(getActivity());
//            progressDialog.setTitle("Пожалуйста подождите");
//            progressDialog.setMessage("Загрузка данных...");
//            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//            //progressDialog.setMax(20);
//            progressDialog.setIndeterminate(true);
//            progressDialog.show();
//
//            makeCall(call_3);
//
//            return view;
//
//            }
        }



        public void makeGetRequest (Call < Parser > call_1) {

            call_1.enqueue(new Callback<Parser>() {
                @SuppressLint("StaticFieldLeak")

                @Override
                public void onResponse(Call<Parser> call_1, Response<Parser> response) {
                    if (response.isSuccessful()) {

                        Parser rss = response.body();

                        int k = 0;
                        for (ValuteCBR valuteCBR : rss.getValuteCBRList()) {
                            if (valuteCBR.getId().equals(idCoin[k])) {
                                nomi_1[k] = valuteCBR.getNominal();
                                mani_1[k] = valuteCBR.getValue();
                                k++;
                            }
                        }
                    }
                }

                @Override
                public void onFailure(Call<Parser> call_1, Throwable t) {
                }
            });

        }

        public void makeGet (Call < Parser > call_2) {

            call_2.enqueue(new Callback<Parser>() {
                @SuppressLint("StaticFieldLeak")

                @Override
                public void onResponse(Call<Parser> call_2, Response<Parser> response) {
                    if (response.isSuccessful()) {

                        Parser rss = response.body();

                        int k = 0;
                        for (ValuteCBR valuteCBR : rss.getValuteCBRList()) {
                            if (valuteCBR.getId().equals(idCoin[k])) {
                                nomi_2[k] = valuteCBR.getNominal();
                                mani_2[k] = valuteCBR.getValue();
                                k++;
                            }
                        }
                    }
                }


                @Override
                public void onFailure(Call<Parser> call_2, Throwable t) {
                }
            });

        }

        public void make (Call < Parser > call) {

            call.enqueue(new Callback<Parser>() {
                @SuppressLint("StaticFieldLeak")

                @Override
                public void onResponse(Call<Parser> call, Response<Parser> response) {
                    if (response.isSuccessful()) {

                        Parser rss = response.body();
                        //LinearLayout linLayout = (LinearLayout) findViewById(R.id.linLayout);

                        //LinearLayout linLayout = (LinearLayout) getActivity().findViewById(R.id.linLayout);
                        //LayoutInflater ltInflater = getLayoutInflater();

                        spinner = getActivity().findViewById(R.id.spinner);

                        listView = getActivity().findViewById(R.id.list_item_1);

                        ArrayList<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
                        Map<String, Object> m;

                        if(spinner.getSelectedItemPosition() == 0) {
                                rotate = false;
                            int k = 0;
                            int nominal;
                            for (ValuteCBR valuteCBR : rss.getValuteCBRList()) {
                                if (valuteCBR.getId().equals(idCoin[k])) {

                                    m = new HashMap<String, Object>();
                                    m.put(TEXT_TITLE, valuteCBR.getName());
                                    nominal = Integer.parseInt(valuteCBR.getNominal());
                                    m.put(TEXT_NOMINAL, " " + valuteCBR.getNominal());
                                    m.put(TEXT_PRICE, " " + String.valueOf((Double.parseDouble(String.valueOf(valuteCBR.getValue()).replaceAll(",", ".")) / nominal)).replaceAll(",", ".").substring(0, 6) + " " + Html.fromHtml("&#x20bd"));

                                    if (k == 1) {
                                        dollar = Double.parseDouble(String.valueOf((Double.parseDouble(String.valueOf(valuteCBR.getValue()).replaceAll(",", ".")) / nominal)).replaceAll(",", ".").substring(0, 6));
                                    }

//                                View item = ltInflater.inflate(R.layout.activity_main, linLayout, false);
//                                galerey = item.findViewById(R.id.image);
//                                nameCoin = item.findViewById(R.id.name);
//                                nominalCoin = item.findViewById(R.id.nominal);
//                                maniCoin = item.findViewById(R.id.mani);
//                                mani24h = item.findViewById(R.id.day);
//                                mani7d = item.findViewById(R.id.week);
//                                nameCoin.setText(valuteCBR.getName());
//                                nominalCoin.append(" " + valuteCBR.getNominal());
                                    // maniCoin.append(" " + String.valueOf(valuteCBR.getValue()).replaceAll(",", ".") + " " + Html.fromHtml("&#x20bd"));

                                    double h = 100 - ((Double.valueOf(mani_1[k].replaceAll(",", ".")) / Integer.parseInt(nomi_1[k])) / ((Double.valueOf(valuteCBR.getValue().replaceAll(",", ".")) / nominal) / 100));
                                    double d = 100 - ((Double.valueOf(mani_2[k].replaceAll(",", ".")) / Integer.parseInt(nomi_2[k])) / ((Double.valueOf(valuteCBR.getValue().replaceAll(",", ".")) / nominal) / 100));
                                    if (h < 0) {
                                        m.put(TEXT_VAL24, " - " + String.valueOf(h).substring(1, 5) + " %");
//                                    mani24h.append(" - " + String.valueOf(h).substring(1, 5) + " %");
//                                    mani24h.setTextColor(Color.RED);
                                    }
                                    if (d < 0) {
                                        m.put(TEXT_VAL7d, " - " + String.valueOf(d).substring(1, 5) + " %");
//                                    mani7d.append(" - " + String.valueOf(d).substring(1, 5) + " %");
//                                    mani7d.setTextColor(Color.RED);
                                    }
                                    if (h > 0) {
                                        m.put(TEXT_VAL24, " + " + String.valueOf(h).substring(0, 4) + " %");
//                                    mani24h.append(" + " + String.valueOf(h).substring(0, 4) + " %");
//                                    mani24h.setTextColor(Color.GREEN);
                                    }
                                    if (d > 0) {
                                        m.put(TEXT_VAL7d, " + " + String.valueOf(d).substring(0, 4) + " %");
//                                    mani7d.append(" + " + String.valueOf(d).substring(0, 4) + " %");
//                                    mani7d.setTextColor(Color.GREEN);
                                    }
                                    if (h == 0) {
                                        m.put(TEXT_VAL24, " " + "0.00 %");
//                                    mani24h.append(" " + "0.00 %");
//                                    mani24h.setTextColor(Color.GRAY);
                                    }
                                    if (d == 0) {
                                        m.put(TEXT_VAL7d, " " + "0.00 %");
//                                    mani7d.append(" " + "0.00 %");
//                                    mani7d.setTextColor(Color.GRAY);
                                    }
                                    if (k == 0)
                                        m.put(TEXT_GALEREY, R.drawable.sterlink);
                                    // galerey.setImageResource(R.drawable.sterlink);
                                    if (k == 1)
                                        m.put(TEXT_GALEREY, R.drawable.dollar);
//                                    galerey.setImageResource(R.drawable.dollar);
                                    if (k == 2)
                                        m.put(TEXT_GALEREY, R.drawable.evro);
//                                    galerey.setImageResource(R.drawable.evro);
                                    if (k == 3)
                                        m.put(TEXT_GALEREY, R.drawable.yuan);
//                                    galerey.setImageResource(R.drawable.yuan);

//                                item.getLayoutParams().width = LinearLayout.LayoutParams.MATCH_PARENT;
//                                linLayout.addView(item);

                                    data.add(m);

                                    k++;
                                }
                                String[] from = {TEXT_TITLE, TEXT_NOMINAL, TEXT_PRICE, TEXT_VAL24, TEXT_VAL7d, TEXT_GALEREY};
                                int[] to = {R.id.name, R.id.nominal, R.id.mani, R.id.day, R.id.week, R.id.image};
                                MySimpleAdapter sAdapter = new MySimpleAdapter(getActivity(), data, R.layout.activity_main,
                                        from, to);
                                listView.setAdapter(sAdapter);
                            }
                        }else{
                                 rotate = true;
                            for (ValuteCBR valuteCBR : rss.getValuteCBRList()) {
                                if (valuteCBR.getId().equals(idCoin[1])) {
                                      dollar = Double.parseDouble(String.valueOf((Double.parseDouble(String.valueOf(valuteCBR.getValue()).replaceAll(",", ".")) / 1)).replaceAll(",", ".").substring(0, 6));
                                    }
                            }

                            int k = 0;
                            int nominal;
                            for (ValuteCBR valuteCBR : rss.getValuteCBRList()) {
                                if (valuteCBR.getId().equals(idCoin[k])) {
                                    nominal = Integer.parseInt(valuteCBR.getNominal());
                                    m = new HashMap<String, Object>();
                                    if( k != 1) {
                                        m.put(TEXT_TITLE, valuteCBR.getName());
                                        m.put(TEXT_NOMINAL, " " + valuteCBR.getNominal());
                                        m.put(TEXT_PRICE, " " + String.valueOf((Double.parseDouble(String.valueOf(valuteCBR.getValue()).replaceAll(",", ".")) / nominal) / dollar).substring(0, 7) + " " + Html.fromHtml("&#36"));
                                    }else{
                                        m.put(TEXT_TITLE,"Российский Рубль");
                                        m.put(TEXT_NOMINAL," " + valuteCBR.getNominal());
                                        m.put(TEXT_PRICE," " + String.valueOf(1/dollar).substring(0,7) + " " + Html.fromHtml("&#36"));
                                    }

//                                View item = ltInflater.inflate(R.layout.activity_main, linLayout, false);
//                                galerey = item.findViewById(R.id.image);
//                                nameCoin = item.findViewById(R.id.name);
//                                nominalCoin = item.findViewById(R.id.nominal);
//                                maniCoin = item.findViewById(R.id.mani);
//                                mani24h = item.findViewById(R.id.day);
//                                mani7d = item.findViewById(R.id.week);
//                                nameCoin.setText(valuteCBR.getName());
//                                nominalCoin.append(" " + valuteCBR.getNominal());
                                    // maniCoin.append(" " + String.valueOf(valuteCBR.getValue()).replaceAll(",", ".") + " " + Html.fromHtml("&#x20bd"));
                                    double h, d;
                                    if(k != 1) {
                                        h =((Double.valueOf(mani_1[k].replaceAll(",", ".")) / Integer.parseInt(nomi_1[k])/ ((Double.valueOf(mani_1[1].replaceAll(",", ".")))))/ (((Double.parseDouble(String.valueOf(valuteCBR.getValue()).replaceAll(",", ".")) / nominal) / dollar) / 100)) - 100;
                                        d =((Double.valueOf(mani_2[k].replaceAll(",", ".")) / Integer.parseInt(nomi_2[k]) / ((Double.valueOf(mani_2[1].replaceAll(",", ".")))))/ (((Double.parseDouble(String.valueOf(valuteCBR.getValue()).replaceAll(",", ".")) / nominal) / dollar) / 100)) - 100;
                                    }else{
                                        h =100 - (Double.valueOf(mani_1[k].replaceAll(",", "."))) / (Double.parseDouble(valuteCBR.getValue().replaceAll(",", ".")) / 100);
                                        d =100 - (Double.valueOf(mani_2[k].replaceAll(",", "."))) / (Double.parseDouble(valuteCBR.getValue().replaceAll(",", ".")) / 100);
                                    }

                                    if (h > 0) {
                                        m.put(TEXT_VAL24, " - " + String.valueOf(h).substring(0,5)+ " %");
//                                    mani24h.append(" - " + String.valueOf(h).substring(1, 5) + " %");
//                                    mani24h.setTextColor(Color.RED);
                                    }
                                    if (d > 0) {
                                        m.put(TEXT_VAL7d, " - " + String.valueOf(d).substring(0,5) + " %");
//                                    mani7d.append(" - " + String.valueOf(d).substring(1, 5) + " %");
//                                    mani7d.setTextColor(Color.RED);
                                    }
                                    if (h < 0) {
                                        m.put(TEXT_VAL24, " + " + String.valueOf(h).substring(1, 6) + " %");
//                                    mani24h.append(" + " + String.valueOf(h).substring(0, 4) + " %");
//                                    mani24h.setTextColor(Color.GREEN);
                                    }
                                    if (d < 0) {
                                        m.put(TEXT_VAL7d, " + " + String.valueOf(d).substring(1,6) + " %");
//                                    mani7d.append(" + " + String.valueOf(d).substring(0, 4) + " %");
//                                    mani7d.setTextColor(Color.GREEN);
                                    }
                                    if (h == 0) {
                                        m.put(TEXT_VAL24, " " + "0.00 %");
//                                    mani24h.append(" " + "0.00 %");
//                                    mani24h.setTextColor(Color.GRAY);
                                    }
                                    if (d == 0) {
                                        m.put(TEXT_VAL7d, " " + "0.00 %");
//                                    mani7d.append(" " + "0.00 %");
//                                    mani7d.setTextColor(Color.GRAY);
                                    }
                                    if (k == 0)
                                        m.put(TEXT_GALEREY, R.drawable.sterlink);
                                    // galerey.setImageResource(R.drawable.sterlink);
                                    if (k == 1)
                                        m.put(TEXT_GALEREY, R.drawable.ruble);
//                                    galerey.setImageResource(R.drawable.dollar);
                                    if (k == 2)
                                        m.put(TEXT_GALEREY, R.drawable.evro);
//                                    galerey.setImageResource(R.drawable.evro);
                                    if (k == 3)
                                        m.put(TEXT_GALEREY, R.drawable.yuan);
//                                    galerey.setImageResource(R.drawable.yuan);

//                                item.getLayoutParams().width = LinearLayout.LayoutParams.MATCH_PARENT;
//                                linLayout.addView(item);

                                    data.add(m);

                                    k++;
                                }
                                String[] from = {TEXT_TITLE, TEXT_NOMINAL, TEXT_PRICE, TEXT_VAL24, TEXT_VAL7d, TEXT_GALEREY};
                                int[] to = {R.id.name, R.id.nominal, R.id.mani, R.id.day, R.id.week, R.id.image};
                                MySimpleAdapter sAdapter = new MySimpleAdapter(getActivity(), data, R.layout.activity_main,
                                        from, to);
                                listView.setAdapter(sAdapter);

                            }
                        }


                        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                if(position == 1){
                                    rotate = true;
                                    listView.removeAllViewsInLayout();
                            ArrayList<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
                            Map<String, Object> m;

                            int k = 0;
                            int nominal;
                            for (ValuteCBR valuteCBR : rss.getValuteCBRList()) {
                                if (valuteCBR.getId().equals(idCoin[k])) {
                                    nominal = Integer.parseInt(valuteCBR.getNominal());
                                    m = new HashMap<String, Object>();
                                    if( k != 1) {
                                        m.put(TEXT_TITLE, valuteCBR.getName());
                                        m.put(TEXT_NOMINAL, " " + valuteCBR.getNominal());
                                        m.put(TEXT_PRICE, " " + String.valueOf((Double.parseDouble(String.valueOf(valuteCBR.getValue()).replaceAll(",", ".")) / nominal) / dollar).substring(0, 7) + " " + Html.fromHtml("&#36"));
                                    }else{
                                        m.put(TEXT_TITLE,"Российский Рубль");
                                        m.put(TEXT_NOMINAL," " + valuteCBR.getNominal());
                                        m.put(TEXT_PRICE," " + String.valueOf(1/dollar).substring(0,7) + " " + Html.fromHtml("&#36"));
                                    }

//                                View item = ltInflater.inflate(R.layout.activity_main, linLayout, false);
//                                galerey = item.findViewById(R.id.image);
//                                nameCoin = item.findViewById(R.id.name);
//                                nominalCoin = item.findViewById(R.id.nominal);
//                                maniCoin = item.findViewById(R.id.mani);
//                                mani24h = item.findViewById(R.id.day);
//                                mani7d = item.findViewById(R.id.week);
//                                nameCoin.setText(valuteCBR.getName());
//                                nominalCoin.append(" " + valuteCBR.getNominal());
                                    // maniCoin.append(" " + String.valueOf(valuteCBR.getValue()).replaceAll(",", ".") + " " + Html.fromHtml("&#x20bd"));
                                    double h, d;
                                    if(k != 1) {
                                        h =((Double.valueOf(mani_1[k].replaceAll(",", ".")) / Integer.parseInt(nomi_1[k])/ ((Double.valueOf(mani_1[1].replaceAll(",", ".")))))/ (((Double.parseDouble(String.valueOf(valuteCBR.getValue()).replaceAll(",", ".")) / nominal) / dollar) / 100)) - 100;
                                        d =((Double.valueOf(mani_2[k].replaceAll(",", ".")) / Integer.parseInt(nomi_2[k]) / ((Double.valueOf(mani_2[1].replaceAll(",", ".")))))/ (((Double.parseDouble(String.valueOf(valuteCBR.getValue()).replaceAll(",", ".")) / nominal) / dollar) / 100)) - 100;
                                    }else{
                                        h =100 - (Double.valueOf(mani_1[k].replaceAll(",", "."))) / (Double.parseDouble(valuteCBR.getValue().replaceAll(",", ".")) / 100);
                                        d =100 - (Double.valueOf(mani_2[k].replaceAll(",", "."))) / (Double.parseDouble(valuteCBR.getValue().replaceAll(",", ".")) / 100);
                                    }

                                    if (h > 0) {
                                        m.put(TEXT_VAL24, " - " + String.valueOf(h).substring(0,5)+ " %");
//                                    mani24h.append(" - " + String.valueOf(h).substring(1, 5) + " %");
//                                    mani24h.setTextColor(Color.RED);
                                    }
                                    if (d > 0) {
                                        m.put(TEXT_VAL7d, " - " + String.valueOf(d).substring(0,5) + " %");
//                                    mani7d.append(" - " + String.valueOf(d).substring(1, 5) + " %");
//                                    mani7d.setTextColor(Color.RED);
                                    }
                                    if (h < 0) {
                                        m.put(TEXT_VAL24, " + " + String.valueOf(h).substring(1, 6) + " %");
//                                    mani24h.append(" + " + String.valueOf(h).substring(0, 4) + " %");
//                                    mani24h.setTextColor(Color.GREEN);
                                    }
                                    if (d < 0) {
                                        m.put(TEXT_VAL7d, " + " + String.valueOf(d).substring(1,6) + " %");
//                                    mani7d.append(" + " + String.valueOf(d).substring(0, 4) + " %");
//                                    mani7d.setTextColor(Color.GREEN);
                                    }
                                    if (h == 0) {
                                        m.put(TEXT_VAL24, " " + "0.00 %");
//                                    mani24h.append(" " + "0.00 %");
//                                    mani24h.setTextColor(Color.GRAY);
                                    }
                                    if (d == 0) {
                                        m.put(TEXT_VAL7d, " " + "0.00 %");
//                                    mani7d.append(" " + "0.00 %");
//                                    mani7d.setTextColor(Color.GRAY);
                                    }
                                    if (k == 0)
                                        m.put(TEXT_GALEREY, R.drawable.sterlink);
                                    // galerey.setImageResource(R.drawable.sterlink);
                                    if (k == 1)
                                        m.put(TEXT_GALEREY, R.drawable.ruble);
//                                    galerey.setImageResource(R.drawable.dollar);
                                    if (k == 2)
                                        m.put(TEXT_GALEREY, R.drawable.evro);
//                                    galerey.setImageResource(R.drawable.evro);
                                    if (k == 3)
                                        m.put(TEXT_GALEREY, R.drawable.yuan);
//                                    galerey.setImageResource(R.drawable.yuan);

//                                item.getLayoutParams().width = LinearLayout.LayoutParams.MATCH_PARENT;
//                                linLayout.addView(item);

                                    data.add(m);

                                    k++;
                                }
                                String[] from = {TEXT_TITLE, TEXT_NOMINAL, TEXT_PRICE, TEXT_VAL24, TEXT_VAL7d, TEXT_GALEREY};
                                int[] to = {R.id.name, R.id.nominal, R.id.mani, R.id.day, R.id.week, R.id.image};
                                MySimpleAdapter sAdapter = new MySimpleAdapter(getActivity(), data, R.layout.activity_main,
                                        from, to);
                                listView.setAdapter(sAdapter);

                            }

                                }
                                if(position == 0){
                                    rotate = false;
                                    listView.removeAllViewsInLayout();
                                    ArrayList<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
                                    Map<String, Object> m;

                                    int k = 0;
                                    int nominal;
                                    for (ValuteCBR valuteCBR : rss.getValuteCBRList()) {
                                        if (valuteCBR.getId().equals(idCoin[k])) {

                                            m = new HashMap<String, Object>();
                                            m.put(TEXT_TITLE, valuteCBR.getName());
                                            nominal = Integer.parseInt(valuteCBR.getNominal());
                                            m.put(TEXT_NOMINAL, " " + valuteCBR.getNominal());
                                            m.put(TEXT_PRICE, " " + String.valueOf((Double.parseDouble(String.valueOf(valuteCBR.getValue()).replaceAll(",", ".")) / nominal)).replaceAll(",", ".").substring(0, 6) + " " + Html.fromHtml("&#x20bd"));


//                                View item = ltInflater.inflate(R.layout.activity_main, linLayout, false);
//                                galerey = item.findViewById(R.id.image);
//                                nameCoin = item.findViewById(R.id.name);
//                                nominalCoin = item.findViewById(R.id.nominal);
//                                maniCoin = item.findViewById(R.id.mani);
//                                mani24h = item.findViewById(R.id.day);
//                                mani7d = item.findViewById(R.id.week);
//                                nameCoin.setText(valuteCBR.getName());
//                                nominalCoin.append(" " + valuteCBR.getNominal());
                                            // maniCoin.append(" " + String.valueOf(valuteCBR.getValue()).replaceAll(",", ".") + " " + Html.fromHtml("&#x20bd"));
                                            double h, d;
                                            h = 100 - ((Double.valueOf(mani_1[k].replaceAll(",", ".")) / Integer.parseInt(nomi_1[k])) / ((Double.valueOf(valuteCBR.getValue().replaceAll(",", ".")) / nominal) / 100));
                                            d = 100 - ((Double.valueOf(mani_2[k].replaceAll(",", ".")) / Integer.parseInt(nomi_2[k])) / ((Double.valueOf(valuteCBR.getValue().replaceAll(",", ".")) / nominal) / 100));
                                            if (h < 0) {
                                                m.put(TEXT_VAL24, " - " + String.valueOf(h).substring(1, 5) + " %");
//                                    mani24h.append(" - " + String.valueOf(h).substring(1, 5) + " %");
//                                    mani24h.setTextColor(Color.RED);
                                            }
                                            if (d < 0) {
                                                m.put(TEXT_VAL7d, " - " + String.valueOf(d).substring(1, 5) + " %");
//                                    mani7d.append(" - " + String.valueOf(d).substring(1, 5) + " %");
//                                    mani7d.setTextColor(Color.RED);
                                            }
                                            if (h > 0) {
                                                m.put(TEXT_VAL24, " + " + String.valueOf(h).substring(0, 4) + " %");
//                                    mani24h.append(" + " + String.valueOf(h).substring(0, 4) + " %");
//                                    mani24h.setTextColor(Color.GREEN);
                                            }
                                            if (d > 0) {
                                                m.put(TEXT_VAL7d, " + " + String.valueOf(d).substring(0, 4) + " %");
//                                    mani7d.append(" + " + String.valueOf(d).substring(0, 4) + " %");
//                                    mani7d.setTextColor(Color.GREEN);
                                            }
                                            if (h == 0) {
                                                m.put(TEXT_VAL24, " " + "0.00 %");
//                                    mani24h.append(" " + "0.00 %");
//                                    mani24h.setTextColor(Color.GRAY);
                                            }
                                            if (d == 0) {
                                                m.put(TEXT_VAL7d, " " + "0.00 %");
//                                    mani7d.append(" " + "0.00 %");
//                                    mani7d.setTextColor(Color.GRAY);
                                            }
                                            if (k == 0)
                                                m.put(TEXT_GALEREY, R.drawable.sterlink);
                                            // galerey.setImageResource(R.drawable.sterlink);
                                            if (k == 1)
                                                m.put(TEXT_GALEREY, R.drawable.dollar);
//                                    galerey.setImageResource(R.drawable.dollar);
                                            if (k == 2)
                                                m.put(TEXT_GALEREY, R.drawable.evro);
//                                    galerey.setImageResource(R.drawable.evro);
                                            if (k == 3)
                                                m.put(TEXT_GALEREY, R.drawable.yuan);
//                                    galerey.setImageResource(R.drawable.yuan);

//                                item.getLayoutParams().width = LinearLayout.LayoutParams.MATCH_PARENT;
//                                linLayout.addView(item);

                                            data.add(m);

                                            k++;
                                        }
                                        String[] from = {TEXT_TITLE, TEXT_NOMINAL, TEXT_PRICE, TEXT_VAL24, TEXT_VAL7d, TEXT_GALEREY};
                                        int[] to = {R.id.name, R.id.nominal, R.id.mani, R.id.day, R.id.week, R.id.image};
                                        MySimpleAdapter sAdapter = new MySimpleAdapter(getActivity(), data, R.layout.activity_main,
                                                from, to);
                                        listView.setAdapter(sAdapter);
                                    }
                                }
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {
                            }
                        });
                    }
                }


                @Override
                public void onFailure(Call<Parser> call, Throwable t) {
//                    progressDialog.dismiss();
                    String tx = "Пожауйста, проверьте Ваше подключение к сети и перезапустите приложение.\n Если у Вас не получается решить проблему, пишите: Kirzak899@gmail.com \n С уважением.";
                    Toast tool = Toast.makeText(getActivity(),tx,Toast.LENGTH_LONG);
                    tool.setGravity(Gravity.CENTER,0,0);
                    tool.show();
//                    LinearLayout linLayout = (LinearLayout) getActivity().findViewById(R.id.linLayout);
//                    LayoutInflater ltInflater = getLayoutInflater();
//
//                    View item = ltInflater.inflate(R.layout.activity_error, linLayout, false);
//                    text = item.findViewById(R.id.text_item);
//                    text.setText("Error: \n" + "Пожауйста, проверьте Ваше подключение к сети и перезапустите приложение." +
//                            "Если у Вас не получается решить проблему, пишите: Kirzak899@gmail.com \n" + "С уважением.");
//                    item.getLayoutParams().width = LinearLayout.LayoutParams.MATCH_PARENT;
//                    linLayout.addView(item);
                }
            });

        }

//        public void makeCall (Call < NewsParser > call_3) {
//
//            call_3.enqueue(new Callback<NewsParser>() {
//                @SuppressLint("StaticFieldLeak")
//
//                @Override
//                public void onResponse(Call<NewsParser> call_3, Response<NewsParser> response) {
//                    if (response.isSuccessful()) {
//
//                        NewsParser rss = response.body();
//
//                        LinearLayout linLayout = (LinearLayout) getActivity().findViewById(R.id.linLay);
//                        LayoutInflater ltInflater = getLayoutInflater();
//
//                        int k = 0;
//                        for (NewsItem newsItem : rss.getNewsItems()) {
//                            if (k == 20) {
//                                continue;
//                            }
//                            View item = ltInflater.inflate(R.layout.activity_news, linLayout, false);
//                            news = item.findViewById(R.id.list_item);
//                            dateNews = item.findViewById(R.id.da);
//                            news.setText(newsItem.getTitle());
//                            dateNews.setText(newsItem.getDate());
//                            item.getLayoutParams().width = LinearLayout.LayoutParams.MATCH_PARENT;
//                            linLayout.addView(item);
//                            item.setTag(k);
//                            k++;
////                                progressDialog.setIndeterminate(false);
////                                progressDialog.incrementProgressBy(1);
////                                progressDialog.setProgress(5);
//                        }
//                        progressDialog.dismiss();
//                    }
//                }


//                @Override
//                public void onFailure(Call<NewsParser> call_3, Throwable t) {
////                    LinearLayout linLayout = (LinearLayout) getActivity().findViewById(R.id.linLay);
////                    LayoutInflater ltInflater = getLayoutInflater();
////
////                    View item = ltInflater.inflate(R.layout.activity_error, linLayout, false);
////                    text = item.findViewById(R.id.text_item);
////                    text.setText("Error: \n" + "Пожауйста, проверьте Ваше подключение к сети и перезапустите приложение." +
////                            "Если у Вас не получается решить проблему, пишите: Kirzak899@gmail.com \n" + "С уважением.");
////                    item.getLayoutParams().width = LinearLayout.LayoutParams.MATCH_PARENT;
////                    linLayout.addView(item);
//                    progressDialog.dismiss();
//                    String tx = "Пожауйста, проверьте Ваше подключение к сети и перезапустите приложение.\nЕсли у Вас не получается решить проблему, пишите: Kirzak899@gmail.com\nС уважением.";
//                    Toast tool = Toast.makeText(getActivity(),tx,Toast.LENGTH_LONG);
//                    tool.setGravity(Gravity.CENTER,0,0);
//                    tool.show();
//
//                }
//            });
//
//        }


//    @Override
//    public void onClick(View v) {
//        LinearLayout linLayout = (LinearLayout)getActivity().findViewById(R.id.linLay);
//        linLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getContext(),"sdfsd",Toast.LENGTH_SHORT).show();
//            }
//        });
//    }

}

