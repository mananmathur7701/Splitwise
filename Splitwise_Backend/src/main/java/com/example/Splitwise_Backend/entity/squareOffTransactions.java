package com.example.Splitwise_Backend.entity;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Date;

@Entity
public class squareOffTransactions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private double amount;

    private Timestamp time;

    @ManyToOne
    @JoinColumn(name = "payerId")
    private users payerId;

    @ManyToOne
    @JoinColumn(name = "payedToId")
    private users payedToId;

    public squareOffTransactions(int id, double amount, Timestamp time, users payerId, users payedToId) {
        this.id = id;
        this.amount = amount;
        this.time = time;
        this.payerId = payerId;
        this.payedToId = payedToId;
    }

    public squareOffTransactions() {
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
