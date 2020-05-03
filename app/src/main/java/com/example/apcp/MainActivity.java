package com.example.apcp;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

//public class MainActivity extends AppCompatActivity {
//
//    @Override protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//}
import android.app.Activity;
import android.os.Bundle;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

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
//
//public class MainActivity extends Activity implements OnTouchListener
//{
//    private ViewFlipper flipper = null;
//    private float fromPosition;
//
//    @Override
//    public void onCreate(Bundle savedInstanceState)
//    {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.main);
//
//        LinearLayout mainLayout = (LinearLayout) findViewById(R.id.main_layout);
//        mainLayout.setOnTouchListener(this);
//
//        flipper = (ViewFlipper) findViewById(R.id.flipper);
//
//        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        int layouts[] = new int[]{ R.layout.first, R.layout.second, R.layout.third, R.layout.fourth };
//        for (int layout : layouts)
//            flipper.addView(inflater.inflate(layout, null));
//    }
//
//    public boolean onTouch(View view, MotionEvent event)
//    {
//        switch (event.getAction())
//        {
//            case MotionEvent.ACTION_DOWN:
//                fromPosition = event.getX();
//                break;
//            case MotionEvent.ACTION_UP:
//                float toPosition = event.getX();
//                if (fromPosition > toPosition)
//                {
//                    flipper.setInAnimation(AnimationUtils.loadAnimation(this,R.anim.go_next_in));
//                    flipper.setOutAnimation(AnimationUtils.loadAnimation(this,R.anim.go_next_out));
//                    flipper.showNext();
//                }
//                else if (fromPosition < toPosition)
//                {
//                    flipper.setInAnimation(AnimationUtils.loadAnimation(this,R.anim.go_prev_in));
//                    flipper.setOutAnimation(AnimationUtils.loadAnimation(this,R.anim.go_prev_out));
//                    flipper.showPrevious();
//                }
//            default:
//                break;
//        }
//        return true;
//    }
//}
//впавпопоаырпаврповрпывадолрп
//import android.os.Bundle;
//
//import androidx.appcompat.app.ActionBar;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.appcompat.widget.Toolbar;
//import androidx.fragment.app.Fragment;
//import androidx.fragment.app.FragmentManager;
//import androidx.fragment.app.FragmentPagerAdapter;
//import androidx.viewpager.widget.ViewPager;
//
//import com.google.android.material.tabs.TabLayout;
//
//public class MainActivity extends AppCompatActivity {
//    private static final String TAG = MainActivity.class.getName();
//
//    private MyPagerAdapter mFragmentAdapter;
//    private ViewPager mViewPager;
//    private TabLayout mTabLayout;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_pager_one);
//
//        // Get the ViewPager and apply the PagerAdapter
//        mFragmentAdapter = new MyPagerAdapter(getSupportFragmentManager());
//        mViewPager = (ViewPager) findViewById(R.id.viewpager);
//        mViewPager.setAdapter(mFragmentAdapter);
//
//        // link the tabLayout and the viewpager together
//        mTabLayout = (TabLayout) findViewById(R.id.tabs);
//        mTabLayout.setupWithViewPager(mViewPager);
//    }
//
//    private class MyPagerAdapter extends FragmentPagerAdapter {
//
//        public MyPagerAdapter(FragmentManager supportFragmentManager) {
//            super(supportFragmentManager);
//        }
//
//        // Returns the fragment to display for that page
//        @Override
//        public Fragment getItem(int position) {
//            switch(position) {
//                case 0:
//                    return new Fragment();
//
//                case 1:
//                    return new Fragment();
//
//                case 2:
//                    return new Fragment();
//
//                default:
//                    return null;
//            }
//        }
//
//        // Will be displayed as the tab's label
//        @Override
//        public CharSequence getPageTitle(int position) {
//            switch(position) {
//                case 0:
//                    return "Fragment 1 title";
//
//                case 1:
//                    return "Fragment 2 title";
//
//                case 2:
//                    return "Fragment 3 title";
//
//                default:
//                    return null;
//            }
//        }
//
//        // Returns total number of pages
//        @Override
//        public int getCount() {
//            return 3;
//        }
//
//    }
//}

//public class MainActivity extends AppCompatActivity {

    //private Toolbar toolbar;
//    private TabLayout tabLayout;
//    private ViewPager viewPager;

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_pager_one);


        //toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        //ActionBar actionBar = getSupportActionBar();
        //actionBar.setDisplayHomeAsUpEnabled(true);

