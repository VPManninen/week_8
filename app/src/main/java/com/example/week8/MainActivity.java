package com.example.week8;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import java.io.IOException;


public class MainActivity extends AppCompatActivity {
    TextView display;
    TextView totalMoney;
    TextView addMoneyAM;
    Button add;
    Button returnM;
    Button buy;
    SeekBar selectAmount;
    double amount;
    Spinner sizeList;
    Spinner drinkList;
    Context context = null;
    double purchaseValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        display = findViewById(R.id.Display);
        add = findViewById(R.id.AddMoney);
        returnM = findViewById(R.id.RetMoney);
        buy = findViewById(R.id.buyBottle);
        selectAmount = findViewById(R.id.moneyAmount);
        totalMoney = findViewById(R.id.moneyView);
        addMoneyAM = findViewById(R.id.addAmount);
        sizeList = findViewById(R.id.size);
        drinkList = findViewById(R.id.type);
        context = MainActivity.this;

        selectAmount.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                amount = selectAmount.getProgress() * 0.1;
                addMoneyAM.setText(String.format("%.2f €", amount));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        ArrayAdapter<String> nameAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_dropdown_item, BottleDispenser.getInstance().getBottles());
        nameAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        drinkList.setAdapter(nameAdapter);

        ArrayAdapter<Double> sizeAdapter = new ArrayAdapter<Double>(context, android.R.layout.simple_spinner_dropdown_item, BottleDispenser.getInstance().getSizes());
        sizeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sizeList.setAdapter(sizeAdapter);
    }

    public void addSelected(View v) {
        BottleDispenser.getInstance().addMoney(amount);
        display.setText(String.format("%.2f €, added successfully.", amount));
        selectAmount.setProgress(0);
        totalMoney.setText(String.format("%.2f €", BottleDispenser.getInstance().getMoney()));
    }

    public void returnAll(View v) {
        display.setText(String.format("%.2f €, returned successfully.", BottleDispenser.getInstance().getMoney()));
        BottleDispenser.getInstance().returnMoney();
        totalMoney.setText(String.format("%.2f €", BottleDispenser.getInstance().getMoney()));
    }

    public void buyBottle(View v) {
        purchaseValue = BottleDispenser.getInstance().buyBottle(drinkList.getSelectedItem().toString(), Double.parseDouble(sizeList.getSelectedItem().toString()));
        switch((int) purchaseValue) {
            case(0):
                display.setText("Add more money to buy product.");
                break;
            case(-1):
                display.setText("No such product found.");
                break;
            default:
                display.setText("Successfully purchased: \n " + drinkList.getSelectedItem().toString() + " : " + sizeList.getSelectedItem().toString());
                printReceipt(purchaseValue);
        }
        totalMoney.setText(String.format("%.2f €", BottleDispenser.getInstance().getMoney()));
    }

    public void printReceipt(double price) {
        String receiptName = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss").format(Calendar.getInstance().getTime());
        try {
            BufferedWriter output = new BufferedWriter(new OutputStreamWriter(context.openFileOutput(receiptName + ".txt", Context.MODE_PRIVATE)));
            output.write("Purchase:\n" + drinkList.getSelectedItem() + " : " + sizeList.getSelectedItem() + "\n Total price: " + String.valueOf(purchaseValue));
            output.close();
        } catch (IOException e){
            System.out.println("IOError when printing receipt");
        }
    }
}