package com.example.Splitwise_Backend.Expenses.DTO;

import com.example.Splitwise_Backend.Groups.Entity.Groups;

import java.sql.Timestamp;

public class expenseDTO {


        private Timestamp spentAt;
        private float amountPaid;
        private String comment;

        private Groups groups;


        // Getters and setters for the above fields


    public expenseDTO(Timestamp spentAt, float amountPaid, String comment, Groups groups) {
        this.spentAt = spentAt;
        this.amountPaid = amountPaid;
        this.comment = comment;
        this.groups = groups;
    }

    public expenseDTO() {
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

    @Override
    public String toString() {
        return "expenseDTO{" +
                "spentAt=" + spentAt+
                ", amountPaid=" + amountPaid +
                ", comment='" + comment + '\'' +
                ", groups=" + groups +
                '}';
    }
}
