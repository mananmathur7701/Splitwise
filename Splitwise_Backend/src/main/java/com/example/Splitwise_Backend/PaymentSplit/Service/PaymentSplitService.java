package com.example.Splitwise_Backend.PaymentSplit.Service;

import com.example.Splitwise_Backend.PaymentSplit.Entity.PaymentSplit;

public interface PaymentSplitService {
    public PaymentSplit savePaymentSplit(int userId,int expenseId,double amountPaid);
}
