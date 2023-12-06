package com.example.Splitwise_Backend.Expenses.Entity;

import com.example.Splitwise_Backend.ExpenseSplit.Entity.ExpenseSplit;
import com.example.Splitwise_Backend.Groups.Entity.Groups;
import com.example.Splitwise_Backend.PaymentSplit.Entity.PaymentSplit;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import javax.validation.constraints.NotEmpty;
import java.sql.Timestamp;
import javax.validation.constraints.Positive;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class Expenses {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp spentAt;
    @NotNull(message = "AmountPaid cannot be null")
    @Positive(message = "AmountPaid must be greater than 0")
    private float amountPaid;
    @NotEmpty(message = "What Paid For Comment cannot be empty")
    private String comment;

    @ManyToOne
    @JoinColumn(name = "groups_id")
    @JsonIgnoreProperties("expenses")
    private Groups groups;

    @OneToMany(mappedBy = "expenses")
    private List<ExpenseSplit> expenseSplit;

    @OneToMany(mappedBy = "expenses")
    private List<PaymentSplit> paymentSplit;

    public Expenses(int id, Timestamp spentAt, float amountPaid, String comment, Groups groups, List<ExpenseSplit> expenseSplit, List<PaymentSplit> paymentSplit) {
        this.id = id;
        this.spentAt = spentAt;
        this.amountPaid = amountPaid;
        this.comment = comment;
        this.groups = groups;
        this.expenseSplit = expenseSplit;
        this.paymentSplit = paymentSplit;
    }

    public Expenses() {
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

    public Groups getGroups() {
        return groups;
    }

    public void setGroups(Groups groups) {
        this.groups = groups;
    }

    public List<ExpenseSplit> getExpenseSplit() {
        return expenseSplit;
    }

    public void setExpenseSplit(List<ExpenseSplit> expenseSplit) {
        this.expenseSplit = expenseSplit;
    }

    public List<PaymentSplit> getPaymentSplit() {
        return paymentSplit;
    }

    public void setPaymentSplit(List<PaymentSplit> paymentSplit) {
        this.paymentSplit = paymentSplit;
    }

    @Override
    public String toString() {
        return "expenses{" +
                "id=" + id +
                ", comment='" + comment + '\'' +
                ", spentAt=" + spentAt +
                ", amountPaid=" + amountPaid +

                ", groups=" + groups.getId() +
//                ", expenseSplit=" + expenseSplit+
//                ", paymentSplit=" + paymentSplit +
                '}';
    }
}
