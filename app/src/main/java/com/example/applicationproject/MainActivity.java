package com.example.applicationproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout d1;
    private ActionBarDrawerToggle abdt;
    private Button call_btn;
    ImageView main_iv;

    ImageView menu2,specials;
    androidx.cardview.widget.CardView menu3;
    androidx.cardview.widget.CardView specials2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        call_btn = (Button) findViewById(R.id.buttonCall);

        //on clicking the call button
        call_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:0549481884"));
                startActivity(callIntent);

                if (ActivityCompat.checkSelfPermission(MainActivity.this,
                        Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                startActivity(callIntent);
            }
        });


        menu3 = findViewById(R.id.menu_card);
        specials2 = findViewById(R.id.specials_card);

        menu2 = findViewById(R.id.menu_iv);
        specials = findViewById(R.id.special_iv);
        main_iv = findViewById(R.id.mors_pic);



        d1= (DrawerLayout)findViewById(R.id.dl);
        abdt = new ActionBarDrawerToggle(this,d1,R.string.Open,R.string.Close);
        abdt.setDrawerIndicatorEnabled(true);

        d1.addDrawerListener(abdt);
        abdt.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        NavigationView nav_view = (NavigationView) findViewById(R.id.nav_view);

        //navigation bar function
        nav_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener(){
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id= item.getItemId();
                if(id == R.id.nav_about){
                    Intent AboutIntent  = new Intent(MainActivity.this,AboutActivity.class);
                    startActivity(AboutIntent);
                    return false;
                }
                else if(id ==R.id.nav_menu){
                    Intent MenuIntent  = new Intent(MainActivity.this,MenuActivity.class);
                    startActivity(MenuIntent);
                    return false;
                }

                else if(id ==R.id.nav_special){
                    Intent MenuIntent  = new Intent(MainActivity.this,GalleryActivity.class);
                    startActivity(MenuIntent);
                    return false;
                }
                return true;
            }
        });


        main_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent MenuIntent  = new Intent(MainActivity.this,AboutActivity.class);
                startActivity(MenuIntent);
            }
        });

        menu2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent MenuIntent  = new Intent(MainActivity.this,MenuActivity.class);
                startActivity(MenuIntent);

            }
        });
        specials.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent  = new Intent(MainActivity.this,GalleryActivity.class);
                startActivity(galleryIntent);
            }
        });
        menu3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent MenuIntent  = new Intent(MainActivity.this,MenuActivity.class);
                startActivity(MenuIntent);

            }
        });
        specials2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent  = new Intent(MainActivity.this,GalleryActivity.class);
                startActivity(galleryIntent);
            }
        });


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return abdt.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }
}