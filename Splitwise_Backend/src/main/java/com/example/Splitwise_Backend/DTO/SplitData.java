package com.example.Splitwise_Backend.DTO;

public class SplitData {
    private int userId;
    private float amount;

    public SplitData(int userId, float amount) {
        this.userId = userId;
        this.amount = amount;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "splitData{" +
                "userId=" + userId +
                ", amount=" + amount +
                '}';
    }
}