//        viewPager = (ViewPager) findViewById(R.id.viewpager);
//        setupViewPager(viewPager);
//
//        tabLayout = (TabLayout) findViewById(R.id.tablayout);
//        tabLayout.setupWithViewPager(viewPager);
//
//
//    }
//
//    private void setupViewPager(ViewPager viewPager) {
//        FragmentAdapter adapter = new FragmentAdapter(getSupportFragmentManager());
//        adapter.addFragment(new PageFragment_one(), "People");
//        adapter.addFragment(new PageFragment_one(), "Group");
//        adapter.addFragment(new PageFragment_one(), "Calls");
//        viewPager.setAdapter(adapter);
//    }
//}
//AppCompatActivity
//public class MainActivity extends AppCompatActivity implements Callback<Parser> {
//aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa
public class MainActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        FragmentAdapter adapter = new FragmentAdapter(getSupportFragmentManager());
        adapter.addFragment(new PageFragment_one(), "КУРСЫ ВАЛЮТ");
        adapter.addFragment(new PageFragment_two(), "НОВОСТИ");
        viewPager.setAdapter(adapter);
    }

}
//aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa
//    ImageView galerey;
//    TextView text;
//    TextView nameCoin;
//    TextView nominalCoin;
//    TextView maniCoin;
//    TextView mani24h;
//    TextView mani7d;
//    String[] mani_1 = new String[4];
//    String[] mani_2 = new String[4];
//    String[] idCoin = {"R01035", "R01235", "R01239", "R01375", ""};
//    Handler handler;
//    ViewPager viewPager;
//    TabLayout tabLayout;

    //static final String BASE_URL = "http://www.cbr.ru/scripts/";

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_fragment);
//
//        // Получаем ViewPager и устанавливаем в него адаптер
//        ViewPager viewPager = findViewById(R.id.viewpager);
//        // viewPager.findViewById()
//        viewPager.setAdapter(
//                new FragmentAdapter(getSupportFragmentManager()));
//
//        // Передаём ViewPager в TabLayout
//        TabLayout tabLayout = findViewById(R.id.tabs);
//        tabLayout.setupWithViewPager(viewPager);
//
//    }
//}aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa
//        ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
//         progressDialog.setTitle("Пожалуйста подождите");
//         progressDialog.setMessage("Загрузка данных...");
//         progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
//         progressDialog.setMax(100);
//         progressDialog.setIndeterminate(true);
//         progressDialog.show();
//
//         handler = new Handler() {
//             public void handleMessage(Message msg) {
//                 progressDialog.setIndeterminate(false);
//                 if (progressDialog.getProgress() < progressDialog.getMax()) {
//                     progressDialog.incrementProgressBy(10);
//                     progressDialog.incrementSecondaryProgressBy(7);
//                     handler.sendEmptyMessageDelayed(0, 100);
//                 } else {
//                     progressDialog.dismiss();
//                 }
//             }
//         };
//
//         handler.sendEmptyMessageDelayed(0, 10000);


   //  if ({

