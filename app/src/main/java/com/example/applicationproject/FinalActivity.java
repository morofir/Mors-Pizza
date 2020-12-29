package com.example.applicationproject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class FinalActivity extends AppCompatActivity {
    EditText amount;

    TextView total,order_dtl, sum_tv;
    Button place_order,ok_btn,drink_btn;
    EditText name_et,address_et,phone_et,drink_num;
    Spinner drink_sp;
    ArrayList<String> drinks = new ArrayList<String>();
    int num_drinks=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);

        total = findViewById(R.id.total);
        order_dtl = findViewById(R.id.Order_details);
        place_order = findViewById(R.id.place_order);
        name_et = findViewById(R.id.name_et);
        address_et = findViewById(R.id.address_et);
        phone_et = findViewById(R.id.phone_et);
        drink_num = findViewById(R.id.drinks_number);
        ok_btn = (Button) findViewById(R.id.ok_btn);
        sum_tv = findViewById(R.id.sumTextView);



        Bundle bundle=getIntent().getExtras();
        int value=bundle.getInt("amount");  //get the values of the cart from intent
        total.setText(String.valueOf("Total: "+value+" $"));



        ok_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int amount=0;
                String drinksNum = drink_num.getText().toString();

                if (drinksNum.equals("")) {
                    Toast.makeText(FinalActivity.this, R.string.nodrinks_toast, Toast.LENGTH_SHORT).show();
                } else {
                    amount = Integer.parseInt(drinksNum);
                    LinearLayout spinnersLayout = findViewById(R.id.spinners_layout);
                    spinnersLayout.removeAllViews();

                    for (int i = 0; i < amount ; i++) {
                        drink_sp = new Spinner(FinalActivity.this);

                        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
                        drink_sp.setLayoutParams(layoutParams);

                        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(FinalActivity.this, R.array.drinks_array, android.R.layout.simple_spinner_item);
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        drink_sp.setAdapter(adapter);

                        drink_sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                String drink = "";
                                drink = drink_sp.getSelectedItem().toString();

                                if (!(drink.equals(R.string.choose))) {
                                    System.out.println(drink);
                                    drinks.add(drink);
                                    for (int i=0; i < drinks.size(); i++) {
                                        System.out.println(drinks.get(i));
                                    }
                                    num_drinks = drinks.size();
                                }
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });

                        spinnersLayout.addView(drink_sp);
                    }
                }
            }
        });



        place_order.setOnClickListener(new View.OnClickListener()
        {
            int count = 0;

            @Override
            public void onClick (View v){
                count++;

                //retrieve the order information
                String info, toppings;
                if (savedInstanceState == null) {
                    Bundle extras = getIntent().getExtras();
                    if (extras == null) {
                        info = null;
                        toppings = null;

                    } else {
                        info = extras.getString("INFO");
                        toppings = extras.getString("TOPPINGS");

                    }
                } else {
                    info = (String) savedInstanceState.getSerializable("INFO");
                    toppings = (String) savedInstanceState.getSerializable("TOPPINGS");
                }

                String name = name_et.getText().toString();
                String address = address_et.getText().toString();
                String phone = phone_et.getText().toString();

                if (name.matches("")) {
                    Toast.makeText(FinalActivity.this, "You did not enter a name", Toast.LENGTH_SHORT).show();
                    count = 0;
                    return;
                }
                if (address.matches("")) {
                    Toast.makeText(FinalActivity.this, "You did not enter a address", Toast.LENGTH_SHORT).show();
                    count = 0;
                    return;
                }
                if (phone.matches("")) {
                    Toast.makeText(FinalActivity.this, "You did not enter a phone", Toast.LENGTH_SHORT).show();
                    count = 0;
                    return;
                }

                order_dtl.setSingleLine(false);
                order_dtl.setText("Hello " + name_et.getText() + " you ordered: \n" + info);
                order_dtl.append("\nAdditional Toppings: (Regular pizzas only) \n" + toppings + "\n");
                order_dtl.append("Address: " + address_et.getText() + "\n\nPhone: " + phone_et.getText());
                order_dtl.append("\nNumber of drinks: "+ (num_drinks-1));

                order_dtl.append("\n\nCLICK PLACE ORDER AGAIN TO CONFIRM ORDER");


                if (count > 1) {
                    AlertDialog alertDialog = new AlertDialog.Builder(FinalActivity.this).create();
                    alertDialog.setTitle("Get Ready to eat :)");
                    alertDialog.setMessage("Your order has been sent!!! \nExpected delivery time ~1 hour.");
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();
                }

                if (count > 2) {
                    new AlertDialog.Builder(FinalActivity.this)
                            .setMessage("YOU ALREADY ORDERED, Do you want to exit?")
                            .setCancelable(false)
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    FinalActivity.super.onBackPressed();
                                }
                            })
                            .setNegativeButton("No", null)
                            .show();
                }
            }
        });
    }
}

