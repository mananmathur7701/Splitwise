package com.example.Splitwise_Backend.entity;

import jakarta.persistence.*;

@Entity
public class expenseSplit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "share_amount")
    private float shareAmount;

    @ManyToOne
    @JoinColumn(name = "payerId")
    private users payerId;

    @ManyToOne
    @JoinColumn(name = "payedToId")
    private users payedToId;

    @ManyToOne
    @JoinColumn(name = "ExpenseId")
    private expenses expenses;

   @ManyToOne
    @JoinColumn(name = "GroupId")
    private groups groups;

    public expenseSplit(int id, float shareAmount, users payerId, users payedToId, com.example.Splitwise_Backend.entity.expenses expenses, com.example.Splitwise_Backend.entity.groups groups) {
        this.id = id;
        this.shareAmount = shareAmount;
        this.payerId = payerId;
        this.payedToId = payedToId;
        this.expenses = expenses;
        this.groups = groups;
    }

    public expenseSplit() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getShareAmount() {
        return shareAmount;
    }

    public void setShareAmount(float shareAmount) {
        this.shareAmount = shareAmount;
    }

    public users getPayerId() {
        return payerId;
    }

    public void setPayerId(users payerId) {
        this.payerId = payerId;
    }

    public users getPayedToId() {
        return payedToId;
    }

    public void setPayedToId(users payedToId) {
        this.payedToId = payedToId;
    }

    public com.example.Splitwise_Backend.entity.expenses getExpenses() {
        return expenses;
    }

    public void setExpenses(com.example.Splitwise_Backend.entity.expenses expenses) {
        this.expenses = expenses;
    }

    public com.example.Splitwise_Backend.entity.groups getGroups() {
        return groups;
    }

    public void setGroups(com.example.Splitwise_Backend.entity.groups groups) {
        this.groups = groups;
    }

    @Override
    public String toString() {
        return "expenseSplit{" +
                "id=" + id +
                ", shareAmount=" + shareAmount +
                ", payerId=" + payerId +
                ", payedToId=" + payedToId +
                ", expenses=" + expenses +
                ", groups=" + groups +
                '}';
    }
}
