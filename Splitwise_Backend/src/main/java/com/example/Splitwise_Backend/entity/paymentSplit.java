package com.example.Splitwise_Backend.entity;

import jakarta.persistence.*;

@Entity
public class paymentSplit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private double amountPaid;

    @ManyToOne
    @JoinColumn(name = "payer_id")
    private users users;

    @ManyToOne
    @JoinColumn(name = "expense_id")
    private expenses expenses;

    public paymentSplit(int id, double amountPaid, com.example.Splitwise_Backend.entity.users users, com.example.Splitwise_Backend.entity.expenses expenses) {
        this.id = id;
        this.amountPaid = amountPaid;
        this.users = users;
        this.expenses = expenses;
    }

    public paymentSplit() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(double amountPaid) {
        this.amountPaid = amountPaid;
    }

    public com.example.Splitwise_Backend.entity.users getUsers() {
        return users;
    }

    public void setUsers(com.example.Splitwise_Backend.entity.users users) {
        this.users = users;
    }

    public com.example.Splitwise_Backend.entity.expenses getExpenses() {
        return expenses;
    }

    public void setExpenses(com.example.Splitwise_Backend.entity.expenses expenses) {
        this.expenses = expenses;
    }

    @Override
    public String toString() {
        return "paymentSplit{" +
                "id=" + id +
                ", amountPaid=" + amountPaid +
                ", users=" + users +
                ", expenses=" + expenses +
                '}';
    }
}
