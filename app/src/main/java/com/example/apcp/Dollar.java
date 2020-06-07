package com.example.apcp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;
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

public class Dollar extends AppCompatActivity {

    static final String BASE_URL = "http://www.cbr.ru/scripts/";
    Double[] val = new Double[9];
    String[] dat = new String[9];
    Toast toast;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dollar);

        Intent intent = getIntent();

        String dol = intent.getStringExtra("dol2");

        if(dol.equals("yes")){
            TextView textView = findViewById(R.id.dol2);
            textView.append("Российский Рубль");
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

            Call<ValuteParser> call = parserXML.valueParser(sdf.format(calendar.getTime()), sdf.format(calendar1.getTime()), "R01235");

            make1(call);
        }

        if(dol.equals("not")) {

            TextView textView = findViewById(R.id.dol2);
            textView.append("Доллар США");

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

            Call<ValuteParser> call = parserXML.valueParser(sdf.format(calendar.getTime()), sdf.format(calendar1.getTime()), "R01235");

            make(call);
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
            Date currentDate = new Date();
            @Override
            public void onResponse(Call<ValuteParser> call, Response<ValuteParser> response) {
                if (response.isSuccessful()) {

                    ValuteParser rss = response.body();
                    int k =0;
                    for(ValPars valPars : rss.getValutePars()){
                        if( k >= 9)
                            continue;
                        val[k] = Double.valueOf(valPars.getValue().replaceAll(",", ".")) / Double.valueOf(valPars.getNominal());
                        dat[k] = valPars.getDate().substring(0,2);
                        k++;
                    }
                    GraphView graph = new GraphView(Dollar.this);
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
                Toast tool = Toast.makeText(Dollar.this,tx,Toast.LENGTH_LONG);
                tool.setGravity(Gravity.CENTER,0,0);
                tool.show();
            }
        });

    }

    public void make1 (Call < ValuteParser > call) {

        call.enqueue(new Callback<ValuteParser>() {
            @SuppressLint("StaticFieldLeak")
            Date currentDate = new Date();
            @Override
            public void onResponse(Call<ValuteParser> call, Response<ValuteParser> response) {
                if (response.isSuccessful()) {

                    ValuteParser rss = response.body();
                    int k =0;
                    for(ValPars valPars : rss.getValutePars()){
                        if(k >= 9)
                            continue;
                        val[k] = Double.valueOf(valPars.getValue().replaceAll(",", ".")) / Double.valueOf(valPars.getNominal());
                        dat[k] = valPars.getDate().substring(0,2);
                        k++;
                    }
                    GraphView graph = new GraphView(Dollar.this);
                    LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(new DataPoint[] {
                            new DataPoint(0, 1/val[k-9]),
                            new DataPoint(1, 1/val[k-8]),
                            new DataPoint(2, 1/val[k-7]),
                            new DataPoint(3, 1/val[k-6]),
                            new DataPoint(4, 1/val[k-5]),
                            new DataPoint(5, 1/val[k-4]),
                            new DataPoint(6, 1/val[k-3]),
                            new DataPoint(7, 1/val[k-2]),
                            new DataPoint(8, 1/val[k-1])
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
                Toast tool = Toast.makeText(Dollar.this,tx,Toast.LENGTH_LONG);
                tool.setGravity(Gravity.CENTER,0,0);
                tool.show();
            }
        });

    }
}

