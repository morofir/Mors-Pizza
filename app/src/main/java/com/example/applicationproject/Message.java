package com.example.applicationproject;

import android.content.Context;
import android.widget.Toast;

public class Message {
    public static void messeage(Context context,String msg){
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }
}
