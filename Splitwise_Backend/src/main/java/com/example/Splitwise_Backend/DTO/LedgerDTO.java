package com.example.Splitwise_Backend.DTO;

public class LedgerDTO
{
    private String name;
    private String mailId;
    private float amount;

    public LedgerDTO(String name, String mailId, float amount) {
        this.name = name;
        this.mailId = mailId;
        this.amount = amount;
    }

    public LedgerDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMailId() {
        return mailId;
    }

    public void setMailId(String mailId) {
        this.mailId = mailId;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }
}
