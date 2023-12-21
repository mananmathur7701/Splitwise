package com.example.Splitwise_Backend.SquareOffTransactions.Service;

import com.example.Splitwise_Backend.SquareOffTransactions.DTO.SquareOffDTO;
import com.example.Splitwise_Backend.SquareOffTransactions.Entity.SquareOffTransactions;

import java.util.ArrayList;
import java.util.List;

public interface SquareOffTransactionsService
{
    public SquareOffTransactions addSquareOffTransaction(float amount, int payerId, int payedToId);
    public String deleteSquareOffTransactions(int transactionId);
    public SquareOffTransactions updateSquareOffTransactions(int transactionId,float amount, int payerId, int payedToId);
    public SquareOffTransactions viewSquareOffTransactions(int transactionId);

    List<SquareOffTransactions> viewTransactionListOfUser(int userId);

    public float totalAmountByPayerId(int payerId);
    public float totalAmountByPaidToId(int payedToId);
    //public ArrayList<String> dataToBeSentOnDashboard(int userId);
}
