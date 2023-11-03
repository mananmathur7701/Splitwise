package com.example.Splitwise_Backend.ExpenseSplit.Service;

import com.example.Splitwise_Backend.ExpenseSplit.Entity.ExpenseSplit;
import com.example.Splitwise_Backend.PaymentSplit.Entity.PaymentSplit;

public interface ExpenseSplitService {
    public ExpenseSplit saveExpenseSplit(int payerId, int payedToId, int groupId, int expenseId, float amountPaid);
}
