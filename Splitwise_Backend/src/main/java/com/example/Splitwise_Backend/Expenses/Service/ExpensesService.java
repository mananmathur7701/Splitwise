package com.example.Splitwise_Backend.Expenses.Service;\
import com.example.Splitwise_Backend.DTO.ExpenseInfoDTO;
import com.example.Splitwise_Backend.Expenses.Entity.Expenses;
import java.util.List;

public interface ExpensesService {

//    public Expenses addExpense(int groupId,float amountPaid,String comment);
    public Expenses addExpense(ExpenseInfoDTO expenseInfoDTO);
    public String deleteExpense(int expenseId);
    public List<Expenses> showAllExpenses(int groupId);
    public Expenses editExpense(int ExpenseId);
    public Expenses expenseInfoById(int expenseId);
}
