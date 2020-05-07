package com.example.apcp;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import androidx.appcompat.app.AppCompatActivity;

public class Activity_internet extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internet);
        Intent intent = getIntent();

        String link = intent.getStringExtra("link");

        WebView webView = findViewById(R.id.web);

        if(link.substring(0,8).equals("/Queries")) {
            webView.loadUrl("https://docs.google.com/gview?embedded=true&url=www.cbr.ru" + link);
        }else {
            webView.loadUrl("http://cbr.ru" + link);
        }
    }
}
