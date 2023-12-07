package com.example.Splitwise_Backend.DTO;

public class LedgerDTO
{
    private int id;
    private String name;
    private String mailId;
    private float amount;

    public LedgerDTO(int id, String name, String mailId, float amount) {
        this.id = id;
        this.name = name;
        this.mailId = mailId;
        this.amount = amount;
    }

    public LedgerDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "LedgerDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", mailId='" + mailId + '\'' +
                ", amount=" + amount +
                '}';
    }
}
