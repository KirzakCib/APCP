package com.example.apcp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.helper.StaticLabelsFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

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

public class Yuan extends AppCompatActivity {

    static final String BASE_URL = "http://www.cbr.ru/scripts/";
    Double[] val = new Double[90];
    String[] dat = new String[90];
    String[] valDol = new String[90];
    Toast toast;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yuan);


        Intent intent = getIntent();

        String dol = intent.getStringExtra("dol4");
        if(dol.equals("not")) {
            Date currentDate = new Date();
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
            String dateText = dateFormat.format(currentDate);
            Calendar calendar = Calendar.getInstance();
            Calendar calendar1 = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            try {
                calendar.setTime(sdf.parse(dateText));
                calendar1.setTime(sdf.parse(dateText));
            } catch (
                    ParseException e) {
                e.printStackTrace();
            }
            Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                    .addConverterFactory(SimpleXmlConverterFactory.create()).build();

            ParserXML parserXML = retrofit.create(ParserXML.class);

            calendar.add(Calendar.WEEK_OF_MONTH, -3);

            Call<ValuteParser> call = parserXML.valueParser(sdf.format(calendar.getTime()), sdf.format(calendar1.getTime()), "R01375");

            make(call);
        }

        if(dol.equals("yes")) {
            Date currentDate = new Date();
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
            String dateText = dateFormat.format(currentDate);
            Calendar calendar = Calendar.getInstance();
            Calendar calendar1 = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            try {
                calendar.setTime(sdf.parse(dateText));
                calendar1.setTime(sdf.parse(dateText));
            } catch (
                    ParseException e) {
                e.printStackTrace();
            }
            Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                    .addConverterFactory(SimpleXmlConverterFactory.create()).build();

            ParserXML parserXML = retrofit.create(ParserXML.class);

            calendar.add(Calendar.WEEK_OF_MONTH, -3);

            Call<ValuteParser> call1 = parserXML.valueParser(sdf.format(calendar.getTime()), sdf.format(calendar1.getTime()), "R01235");


            Call<ValuteParser> call2 = parserXML.valueParser(sdf.format(calendar.getTime()), sdf.format(calendar1.getTime()), "R01375");

//            make1(call1);
//            make2(call2);
            try {
                make1(call1);
                Thread.sleep(1000);
                make2(call2);
            } catch (Exception e) {
            }


        }

//        GraphView graph = new GraphView(this);
//        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(new DataPoint[] {
//                new DataPoint(1, 73.5),
//                new DataPoint(2, 72.4),
//                new DataPoint(3, 74.0),
////                new DataPoint(dat[3], val[3]),
////                new DataPoint(dat[4], val[4]),
////                new DataPoint(dat[5], val[5]),
//        });
//        graph.addSeries(series);
////        graph.addSeries(new LineGraphSeries(generateData()));
//        LinearLayout layout = (LinearLayout) findViewById(R.id.layout);
//        layout.addView(graph);



    }

