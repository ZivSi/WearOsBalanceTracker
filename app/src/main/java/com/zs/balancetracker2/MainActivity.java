package com.zs.balancetracker2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;

import com.zs.balancetracker2.databinding.ActivityMainBinding;

public class MainActivity extends Activity {
    public static final String IS_ADD = "isAdd";
    public static final char SHEKEL = '₪';
    private ActivityMainBinding binding;
    private TextView balanceTextView, predictionTextView, expensesLeftTextView;

    private SharedPreferencesObject sharedPrefrencesObject;

    private CardView addExpenseCardView;
    private CardView removeExpenseCardView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        balanceTextView = binding.balanceValueTextView;
        addExpenseCardView = binding.addBalanceCardView;
        removeExpenseCardView = binding.removeBalanceCardView;
        expensesLeftTextView = binding.expensesLeftTextView;

        sharedPrefrencesObject = new SharedPreferencesObject(this);

        initListeners();
        setPrediction();
        setBezelScroll();

        if (sharedPrefrencesObject.noBalance()) {
            setBalance();
        }

        balanceTextView.setText(String.valueOf(sharedPrefrencesObject.getBalance()) + SHEKEL);
    }

    private void setPrediction() {
        predictionTextView = findViewById(R.id.nextMonthPredictionTextView);

        int salary = sharedPrefrencesObject.getSalary();
        int balance = sharedPrefrencesObject.getBalance();
        int expensesLeft = sharedPrefrencesObject.getExpensesLeft();

        predictionTextView.setText("Next month prediction: " + (salary - expensesLeft + balance) + SHEKEL);
        expensesLeftTextView.setText("Expenses left: " + expensesLeft + SHEKEL);
    }

    private void setBalance() {
        Toast.makeText(this, "Please set your balance", Toast.LENGTH_LONG).show();
        startActivity(new Intent(this, InitBalanceEdit.class));

        finish();
    }

    private void setBezelScroll() {
        findViewById(R.id.scrollView).requestFocus();
    }

    private void initListeners() {
        addExpenseCardView.setOnClickListener(v -> {
            Intent intent = new Intent(this, AddAmount.class);
            intent.putExtra(IS_ADD, true);
            startActivity(intent);

            finish();
        });

        removeExpenseCardView.setOnClickListener(v -> {
            Intent intent = new Intent(this, AddAmount.class);
            intent.putExtra(IS_ADD, false);
            startActivity(intent);

            finish();
        });
    }

    public void setBalanceProperties(View view) {
        startActivity(new Intent(this, InitBalanceEdit.class));
        finish();
    }
}