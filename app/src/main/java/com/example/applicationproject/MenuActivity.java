package com.example.applicationproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.io.Serializable;
import java.nio.IntBuffer;

public class MenuActivity extends AppCompatActivity {
    Button btnCancel, btnAdd;
    RadioGroup size;
    CheckBox corn,pineapple,olives,feta,anchovies,onions,tomato,peppers,cheese,mushrooms;
    CheckBox s_marinera, s_fungi,s_margarita,s_vegi,s_pepperoni,s_greek;
    int totalAmount=0,adOns=0;
    StringBuilder info = new StringBuilder(200);  //order information
    StringBuilder toppings = new StringBuilder(100);  //order information





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        size = findViewById(R.id.radioGroup_pizzaSize_id);
        corn = (CheckBox)findViewById(R.id.checkBox_corn);
        pineapple = (CheckBox)findViewById(R.id.checkBox_pineapple);
        olives = (CheckBox)findViewById(R.id.checkBox_olives);
        feta = (CheckBox)findViewById(R.id.checkBox_bulgarian);
        anchovies = (CheckBox)findViewById(R.id.checkBox_anchovies);
        onions = (CheckBox)findViewById(R.id.checkBox_Onion);
        tomato = (CheckBox)findViewById(R.id.checkBox_Tomato);
        peppers = (CheckBox)findViewById(R.id.checkBox_peppers);
        mushrooms = (CheckBox)findViewById(R.id.checkBox_Mushrooms);
        cheese = (CheckBox)findViewById(R.id.checkBox_Cheese);

        btnCancel = findViewById(R.id.button_Cancel_Pizza_id);
        btnAdd = findViewById(R.id.button_AddToCart_Pizza_id);

        s_greek = (CheckBox)findViewById(R.id.greek_pizza);
        s_marinera = (CheckBox)findViewById(R.id.marinera_pizza);
        s_fungi = (CheckBox)findViewById(R.id.fungi_pizza);
        s_margarita = (CheckBox)findViewById(R.id.margarita_pizza);
        s_pepperoni = (CheckBox)findViewById(R.id.pepperoni_pizza);
        s_vegi = (CheckBox)findViewById(R.id.vegi_pizza);

        Bundle bundle=getIntent().getExtras();
        if(bundle!=null){
            int value=bundle.getInt("special");  //get the values of the cart from intent
            order_function(value);
        }


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int checkedId = size.getCheckedRadioButtonId();
                //if special pizza no regular pizza

                if(checkedId == -1) {
                    if (isSpecialChecked() == false) {
                        //No selection
                        totalAmount =0;
                        Message.messeage(getApplicationContext(),"Please select Pizza size or Special Pizza");
                    }
                    else{
                        isSpecial();
                        openFinalActivity();
                    }

                }
                else{
                    //or special or regular size selected, function that sums the amount
                    findRadioButton(checkedId);
                    addOns();       //pizza addons updating
                    isSpecial();    //special pizza add

                    openFinalActivity();
                }
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                size.clearCheck();
                corn.setChecked(false);
                pineapple.setChecked(false);
                anchovies.setChecked(false);
                feta.setChecked(false);
                olives.setChecked(false);
                tomato.setChecked(false);
                cheese.setChecked(false);
                mushrooms.setChecked(false);
                peppers.setChecked(false);
                onions.setChecked(false);

                s_vegi.setChecked(false);
                s_pepperoni.setChecked(false);
                s_marinera.setChecked(false);
                s_margarita.setChecked(false);
                s_fungi.setChecked(false);
                s_greek.setChecked(false);

            }
        });
    }

    private void isSpecial() {
        if (s_greek.isChecked()) {
            totalAmount = totalAmount + 17;
            info.append("Greek Pizza \n");
        }

        if (s_fungi.isChecked()) {
            totalAmount = totalAmount + 15;
            info.append("Fungi Pizza \n");
        }

        if (s_margarita.isChecked()) {
            totalAmount = totalAmount + 15;
            info.append("Margarita Pizza \n");
        }
        if (s_marinera.isChecked()) {
            totalAmount = totalAmount + 16;
            info.append("Marinera Pizza \n");
        }
        if (s_pepperoni.isChecked()) {
            totalAmount = totalAmount + 19;
            info.append("Pepperoni Pizza \n");
        }

        if (s_vegi.isChecked()){
            totalAmount = totalAmount + 15;
            info.append("Vegetarian Pizza \n");
        }
    }

    private boolean isSpecialChecked() {
       return ((s_greek).isChecked() || (s_marinera).isChecked() ||(s_fungi).isChecked() ||
               (s_margarita).isChecked() ||(s_pepperoni).isChecked() ||(s_vegi).isChecked());

    }

    //this function checks what special pizza was ordered from the Gallery intent
    private void order_function(int value) {
        switch(value){
            case 1:
                s_greek.setChecked(true);
                break;
            case 2:
                s_margarita.setChecked(true);
                break;
            case 3:
                s_fungi.setChecked(true);
                break;
            case 4:
                s_pepperoni.setChecked(true);
                break;
            case 5:
                s_marinera.setChecked(true);
                break;
            case 6:
                s_vegi.setChecked(true);
                break;

        }
    }

    private void openFinalActivity() {
        Intent intent = new Intent(this,FinalActivity.class);
        intent.putExtra("amount",totalAmount);       //pass the total amount with the itent
        intent.putExtra("INFO", (Serializable) info);
        intent.putExtra("TOPPINGS", (Serializable) toppings);

        startActivity(intent);
    }
    private void addOns() {
        //check if there is any addition to the pizza, updating the amount accordingly
        if(corn.isChecked()) {
            totalAmount = totalAmount + 1;
            toppings.append("Corn, ");
        }
        if(feta.isChecked()) {
            totalAmount = totalAmount + 1;
            toppings.append("Feta cheese\n");
        }
        if(pineapple.isChecked()) {
            totalAmount = totalAmount + 1;
            toppings.append("Pineapple\n");

        }
        if(olives.isChecked()) {
            totalAmount = totalAmount + 1;
            toppings.append("Olives\n");

        }
        if(anchovies.isChecked()){
            totalAmount = totalAmount+1;
            toppings.append("Anchovies\n");
        }
        if(onions.isChecked())
            toppings.append("Onions\n");

        if(tomato.isChecked())
            toppings.append("tomato\n");

        if(peppers.isChecked())
            toppings.append("peppers\n");

        if(mushrooms.isChecked())
            toppings.append("mushrooms\n");

        if(cheese.isChecked())
            toppings.append("Cheese\n");


    }

    private void findRadioButton(int checkedId) {
        switch(checkedId){
            case R.id.radioButton_pizzaSmall_id:
                totalAmount = totalAmount + 5;
                info.append("Small Pizza \n");

                break;
            case R.id.radioButton_pizzaMedium_id:
                totalAmount = totalAmount + 10;
                info.append("Medium Pizza \n");

                break;
            case R.id.radioButton_pizzaLarge:
                totalAmount = totalAmount + 15;
                info.append("Large Pizza \n");
                break;
        }
    }

}