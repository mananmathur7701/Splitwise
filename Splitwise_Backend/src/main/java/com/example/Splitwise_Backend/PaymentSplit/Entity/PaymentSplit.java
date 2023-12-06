package com.example.Splitwise_Backend.PaymentSplit.Entity;

import com.example.Splitwise_Backend.Expenses.Entity.Expenses;
import com.example.Splitwise_Backend.Users.Entity.Users;
import jakarta.persistence.*;
import javax.validation.constraints.Positive;
import javax.validation.constraints.NotNull;

@Entity
public class PaymentSplit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull(message = "AmountPaid cannot be null")
    @Positive(message = "AmountPaid must be greater than 0")
    private double amountPaid;

    @ManyToOne
    @JoinColumn(name = "payer_id")
    private Users users;

    @ManyToOne
    @JoinColumn(name = "expense_id")
    private Expenses expenses;

    public PaymentSplit(int id, double amountPaid, Users users, Expenses expenses) {
        this.id = id;
        this.amountPaid = amountPaid;
        this.users = users;
        this.expenses = expenses;
    }

    public PaymentSplit() {
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

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public Expenses getExpenses() {
        return expenses;
    }

    public void setExpenses(Expenses expenses) {
        this.expenses = expenses;
    }

    @Override
    public String toString() {
        return "paymentSplit{" +
                "id=" + id +
                ", amountPaid=" + amountPaid +
                ", users=" + users.getId() +
                ", expenses=" + expenses.getId() +
                '}';
    }
}
