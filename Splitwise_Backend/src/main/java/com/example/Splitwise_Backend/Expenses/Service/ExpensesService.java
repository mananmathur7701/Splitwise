package com.example.Splitwise_Backend.Expenses.Service;

import com.example.Splitwise_Backend.Expenses.Entity.Expenses;

public interface ExpensesService {

    public int addExpense();
    public String deleteExpense();
    public Expenses showAllExpenses();
    public Expenses editExpense();
}
