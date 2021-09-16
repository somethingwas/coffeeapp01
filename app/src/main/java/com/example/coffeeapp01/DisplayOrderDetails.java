package com.example.coffeeapp01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class DisplayOrderDetails extends AppCompatActivity {
    String message;
    String name;
    String price;
    coffeeDBHandler dbHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_order_details);
        dbHandler = new coffeeDBHandler(this, null, null, 1);
        Intent intent = getIntent();
        message = intent.getStringExtra("message");
        name = intent.getStringExtra("name");
        price = intent.getStringExtra("price");
        TextView textView = findViewById(R.id.textView2);
        textView.setText(message);
    }

    public void sendEmail(View view) {
        String[] address = {"haiou@gmail.com", "s3899845@student.rmit.edu.au"};
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_EMAIL, address);
        intent.putExtra(Intent.EXTRA_SUBJECT, "Coffee Order");
        intent.putExtra(Intent.EXTRA_TEXT, message);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public void addButtonClicked(View view) {
Order order = new Order(name, Integer.parseInt(price));
dbHandler.addOrder(order);
        Toast.makeText(getApplicationContext(),"Data Saved!",Toast.LENGTH_SHORT).show();
    }

    public void salesReport(View view) {
String dbString = dbHandler.databaseToString();
Intent salesIntent = new Intent(this,DisplaySalesDetails.class);
salesIntent.putExtra("db", dbString);
startActivity(salesIntent);
    }

    public void reorder(View view) {
        String dbString = dbHandler.databaseToString();
        Intent salesIntent = new Intent(this,DisplaySalesDetails.class);
        salesIntent.putExtra("db", dbString);
        startActivity(salesIntent);
    }
}
