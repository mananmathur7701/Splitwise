package com.example.Splitwise_Backend.ExpenseSplit.Service;

import com.example.Splitwise_Backend.ExpenseSplit.DTO.ExpenseSplitDTO;
import com.example.Splitwise_Backend.ExpenseSplit.Entity.ExpenseSplit;
import com.example.Splitwise_Backend.PaymentSplit.Entity.PaymentSplit;

import java.util.List;

public interface ExpenseSplitService {
    public ExpenseSplit saveExpenseSplit(int payerId, int payedToId, int groupId, int expenseId, float amountPaid);
    public String deleteExpenseSplit(int expenseId);
    public List<ExpenseSplitDTO> amountToBeRecievedByYou(int userId);
    public List<ExpenseSplitDTO> amountToBeGivenByYou(int userId);
    public List<ExpenseSplitDTO> expenseSplitOfAllGroups(int groupId);
    public List<ExpenseSplitDTO> expenseSplitOfParticularExpenseId(int expenseId);
    public float totalAmountJoDenaHaiByPayerId(int payerId);
    public float totalAmountJoLenaHaiByPaidToId(int payedToId);
}
