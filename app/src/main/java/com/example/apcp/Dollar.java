package com.example.apcp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.jjoe64.graphview.GraphView;
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
    Double[] val = new Double[15];
    Double[] dat = new Double[15];
    Toast toast;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dollar);

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

        Call<ValuteParser> call = parserXML.valueParser(sdf.format(calendar.getTime()),sdf.format(calendar1.getTime()),"R01235");

        make(call);


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
                    int k = 0;
                    for(ValPars valPars : rss.getValutePars()){

                        val[k] = Double.valueOf(valPars.getValue().replaceAll(",", "."));
                        k++;
                    }
                    GraphView graph = new GraphView(Dollar.this);
                    LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(new DataPoint[] {
                            new DataPoint(0, val[0]),
                            new DataPoint(1, val[1]),
                            new DataPoint(2, val[2]),
                            new DataPoint(3, val[3]),
                            new DataPoint(4, val[4]),
                            new DataPoint(5, val[5]),
                            new DataPoint(6, val[6]),
                            new DataPoint(7, val[7]),
                            new DataPoint(8, val[8])
                    });
                    graph.addSeries(series);
//        graph.addSeries(new LineGraphSeries(generateData()));
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

