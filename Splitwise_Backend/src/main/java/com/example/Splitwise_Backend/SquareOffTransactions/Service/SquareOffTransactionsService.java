package com.example.Splitwise_Backend.SquareOffTransactions.Service;

import com.example.Splitwise_Backend.SquareOffTransactions.Entity.SquareOffTransactions;

import java.util.ArrayList;

public interface SquareOffTransactionsService
{
    public SquareOffTransactions addSquareOffTransaction(float amount, int payerId, int payedToId);
    public String deleteSquareOffTransactions(int transactionId);
    public SquareOffTransactions updateSquareOffTransactions(int transactionId,float amount, int payerId, int payedToId);
    public SquareOffTransactions viewSquareOffTransactions(int transactionId);
    public float totalAmountByPayerId(int payerId);
    public float totalAmountByPaidToId(int payedToId);
    //public ArrayList<String> dataToBeSentOnDashboard(int userId);
}
