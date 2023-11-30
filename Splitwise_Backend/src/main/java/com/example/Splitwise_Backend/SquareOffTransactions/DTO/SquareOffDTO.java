package com.example.Splitwise_Backend.SquareOffTransactions.DTO;

import java.sql.Timestamp;

public class SquareOffDTO
{
    private int id;
    private double amount;
    private Timestamp time;
    private int payerId;
    private String payerEmail;
    private int payedToId;
    private String payedToEmail;

    public SquareOffDTO(int id, double amount, Timestamp time, int payerId, String payerEmail, int payedToId, String payedToEmail) {
        this.id = id;
        this.amount = amount;
        this.time = time;
        this.payerId = payerId;
        this.payerEmail = payerEmail;
        this.payedToId = payedToId;
        this.payedToEmail = payedToEmail;
    }

    public SquareOffDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public int getPayerId() {
        return payerId;
    }

    public void setPayerId(int payerId) {
        this.payerId = payerId;
    }

    public String getPayerEmail() {
        return payerEmail;
    }

    public void setPayerEmail(String payerEmail) {
        this.payerEmail = payerEmail;
    }

    public int getPayedToId() {
        return payedToId;
    }

    public void setPayedToId(int payedToId) {
        this.payedToId = payedToId;
    }

    public String getPayedToEmail() {
        return payedToEmail;
    }

    public void setPayedToEmail(String payedToEmail) {
        this.payedToEmail = payedToEmail;
    }

    @Override
    public String toString() {
        return "SquareOffDTO{" +
                "id=" + id +
                ", amount=" + amount +
                ", time=" + time +
                ", payerId=" + payerId +
                ", payerEmail='" + payerEmail + '\'' +
                ", payedToId=" + payedToId +
                ", payedToEmail='" + payedToEmail + '\'' +
                '}';
    }
}
