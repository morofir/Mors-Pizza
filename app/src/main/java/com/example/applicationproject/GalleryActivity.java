package com.example.applicationproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ViewFlipper;

public class GalleryActivity extends AppCompatActivity {
        private ViewFlipper viewFlipper;
        Button order_btn1,order_btn2,order_btn3,order_btn4,order_btn5,order_btn6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        viewFlipper = findViewById(R.id.view_flipper);
        order_btn1 = findViewById(R.id.order_btn1);
        order_btn2 = findViewById(R.id.order_btn2);
        order_btn3 = findViewById(R.id.order_btn3);
        order_btn4 = findViewById(R.id.order_btn4);
        order_btn5 = findViewById(R.id.order_btn5);
        order_btn6 = findViewById(R.id.order_btn6);


//        viewFlipper.setFlipInterval(3000); //3 seconds
//        viewFlipper.startFlipping();

        order_btn1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GalleryActivity.this, MenuActivity.class);
                intent.putExtra("special",1);       //pass different value for each special pizza
                startActivity(intent);
            }
        });
        order_btn2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GalleryActivity.this, MenuActivity.class);
                intent.putExtra("special",2);       //pass different value for each special pizza
                startActivity(intent);
            }
        });
        order_btn3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GalleryActivity.this, MenuActivity.class);
                intent.putExtra("special",3);       //pass different value for each special pizza
                startActivity(intent);
            }
        });
        order_btn4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GalleryActivity.this, MenuActivity.class);
                intent.putExtra("special",4);       //pass different value for each special pizza
                startActivity(intent);
            }
        });
        order_btn5.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GalleryActivity.this, MenuActivity.class);
                intent.putExtra("special",5);       //pass different value for each special pizza
                startActivity(intent);
            }
        });
        order_btn6.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GalleryActivity.this, MenuActivity.class);
                intent.putExtra("special",6);       //pass different value for each special pizza
                startActivity(intent);
            }
        });
    }



    public void previousView(View v){
        viewFlipper.setInAnimation(this,R.anim.slide_in_right);
        viewFlipper.setOutAnimation(this,R.anim.slide_out_left);
        viewFlipper.showPrevious();
    }

    public void NextView(View v){
        //android defaults
        viewFlipper.setInAnimation(this,android.R.anim.slide_in_left);
        viewFlipper.setOutAnimation(this,android.R.anim.slide_out_right);
        viewFlipper.showNext();
    }


}