package com.example.Splitwise_Backend.SquareOffTransactions.Entity;

import com.example.Splitwise_Backend.Users.Entity.Users;
import jakarta.persistence.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.sql.Timestamp;
import java.util.Date;

@Entity
public class SquareOffTransactions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull(message = "AmountPaid cannot be null")
    @Positive(message = "AmountPaid must be greater than 0")
    private double amount;

    private Timestamp time;

    @ManyToOne
    @JoinColumn(name = "payerId")
    private Users payerId;

    @ManyToOne
    @JoinColumn(name = "payedToId")
    private Users payedToId;

    public SquareOffTransactions(int id, double amount, Timestamp time, Users payerId, Users payedToId) {
        this.id = id;
        this.amount = amount;
        this.time = time;
        this.payerId = payerId;
        this.payedToId = payedToId;
    }

    public SquareOffTransactions() {
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

    public Date getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
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

    @Override
    public String toString() {
        return "squareOffTransactions{" +
                "id=" + id +
                ", amount=" + amount +
                ", time=" + time +
                ", payerId=" + payerId +
                ", payedToId=" + payedToId +
                '}';
    }
}
