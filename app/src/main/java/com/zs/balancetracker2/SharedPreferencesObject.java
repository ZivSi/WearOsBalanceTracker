package com.zs.balancetracker2;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesObject {
    private final SharedPreferences sharedPreferences;
    private final SharedPreferences.Editor editor;
    static final String SALARY_KEY = "salary";
    static final String EXPENSES_KEY = "expenses";
    static final String BALANCE_KEY = "balance";
    static final String BALANCE_FILE = "balanceFile.txt";

    private final Context context;

    public SharedPreferencesObject(Context context) {
        this.context = context;

        sharedPreferences = context.getSharedPreferences(BALANCE_FILE, MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public boolean noBalance() {
        return getBalance() == -1;
    }

    public int getBalance() {
        return sharedPreferences.getInt(BALANCE_KEY, -1);
    }

    public void saveBalance(int balance) {
        editor.putInt(BALANCE_KEY, balance);
        editor.apply();
        editor.commit();
    }

    public void updateBalance(int amount, boolean isAdd) {
        int currentBalance = getBalance();

        if (isAdd) {
            currentBalance += amount;
        } else {

            decreaseExpensesLeft(amount);

            currentBalance -= amount;
        }
        saveBalance(currentBalance);
    }

    public void clearBalance() {
        editor.clear();
        editor.apply();
        editor.commit();
    }

    public int getExpensesLeft() {
        return sharedPreferences.getInt(EXPENSES_KEY, 0);
    }

    public void decreaseExpensesLeft(int expenses) {
        int currentExpensesLeft = getExpensesLeft();
        currentExpensesLeft -= expenses;

        currentExpensesLeft = Math.max(currentExpensesLeft, 0);

        saveExpensesLeft(currentExpensesLeft);
    }

    public void saveExpensesLeft(int expenses) {
        editor.putInt(EXPENSES_KEY, expenses);
        editor.apply();
        editor.commit();
    }

    public int getSalary() {
        return sharedPreferences.getInt(SALARY_KEY, 0);
    }

    public void saveSalary(int salary) {
        editor.putInt(SALARY_KEY, salary);
        editor.apply();
        editor.commit();
    }
}
