package com.example.Splitwise_Backend.DTO;

import java.sql.Timestamp;
import java.util.List;

public class ExpenseInfoDTO {
    private int groupId;
    private float amountPaid;
    private Timestamp spentAt;
    private String comment;
    private List<SplitData> payee;
    private List<SplitData> settlement;


    public ExpenseInfoDTO() {
    }

//    public ExpenseInfoDTO(int groupId, float amountPaid, String comment, List<SplitData> payee, List<SplitData> settelment) {
//        this.groupId = groupId;
//        this.amountPaid = amountPaid;
//        this.comment = comment;
//        this.payee = payee;
//        this.settlement = settelment;
//    }

    public ExpenseInfoDTO(int groupId, float amountPaid, Timestamp spentAt, String comment, List<SplitData> payee, List<SplitData> settlement) {
        this.groupId = groupId;
        this.amountPaid = amountPaid;
        this.spentAt = spentAt;
        this.comment = comment;
        this.payee = payee;
        this.settlement = settlement;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
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

    public List<SplitData> getPayee() {
        return payee;
    }

    public void setPayee(List<SplitData> payee) {
        this.payee = payee;
    }

    public List<SplitData> getSettlement() {
        return settlement;
    }

    public void setSettlement(List<SplitData> settlement) {
        this.settlement = settlement;
    }

    public Timestamp getSpentAt() {
        return spentAt;
    }

    public void setSpentAt(Timestamp spentAt) {
        this.spentAt = spentAt;
    }

    @Override
    public String toString() {
        return "ExpenseInfoDTO{" +
                "groupId=" + groupId +
                ", amountPaid=" + amountPaid +
                ", spentAt=" + spentAt +
                ", comment='" + comment + '\'' +
                ", payee=" + payee +
                ", settlement=" + settlement +
                '}';
    }
}

