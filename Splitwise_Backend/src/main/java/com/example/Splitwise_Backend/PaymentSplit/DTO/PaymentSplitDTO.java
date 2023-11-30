package com.example.Splitwise_Backend.PaymentSplit.DTO;

public class PaymentSplitDTO {
    private int id;
    private double amount;
    private int expId;
    private String expenseName;
    private String groupName;
    private int userId;

    public PaymentSplitDTO(int id, double amount, int expId, String expenseName, String groupName, int userId) {
        this.id = id;
        this.amount = amount;
        this.expId = expId;
        this.expenseName = expenseName;
        this.groupName = groupName;
        this.userId = userId;
    }

    public PaymentSplitDTO() {
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

    public int getExpId() {
        return expId;
    }

    public void setExpId(int expId) {
        this.expId = expId;
    }

    public String getExpenseName() {
        return expenseName;
    }

    public void setExpenseName(String expenseName) {
        this.expenseName = expenseName;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "PaymentSplitDTO{" +
                "id=" + id +
                ", amount=" + amount +
                ", expId=" + expId +
                ", expenseName='" + expenseName + '\'' +
                ", groupName='" + groupName + '\'' +
                ", userId=" + userId +
                '}';
    }
}
