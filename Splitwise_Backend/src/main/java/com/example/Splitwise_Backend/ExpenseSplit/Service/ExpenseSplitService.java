package com.example.Splitwise_Backend.ExpenseSplit.Service;

import com.example.Splitwise_Backend.ExpenseSplit.Entity.ExpenseSplit;
import com.example.Splitwise_Backend.PaymentSplit.Entity.PaymentSplit;

import java.util.List;

public interface ExpenseSplitService {
    public ExpenseSplit saveExpenseSplit(int payerId, int payedToId, int groupId, int expenseId, float amountPaid);
    public String deleteExpenseSplit(int expenseId);
    public List<ExpenseSplit> amountToBeRecievedByYou(int userId);
    public List<ExpenseSplit> amountToBeGivenByYou(int userId);
    public List<ExpenseSplit> expenseSplitOfAllGroups(int groupId);
    public List<ExpenseSplit> expenseSplitOfParticularExpenseId(int expenseId);
}
