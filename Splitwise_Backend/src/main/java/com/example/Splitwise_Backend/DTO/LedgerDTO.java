package com.example.Splitwise_Backend.DTO;

public class LedgerDTO
{
    private String name;
    private float amount;

    public LedgerDTO(String name, float amount) {
        this.name = name;
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

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }
}
