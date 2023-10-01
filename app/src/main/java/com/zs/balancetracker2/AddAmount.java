package com.zs.balancetracker2;

import static com.zs.balancetracker2.MainActivity.IS_ADD;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AddAmount extends AppCompatActivity {
    private SharedPreferencesObject sharedPrefrencesObject;
    private boolean isAdd;
    private TextView actionTextView;

    private ImageView saveBalanceImageView;
    private EditText amountEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_amount);

        amountEditText = findViewById(R.id.amountEditText);
        saveBalanceImageView = findViewById(R.id.updateBalanceImageView);
        actionTextView = findViewById(R.id.actionTextView);
        sharedPrefrencesObject = new SharedPreferencesObject(this);

        isAdd = getIntent().getBooleanExtra(IS_ADD, true);

        saveBalanceImageView.setImageResource(isAdd ? R.drawable.ic_baseline_add : R.drawable.ic_baseline_remove);
        actionTextView.setText(isAdd ? "Add Amount" : "Remove Amount");
    }

    public void updateBalance(View view) {
        int balanceValue = Integer.parseInt(amountEditText.getText().toString());
        sharedPrefrencesObject.updateBalance(balanceValue, isAdd);

        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}