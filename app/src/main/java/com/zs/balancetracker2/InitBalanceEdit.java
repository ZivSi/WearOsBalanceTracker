package com.zs.balancetracker2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class InitBalanceEdit extends AppCompatActivity {
    private EditText balance, salary, expenses;
    private SharedPreferencesObject sharedPreferencesObject;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_init_balance_edit);

        balance = findViewById(R.id.balanceEditText);
        salary = findViewById(R.id.salaryEditText);
        expenses = findViewById(R.id.expensesEditText);

        sharedPreferencesObject = new SharedPreferencesObject(this);

        if (!sharedPreferencesObject.noBalance()) {
            balance.setText(String.valueOf(sharedPreferencesObject.getBalance()));
        }

        salary.setText(String.valueOf(sharedPreferencesObject.getSalary()));
        expenses.setText(String.valueOf(sharedPreferencesObject.getExpensesLeft()));
    }

    public void saveAll(View view) {
        String balanceString = balance.getText().toString();
        String salaryString = salary.getText().toString();
        String expensesString = expenses.getText().toString();

        try {
            sharedPreferencesObject.saveBalance(Integer.parseInt(balanceString));
        } catch (NumberFormatException ignored) {
        }

        try {
            sharedPreferencesObject.saveSalary(Integer.parseInt(salaryString));
        } catch (NumberFormatException ignored) {
        }

        try {
            sharedPreferencesObject.saveExpensesLeft(Integer.parseInt(expensesString));
        } catch (NumberFormatException ignored) {
        }

        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
