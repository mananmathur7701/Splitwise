package com.example.Splitwise_Backend.Expenses.Service;

import com.example.Splitwise_Backend.Expenses.DTO.expenseDTO;
import com.example.Splitwise_Backend.Expenses.Entity.Expenses;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ExpensesService {

    public Expenses addExpense(Expenses expenses);
    public String deleteExpense(int expenseId);
    public List<Expenses> showAllExpenses();
    public Expenses editExpense(int ExpenseId);
    public Expenses expenseInfoById(int expenseId);
    public List<Expenses> allExpensesOfGroup(int groupId);
}
