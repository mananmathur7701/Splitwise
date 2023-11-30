package com.example.Splitwise_Backend.Expenses.DTO;

import java.sql.Timestamp;

public class ExpDTO {

    private int id;
    private Timestamp spentAt;
    private float amountPaid;
    private String comment;
    private String GroupName;

    public ExpDTO(int id,Timestamp spentAt, float amountPaid, String comment, String groupName) {
        this.id=id;
        this.spentAt = spentAt;
        this.amountPaid = amountPaid;
        this.comment = comment;
        GroupName = groupName;
    }

    public ExpDTO() {
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

    public String getGroupName() {
        return GroupName;
    }

    public void setGroupName(String groupName) {
        GroupName = groupName;
    }
}
