package com.example.apcp;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Map;

import static android.content.ContentValues.TAG;

class MySimpleAdapter extends SimpleAdapter {

    public MySimpleAdapter(Context context, List<? extends Map<String, ?>> data, int resource, String[] from, int[] to) {
        super(context, data, resource, from, to);
    }

    @Override
    public void setViewText(TextView v, String text) {
        super.setViewText(v, text);
        if(v.getId() == R.id.day){
            if(text.substring(0,3).equals(" - "))
                v.setTextColor(Color.RED);
            if(text.substring(0,3).equals(" + "))
                v.setTextColor(Color.GREEN);
            if(!(text.substring(0,3).equals(" - ") && !(text.substring(0,3).equals(" + "))))
                v.setTextColor(Color.GRAY);
        }
        if(v.getId() == R.id.week){
            if(text.substring(0,3).equals(" - "))
            v.setTextColor(Color.RED);
            if(text.substring(0,3).equals(" + "))
                v.setTextColor(Color.GREEN);
            if(!(text.substring(0,3).equals(" - ") && !(text.substring(0,3).equals(" + "))))
                v.setTextColor(Color.GRAY);
        }
    }
}
