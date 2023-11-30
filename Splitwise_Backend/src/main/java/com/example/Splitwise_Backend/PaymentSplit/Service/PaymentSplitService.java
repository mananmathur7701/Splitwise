package com.example.Splitwise_Backend.PaymentSplit.Service;

import com.example.Splitwise_Backend.PaymentSplit.DTO.PaymentSplitDTO;
import com.example.Splitwise_Backend.PaymentSplit.Entity.PaymentSplit;

import java.util.List;
import java.util.Optional;

public interface PaymentSplitService {
    public PaymentSplit savePaymentSplit(int userId,int expenseId,double amountPaid);
    public List<PaymentSplitDTO> paymentDoneByUser(int userId);
    public String deletePaymentSplit(int expenseId);
    public List<PaymentSplitDTO> paymentDoneForExpense(int expenseId);


}