//    private DataPoint[] generateData() {
//        DataPoint[] values = new DataPoint[5];
//        for(int i =0; i < 5; i++){
//            double x = dat[i];
//            double y = val[i];
//            DataPoint v = new DataPoint(x,y);
//            values[i] = v;
//        }
//        return values;
//    }

    public void make (Call < ValuteParser > call) {

        call.enqueue(new Callback<ValuteParser>() {
            @SuppressLint("StaticFieldLeak")

            @Override
            public void onResponse(Call<ValuteParser> call, Response<ValuteParser> response) {
                if (response.isSuccessful()) {

                    ValuteParser rss = response.body();
                    int k =0;
                    for(ValPars valPars : rss.getValutePars()){
//                        if(k >= 9)
//                            continue;
                        val[k] = Double.valueOf(valPars.getValue().replaceAll(",", ".")) / Double.valueOf(valPars.getNominal());
                        dat[k] = valPars.getDate().substring(0,2);
                        k++;
                    }
                    GraphView graph = new GraphView(Yuan.this);
                    LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(new DataPoint[] {
                            new DataPoint(0, val[k-9]),
                            new DataPoint(1, val[k-8]),
                            new DataPoint(2, val[k-7]),
                            new DataPoint(3, val[k-6]),
                            new DataPoint(4, val[k-5]),
                            new DataPoint(5, val[k-4]),
                            new DataPoint(6, val[k-3]),
                            new DataPoint(7, val[k-2]),
                            new DataPoint(8, val[k-1])
                    });
                    graph.addSeries(series);
//        graph.addSeries(new LineGraphSeries(generateData()));
                    // set manual X bounds
                    StaticLabelsFormatter staticLabelsFormatter = new StaticLabelsFormatter(graph);
//                    staticLabelsFormatter.setVerticalLabels(new String[] {"-20", "-15", "-10","-5","0","5","10","15","20"});
//                    graph.getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter);

                    // set manual Y bounds
                    staticLabelsFormatter.setHorizontalLabels(new String[] {dat[k-9],dat[k-8],dat[k-7],dat[k-6],dat[k-5],dat[k-4],dat[k-3],dat[k-2],dat[k-1],});
                    graph.getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter);
                    graph.getViewport().setScrollable(true);

                    LinearLayout layout = (LinearLayout) findViewById(R.id.layout);
                    layout.addView(graph);
                }
            }



            @Override
            public void onFailure(Call<ValuteParser> call, Throwable t) {
                String tx = "Пожауйста, проверьте Ваше подключение к сети и перезапустите приложение.\n Если у Вас не получается решить проблему, пишите: Kirzak899@gmail.com \n С уважением.";
                Toast tool = Toast.makeText(Yuan.this,tx,Toast.LENGTH_LONG);
                tool.setGravity(Gravity.CENTER,0,0);
                tool.show();
            }
        });

    }

    public void make1 (Call < ValuteParser > call) {

        call.enqueue(new Callback<ValuteParser>() {
            @SuppressLint("StaticFieldLeak")

            @Override
            public void onResponse(Call<ValuteParser> call, Response<ValuteParser> response) {
                if (response.isSuccessful()) {

                    ValuteParser rss = response.body();
                    int k =0;
                    for(ValPars valPars : rss.getValutePars()){
//                        if(k >= 9)
//                            continue;
                        valDol[k] = valPars.getValue();
                        k++;
                    }
                }
            }



            @Override
            public void onFailure(Call<ValuteParser> call, Throwable t) {
                String tx = "Пожауйста, проверьте Ваше подключение к сети и перезапустите приложение.\n Если у Вас не получается решить проблему, пишите: Kirzak899@gmail.com \n С уважением.";
                Toast tool = Toast.makeText(Yuan.this,tx,Toast.LENGTH_LONG);
                tool.setGravity(Gravity.CENTER,0,0);
                tool.show();
            }
        });

    }

    public void make2 (Call < ValuteParser > call) {

        call.enqueue(new Callback<ValuteParser>() {
            @SuppressLint("StaticFieldLeak")

            @Override
            public void onResponse(Call<ValuteParser> call, Response<ValuteParser> response) {
                if (response.isSuccessful()) {

                    ValuteParser rss = response.body();
                    int k =0;
                    for(ValPars valPars : rss.getValutePars()){
//                        if(k >= 9)
//                            continue;
                        val[k] = Double.valueOf(valPars.getValue().replaceAll(",", ".")) /Double.valueOf(valPars.getNominal());
                       // val[k] = Double.valueOf(valPars.getValue().replaceAll(",", "."));
                        dat[k] = valPars.getDate().substring(0,2);
                        k++;
                    }
                    GraphView graph = new GraphView(Yuan.this);
                    LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(new DataPoint[] {
                            new DataPoint(0, val[k-9]/Double.valueOf(valDol[k-9].replaceAll(",","."))),
                            new DataPoint(1, val[k-8]/Double.valueOf(valDol[k-8].replaceAll(",","."))),
                            new DataPoint(2, val[k-7]/Double.valueOf(valDol[k-7].replaceAll(",","."))),
                            new DataPoint(3, val[k-6]/Double.valueOf(valDol[k-6].replaceAll(",","."))),
                            new DataPoint(4, val[k-5]/Double.valueOf(valDol[k-5].replaceAll(",","."))),
                            new DataPoint(5, val[k-4]/Double.valueOf(valDol[k-4].replaceAll(",","."))),
                            new DataPoint(6, val[k-3]/Double.valueOf(valDol[k-3].replaceAll(",","."))),
                            new DataPoint(7, val[k-2]/Double.valueOf(valDol[k-2].replaceAll(",","."))),
                            new DataPoint(8, val[k-1]/Double.valueOf(valDol[k-1].replaceAll(",",".")))
                    });
                    graph.addSeries(series);
//        graph.addSeries(new LineGraphSeries(generateData()));
                    // set manual X bounds
                    StaticLabelsFormatter staticLabelsFormatter = new StaticLabelsFormatter(graph);
//                    staticLabelsFormatter.setVerticalLabels(new String[] {"-20", "-15", "-10","-5","0","5","10","15","20"});
//                    graph.getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter);

                    // set manual Y bounds
                    staticLabelsFormatter.setHorizontalLabels(new String[] {dat[k-9],dat[k-8],dat[k-7],dat[k-6],dat[k-5],dat[k-4],dat[k-3],dat[k-2],dat[k-1],});
                    graph.getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter);
                    graph.getViewport().setScrollable(true);

                    LinearLayout layout = (LinearLayout) findViewById(R.id.layout);
                    layout.addView(graph);
                }
            }



            @Override
            public void onFailure(Call<ValuteParser> call, Throwable t) {
                String tx = "Пожауйста, проверьте Ваше подключение к сети и перезапустите приложение.\n Если у Вас не получается решить проблему, пишите: Kirzak899@gmail.com \n С уважением.";
                Toast tool = Toast.makeText(Yuan.this,tx,Toast.LENGTH_LONG);
                tool.setGravity(Gravity.CENTER,0,0);
                tool.show();
            }
        });

    }

//    private DataPoint[] generateData() {
//        DataPoint[] values = new DataPoint[5];
//        for(int i =0; i < 5; i++){
//            double x = dat[i];
//            double y = val[i];
//            DataPoint v = new DataPoint(x,y);
//            values[i] = v;
//        }
//        return values;
//    }



}

