package com.example.apcp;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

public class PageFragment_two extends Fragment {
    public static final String ARG_PAGE = "ARG_PAGE";
    TextView text;
    TextView news;
    TextView dateNews;
    ProgressDialog progressDialog;

    static final String BASE_URL = "http://www.cbr.ru/scripts/";

    private int mPage;

//    public static PageFragment_two newInstance(int page) {
//        Bundle args = new Bundle();
//        args.putInt(ARG_PAGE, page);
//        PageFragment_two fragment = new PageFragment_two();
//        fragment.setArguments(args);
//        return fragment;
//    }
    public  PageFragment_two(){}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mPage = getArguments().getInt(ARG_PAGE);
//        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       // if(mPage == 2){
            View view = inflater.inflate(R.layout.activity_pager_two, container, false);

            Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                    .addConverterFactory(SimpleXmlConverterFactory.create()).build();

            ParserXML parserXML = retrofit.create(ParserXML.class);

            Call<NewsParser> call_3 = parserXML.newsParser();
            progressDialog = new ProgressDialog(getActivity());
            progressDialog.setTitle("Пожалуйста подождите");
            progressDialog.setMessage("Загрузка данных...");
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            //progressDialog.setMax(20);
            progressDialog.setIndeterminate(true);
            progressDialog.show();

            makeCall(call_3);

            return view;
       // }else{View view = inflater.inflate(R.layout.activity_pager_one, container, false);
        //    return view;}
    }


    public void makeCall (Call <NewsParser> call_3) {

        call_3.enqueue(new Callback<NewsParser>() {
            @SuppressLint("StaticFieldLeak")

            @Override
            public void onResponse(Call<NewsParser> call_3, Response<NewsParser> response) {
                if (response.isSuccessful()) {

                    NewsParser rss = response.body();

                    LinearLayout linLayout = (LinearLayout)getActivity().findViewById(R.id.linLay);
                    LayoutInflater ltInflater = getLayoutInflater();
                    int k = 0;
                    for (NewsItem newsItem : rss.getNewsItems()) {
                        if(k == 20){
                            continue;
                        }
                        View item = ltInflater.inflate(R.layout.activity_news, linLayout, false);
                        news = item.findViewById(R.id.list_item);
                        dateNews = item.findViewById(R.id.da);
                        news.setText(newsItem.getTitle());
                        dateNews.setText(newsItem.getDate());
                        item.getLayoutParams().width = LinearLayout.LayoutParams.MATCH_PARENT;
                        linLayout.addView(item);
                        k++;
                    }
                    progressDialog.dismiss();
                }
            }


            @Override
            public void onFailure(Call<NewsParser> call_3, Throwable t) {
//                LinearLayout linLayout = (LinearLayout)getActivity().findViewById(R.id.linLayout);
//                LayoutInflater ltInflater = getLayoutInflater();
//
//                View item = ltInflater.inflate(R.layout.activity_error, linLayout, false);
//                text = item.findViewById(R.id.text_item);
//                text.setText("Error: \n" + "Пожауйста, проверьте Ваше подключение к сети и перезапустите приложение." +
//                        "Если у Вас не получается решить проблему, пишите: Kirzak899@gmail.com \n" + "С уважением.");
//                item.getLayoutParams().width = LinearLayout.LayoutParams.MATCH_PARENT;
//                linLayout.addView(item);
                progressDialog.dismiss();
                String tx = " Пожауйста, проверьте Ваше подключение к сети и перезапустите приложение.\nЕсли у Вас не получается решить проблему, пишите: Kirzak899@gmail.com\nС уважением.";
                Toast tool = Toast.makeText(getActivity(),tx,Toast.LENGTH_LONG);
                tool.setGravity(Gravity.CENTER,0,0);
                tool.show();
            }
        });

    }

}