//
//        // Получаем ViewPager и устанавливаем в него адаптер
//        ViewPager viewPager = findViewById(R.id.viewpager);
//        viewPager.setAdapter(
//                new FragmentAdapter(getSupportFragmentManager(), MainActivity.this));
//        // Передаём ViewPager в TabLayout
//        TabLayout tabLayout = findViewById(R.id.sliding_tabs);
//        tabLayout.setupWithViewPager(viewPager);
//aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa
//         ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
//         progressDialog.setTitle("Сообщение");
//         progressDialog.setMessage("Загрузка данных...");
//         progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
//         progressDialog.setMax(100);
//         progressDialog.setIndeterminate(true);
//         progressDialog.show();
//
//         handler = new Handler() {
//             public void handleMessage(Message msg) {
//                 progressDialog.setIndeterminate(false);
//                 if (progressDialog.getProgress() < progressDialog.getMax()) {
//                     progressDialog.incrementProgressBy(10);
//                     progressDialog.incrementSecondaryProgressBy(7);
//                     handler.sendEmptyMessageDelayed(0, 100);
//                 } else {
//                     progressDialog.dismiss();
//                 }
//             }
//         };
//
//         handler.sendEmptyMessageDelayed(0, 1000);
//
//         Date currentDate = new Date();
//         DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
//         String dateText = dateFormat.format(currentDate);
//         Calendar calendar = Calendar.getInstance();
//         SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//         try {
//             calendar.setTime(sdf.parse(dateText));
//         } catch (
//                 ParseException e) {
//             e.printStackTrace();
//         }
//         Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
//                 .addConverterFactory(SimpleXmlConverterFactory.create()).build();
//
//         ParserXML parserXML = retrofit.create(ParserXML.class);
//
//         calendar.add(Calendar.DAY_OF_MONTH, -1);
//
//         Call<Parser> call_1 = parserXML.loadParser(sdf.format(calendar.getTime()));
//
//         calendar.add(Calendar.WEEK_OF_MONTH, -1);
//
//         Call<Parser> call_2 = parserXML.loadParser(sdf.format(calendar.getTime()));
//
//         try {
//             makeGetRequest(call_1);
//             Thread.sleep(1000);
//             makeGet(call_2);
//             Thread.sleep(1000);
//         } catch (Exception e) {
//         }
//
//         Call<Parser> call = parserXML.loadParser(dateText);
//         call.enqueue(this);
//
//
//      //  }
//
//    }
//
//    public void makeGetRequest(Call<Parser> call_1) {
//
//        call_1.enqueue(new Callback<Parser>() {
//            @SuppressLint("StaticFieldLeak")
//
//            @Override
//            public void onResponse(Call<Parser> call_1, Response<Parser> response) {
//                if (response.isSuccessful()) {
//
//            Parser rss = response.body();
//
//                int k = 0;
//            for (ValuteCBR valuteCBR : rss.getValuteCBRList()) {
//                if (valuteCBR.getId().equals(idCoin[k])) {
//
//                        mani_1[k] = valuteCBR.getValue();
//                      k++;
//                        }
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Parser> call_1, Throwable t) { }
//        });
//
//    }
//
//    public void makeGet(Call<Parser> call_2) {
//
//        call_2.enqueue(new Callback<Parser>() {
//            @SuppressLint("StaticFieldLeak")
//
//            @Override
//            public void onResponse(Call<Parser> call_2, Response<Parser> response) {
//                if (response.isSuccessful()) {
//
//                    Parser rss = response.body();
//
//                    int k = 0;
//                    for (ValuteCBR valuteCBR : rss.getValuteCBRList()) {
//                        if (valuteCBR.getId().equals(idCoin[k])) {
//
//                            mani_2[k] = valuteCBR.getValue();
//                            k++;
//                        }
//                    }
//                }
//            }
//
//
//            @Override
//            public void onFailure(Call<Parser> call_2, Throwable t) { }
//        });
//
//    }
//
//            @Override
//            public void onResponse(Call<Parser> call, Response<Parser> response) {
//                if (response.isSuccessful()) {
//
//                    Parser rss = response.body();
//                    LinearLayout linLayout = (LinearLayout) findViewById(R.id.linLayout);
//                    LayoutInflater ltInflater = getLayoutInflater();
//
//                    int k = 0;
//                    for (ValuteCBR valuteCBR : rss.getValuteCBRList()) {
//                        if (valuteCBR.getId().equals(idCoin[k])) {
//
//                            View item = ltInflater.inflate(R.layout.activity_main, linLayout, false);
//                            galerey = item.findViewById(R.id.image);
//                            nameCoin = item.findViewById(R.id.name);
//                            nominalCoin = item.findViewById(R.id.nominal);
//                            maniCoin = item.findViewById(R.id.mani);
//                            mani24h = item.findViewById(R.id.day);
//                            mani7d = item.findViewById(R.id.week);
//                            nameCoin.setText(valuteCBR.getName());
//                            nominalCoin.append(" " + valuteCBR.getNominal());
//                            maniCoin.append(" " + String.valueOf(valuteCBR.getValue()));
//                            double h,d;
//                            h= 100 - (Double.valueOf(mani_1[k].replaceAll(",","."))/(Double.valueOf(valuteCBR.getValue().replaceAll(",","."))/100));
//                            d= 100 - (Double.valueOf(mani_2[k].replaceAll(",","."))/(Double.valueOf(valuteCBR.getValue().replaceAll(",","."))/100));
//                            if(h < 0) {
//                                mani24h.append(" " + String.valueOf(h).substring(0, 5));
//                                mani24h.setTextColor(Color.RED);
//                            }
//                            if(d < 0) {
//                                mani7d.append(" " + String.valueOf(d).substring(0, 5));
//                                mani7d.setTextColor(Color.RED);
//                            }
//                            if(h > 0) {
//                                mani24h.append(" +" + String.valueOf(h).substring(0, 4));
//                                mani24h.setTextColor(Color.GREEN);
//                            }
//                            if(d > 0) {
//                                mani7d.append(" +" + String.valueOf(d).substring(0, 4));
//                                mani7d.setTextColor(Color.GREEN);
//                            }
//                            if(h == 0) {
//                                mani24h.append(" " + String.valueOf(h).substring(0, 4));
//                                mani24h.setTextColor(Color.GRAY);
//                            }
//                            if(d == 0) {
//                                mani7d.append(" " + String.valueOf(d).substring(0, 4));
//                                mani7d.setTextColor(Color.GRAY);
//                            }
//                            if(k == 0)
//                                galerey.setImageResource(R.drawable.sterlink);
//                            if(k == 1)
//                                galerey.setImageResource(R.drawable.dollar);
//                            if(k == 2)
//                                galerey.setImageResource(R.drawable.evro);
//                            if(k == 3)
//                                galerey.setImageResource(R.drawable.yuan);
//
//                            item.getLayoutParams().width = LinearLayout.LayoutParams.MATCH_PARENT;
//                            linLayout.addView(item);
//
//                            k++;
//                        }
//                    }
//                }
//            }
//
//
//            @Override
//            public void onFailure(Call<Parser> call, Throwable t) {
//                LinearLayout linLayout = (LinearLayout) findViewById(R.id.linLayout);
//                LayoutInflater ltInflater = getLayoutInflater();
//
//                View item = ltInflater.inflate(R.layout.activity_error, linLayout, false);
//                text = item.findViewById(R.id.text_item);
//                text.setText("Error: \n" + "Пожауйста, проверьте Ваше подключение к сети и перезапустите приложение." +
//                        "Если у Вас не получается решить проблему, пишите: Kirzak899@gmail.com \n" + "С уважением.");
//                item.getLayoutParams().width = LinearLayout.LayoutParams.MATCH_PARENT;
//                linLayout.addView(item);
//            }
//
//}



