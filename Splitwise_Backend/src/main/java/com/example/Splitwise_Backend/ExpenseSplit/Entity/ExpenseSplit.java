package com.example.Splitwise_Backend.ExpenseSplit.Entity;

import com.example.Splitwise_Backend.Expenses.Entity.Expenses;
import com.example.Splitwise_Backend.Users.Entity.Users;
import com.example.Splitwise_Backend.Groups.Entity.Groups;
import jakarta.persistence.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Entity
public class ExpenseSplit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "share_amount")
    @NotNull(message = "AmountPaid cannot be null")
    @Positive(message = "AmountPaid must be greater than 0")
    private float shareAmount;

    @ManyToOne
    @JoinColumn(name = "payerId")
    private Users payerId;

    @ManyToOne
    @JoinColumn(name = "payedToId")
    private Users payedToId;

    @ManyToOne
    @JoinColumn(name = "ExpenseId")
    private Expenses expenses;

   @ManyToOne
    @JoinColumn(name = "GroupId")
    private Groups groups;

    public ExpenseSplit(int id, float shareAmount, Users payerId, Users payedToId, Expenses expenses, Groups groups) {
        this.id = id;
        this.shareAmount = shareAmount;
        this.payerId = payerId;
        this.payedToId = payedToId;
        this.expenses = expenses;
        this.groups = groups;
    }

    public ExpenseSplit() {
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

    public Users getPayerId() {
        return payerId;
    }

    public void setPayerId(Users payerId) {
        this.payerId = payerId;
    }

    public Users getPayedToId() {
        return payedToId;
    }

    public void setPayedToId(Users payedToId) {
        this.payedToId = payedToId;
    }

    public Expenses getExpenses() {
        return expenses;
    }

    public void setExpenses(Expenses expenses) {
        this.expenses = expenses;
    }

    public Groups getGroups() {
        return groups;
    }

    public void setGroups(Groups groups) {
        this.groups = groups;
    }

    @Override
    public String toString() {
        return "expenseSplit{" +
                "id=" + id +
                ", shareAmount=" + shareAmount +
                ", payerId=" + payerId.getId() +
                ", payedToId=" + payedToId.getId() +
                ", expenses=" + expenses.getId() +
                ", groups=" + groups.getId() +
                '}';
    }
}
