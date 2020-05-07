package com.example.apcp;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Scroller;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.ListFragment;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;
//implements View.OnClickListener
public class PageFragment_two extends Fragment {
    public static final String ARG_PAGE = "ARG_PAGE";
    TextView text;
    TextView news;
    TextView dateNews;
    ProgressDialog progressDialog;
    boolean vi = false;
    String textNews;
    ListView listView;
    ArrayList<String> arrayAdapter;
    final String TEXT_TITLE = "text";
    final String TEXT_DATE = "date";
    String[] link = new String[20];


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
//        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
//                .addConverterFactory(SimpleXmlConverterFactory.create()).build();
//
//        ParserXML parserXML = retrofit.create(ParserXML.class);
//
//        Call<NewsParser> call_3 = parserXML.newsParser();
//
//        makeCall(call_3);
    }

//    void setProgressDialog(){
//        if(vi == false)
//            Progre();
//    }
//
//    void Progre (){
//        if(vi == false) {
//            progressDialog.show();
//        }else{
//            progressDialog.dismiss();
//        }
//    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        listView = getActivity().findViewById(R.id.list_item);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), Activity_internet.class);
                intent.putExtra("link",link[i]);
                startActivity(intent);
            }
        });
    }


//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            //            WebView webView = getActivity().findViewById(R.id.web);
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
////            webView.loadUrl(link[position]);
//                Toast.makeText(getActivity(), link[position], Toast.LENGTH_SHORT).show();
//            }
//        });
//    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//       // if(mPage == 2){
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
//
           return view;
       // }else{View view = inflater.inflate(R.layout.activity_pager_one, container, false);
         //   return view;
    }


    public void makeCall (Call <NewsParser> call_3) {

        call_3.enqueue(new Callback<NewsParser>() {
            @SuppressLint("StaticFieldLeak")

            @Override
            public void onResponse(Call<NewsParser> call_3, Response<NewsParser> response) {
                if (response.isSuccessful()) {

                    NewsParser rss = response.body();

//                    LinearLayout linLayout = (LinearLayout) getActivity().findViewById(R.id.linLay);
//                    LayoutInflater ltInflater = getLayoutInflater();
//                    listView.findViewById(R.id.list_item);
//                    arrayAdapter= new ArrayList<>();
//                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1, arrayAdapter);
//                    listView.setAdapter(adapter);
                    listView = getActivity().findViewById(R.id.list_item);
                    //ArrayList<String> array = new ArrayList<>();
                    ArrayList<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
                    Map<String, Object> m;
                    int k = 0;
                    for (NewsItem newsItem : rss.getNewsItems()) {
                        if(k == 20){
                            continue;
                        }

                        m = new HashMap<String, Object>();
                        m.put(TEXT_TITLE, newsItem.getTitle());
                        m.put(TEXT_DATE, newsItem.getDate());
                        data.add(m);
                        link[k] = newsItem.getUrl();
                          //array.add("\n"+ newsItem.getTitle()+"\n");
//                        arrayAdapter.add(newsItem.getTitle());
//                        adapter.notifyDataSetChanged();
//                        View item = ltInflater.inflate(R.layout.activity_news,linLayout,false);
//                        news = item.findViewById(R.id.list_item);
//                        dateNews = item.findViewById(R.id.da);
//                        news.setText(newsItem.getTitle());
//                        dateNews.setText(newsItem.getDate());
//                        item.getLayoutParams().width = LinearLayout.LayoutParams.MATCH_PARENT;
//                        linLayout.addView(item);

                        k++;
                    }
                    String[] from = {TEXT_TITLE,TEXT_DATE};
                    int[] to = {R.id.list_text,R.id.da};
                    SimpleAdapter sAdapter = new SimpleAdapter(getActivity(), data, R.layout.activity_news,
                            from, to);
                    listView.setAdapter(sAdapter);
                   // ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.activity_news,array);
                    //listView.setAdapter(adapter);
//                    LinearLayout linLayout = (LinearLayout) getActivity().findViewById(R.id.linLay);
//                    LayoutInflater ltInflater = getLayoutInflater();
//                    int k = 0;
//                    for (NewsItem newsItem : rss.getNewsItems()) {
//                        if(k == 20){
//                            continue;
//                        }
//                        View item = ltInflater.inflate(R.layout.activity_news, linLayout, false);
//                        news = item.findViewById(R.id.list_item);
//                        dateNews = item.findViewById(R.id.da);
//                        news.setText(newsItem.getTitle());
//                        dateNews.setText(newsItem.getDate());
//                        item.getLayoutParams().width = LinearLayout.LayoutParams.MATCH_PARENT;
//                        linLayout.addView(item);
//                        k++;
//                    }
                    progressDialog.dismiss();
//                    vi = true;

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
                vi = true;
                String tx = " Пожауйста, проверьте Ваше подключение к сети и перезапустите приложение.\nЕсли у Вас не получается решить проблему, пишите: Kirzak899@gmail.com\nС уважением.";
                Toast tool = Toast.makeText(getActivity(),tx,Toast.LENGTH_LONG);
                tool.setGravity(Gravity.CENTER,0,0);
                tool.show();
            }
        });

    }

}

