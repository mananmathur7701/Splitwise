package com.example.Splitwise_Backend.entity;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.List;

@Entity
public class expenses {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp spentAt;
    private float amountPaid;
    private String comment;

    @ManyToOne
    @JoinColumn(name = "groups_id")
    private groups groups;

    @OneToMany(mappedBy = "expenses")
    private List<expenseSplit> expenseSplit;

    @OneToMany(mappedBy = "expenses")
    private List<paymentSplit> paymentSplit;

    public expenses(int id, Timestamp spentAt, float amountPaid, String comment, com.example.Splitwise_Backend.entity.groups groups, List<com.example.Splitwise_Backend.entity.expenseSplit> expenseSplit, List<com.example.Splitwise_Backend.entity.paymentSplit> paymentSplit) {
        this.id = id;
        this.spentAt = spentAt;
        this.amountPaid = amountPaid;
        this.comment = comment;
        this.groups = groups;
        this.expenseSplit = expenseSplit;
        this.paymentSplit = paymentSplit;
    }

    public expenses() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getSpentAt() {
        return spentAt;
    }

    public void setSpentAt(Timestamp spentAt) {
        this.spentAt = spentAt;
    }

    public float getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(float amountPaid) {
        this.amountPaid = amountPaid;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public com.example.Splitwise_Backend.entity.groups getGroups() {
        return groups;
    }

    public void setGroups(com.example.Splitwise_Backend.entity.groups groups) {
        this.groups = groups;
    }

    public List<com.example.Splitwise_Backend.entity.expenseSplit> getExpenseSplit() {
        return expenseSplit;
    }

    public void setExpenseSplit(List<com.example.Splitwise_Backend.entity.expenseSplit> expenseSplit) {
        this.expenseSplit = expenseSplit;
    }

    public List<com.example.Splitwise_Backend.entity.paymentSplit> getPaymentSplit() {
        return paymentSplit;
    }

    public void setPaymentSplit(List<com.example.Splitwise_Backend.entity.paymentSplit> paymentSplit) {
        this.paymentSplit = paymentSplit;
    }

    @Override
    public String toString() {
        return "expenses{" +
                "id=" + id +
                ", spentAt=" + spentAt +
                ", amountPaid=" + amountPaid +
                ", comment='" + comment + '\'' +
                ", groups=" + groups +
                ", expenseSplit=" + expenseSplit +
                ", paymentSplit=" + paymentSplit +
                '}';
    }
}
