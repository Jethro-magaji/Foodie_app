package com.example.bettafoods;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;


public class MainActivity<addMeatCheckBox> extends AppCompatActivity {
    int quantity = 1;
    private Object addMeatCheckBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void increment (View view) {
        if (quantity == 10){
            Toast.makeText(this,"Sorry you cannot order above 10",Toast.LENGTH_SHORT).show();
            return;
        }
        quantity = quantity + 1;
        display((quantity));
    }

    public void decrement (View view) {
        if (quantity == 1){
            Toast.makeText(this,"Sorry you cannot order below 1",Toast.LENGTH_SHORT).show();
            return;
        }
        quantity = quantity - 1;
        display(quantity);
    }




    public void submitOrder(View view) {
        EditText nameEditText = (EditText) findViewById(R.id.name);
        String name = nameEditText.getText().toString();

        CheckBox meatCheckBox = (CheckBox) findViewById(R.id.checkBox2);
        boolean hasMeatCheckBox = meatCheckBox.isChecked();

        CheckBox kunnuCheckBox = (CheckBox) findViewById(R.id.checkbox);
        boolean hasKunnuCheckBox = meatCheckBox.isChecked();

        int price = calculatePrice(hasKunnuCheckBox,hasMeatCheckBox);
        String priceMessage = createOrderSummary(name,price, hasKunnuCheckBox,hasMeatCheckBox);


        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto: support@jilstores.com.ng"));
        intent.putExtra(intent.EXTRA_SUBJECT, "Betta Foods"+ name);
        intent.putExtra(intent.EXTRA_TEXT,priceMessage);
        if (intent.resolveActivity(getPackageManager()) !=null){
            startActivity(intent);
        }

    }



    private int calculatePrice(boolean addMeatCheckBox,boolean addKunnuCheckBox) {
        int basePrice = 5;
        if (addMeatCheckBox){
            basePrice = basePrice + 1;
        }

        if (addKunnuCheckBox){
            basePrice = basePrice + 1;
        }

        return quantity * basePrice;
    }

    private String createOrderSummary(String name,int price, Boolean hasKunnuCheckBox,Boolean hasMeatCheckBox){
        String priceMessage = "Name: name";
        priceMessage += "\n Meat ?" + addMeatCheckBox; 
        priceMessage +=  "\n Price;Quantity:" + quantity;
        priceMessage +=  "\nTotal $"+ price;
        priceMessage +=  "\n Thank You!";
        return priceMessage;
    }

    public void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.textView3);
        quantityTextView.setText("" + number);
    }


}