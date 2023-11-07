package com.example.Splitwise_Backend.Expenses.Service;
import com.example.Splitwise_Backend.DTO.ExpenseInfoDTO;
import com.example.Splitwise_Backend.Expenses.Entity.Expenses;
import java.util.List;

public interface ExpensesService {

//    public Expenses addExpense(int groupId,float amountPaid,String comment);
    public String addExpense(ExpenseInfoDTO expenseInfoDTO);
    public String deleteExpense(int expenseId);
    public List<Expenses> showAllGroupExpense(int groupId);
    public String editExpense(int expenseId, ExpenseInfoDTO expenseInfoDTO);
    public Expenses expenseInfoById(int expenseId);
}
