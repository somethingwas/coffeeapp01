package com.example.coffeeapp01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    //create variables
    int noOfcoffee = 0;
    int priceOfCoffee = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // method for submit0rder Button
    public void submit0rder(View view) {
        // call the methond display() is called when the order button is clicked
        //display(1);
        // get user name
        EditText nameField = findViewById(R.id.name_field);
        String name = nameField.getText().toString();
        // user wants whipped cream
        CheckBox whippedCreamCheckbox = findViewById(R.id.Whipped_cream_checkbox);
        boolean hasWhippedCream = whippedCreamCheckbox.isChecked();
        // user wants chocolate
        CheckBox Chocolatecheckbox = findViewById(R.id.chocolate_check_box);
        boolean hasChocolate = Chocolatecheckbox.isChecked();
        int price = calculatePrice(hasWhippedCream, hasChocolate);
        String message = createOrderSummary(name, price, hasWhippedCream, hasChocolate);

        //int totalPrice=noOfcoffee*priceOfCoffee;
        //TextView priceTextView = findViewById(R.id.price_text_view);
        //priceTextView.setText("Name " +name + "\n" + "Total: $"+ totalPrice + "\n" + "Thank you ! " );
        Intent intent = new Intent(this, DisplayOrderDetails.class);
        intent.putExtra("message", message);
        intent.putExtra("name", name);
        intent.putExtra("price", Integer.toString(price));
        startActivity(intent);
    }

    private String createOrderSummary(String name, int price, boolean addWhippedCream, boolean addChocolate) {
        String priceMessage = "Name " + name + "\n" +
                "Add Whipped Cream ? " + addWhippedCream + "\n" +
                "Add Chocolate ? " + addChocolate + "\n" +
                "Quantity : " + noOfcoffee + "\n" +
                "Total : $" + price + "\n" +
                "Thank you for ordering! ";
        return priceMessage;
    }

    private int calculatePrice(boolean hasWhippedCream, boolean hasChocolate) {
        int basePrice = 5;
        if (hasWhippedCream == true) {
            basePrice = basePrice + 1;
        }
        if (hasChocolate == true) {
            basePrice = basePrice + 2;
        }
        int finalPrice = basePrice * noOfcoffee;
        return finalPrice;
    }

    private void display(int i) {
        TextView quantityTextView = findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + i);

    }

    //increase no of coffee with increment
    public void increment(View view) {
        noOfcoffee = noOfcoffee + 1;
        if (noOfcoffee >= 10) {
            noOfcoffee = 10;
        }
        display(noOfcoffee);
    }

    //decrease no of coffee with decrement
    public void decrement(View view) {
        noOfcoffee = noOfcoffee - 1;
        if (noOfcoffee <= 1) {
            noOfcoffee = 1;
        }
        display(noOfcoffee);
    }

}