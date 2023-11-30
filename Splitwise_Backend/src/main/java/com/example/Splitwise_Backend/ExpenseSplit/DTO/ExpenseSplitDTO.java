package com.example.Splitwise_Backend.ExpenseSplit.DTO;

public class ExpenseSplitDTO
{
    private int id;
    private float amount;
    private int expenseId;
    private String expenseName;
    private int groupId;
    private String groupName;
    private int payeeId;
    private String payeeMail;
    private int payerId;
    private String payerMail;

    public ExpenseSplitDTO(int id, float amount, int expenseId, String expenseName, int groupId, String groupName, int payeeId, String payeeMail, int payerId, String payerMail) {
        this.id = id;
        this.amount = amount;
        this.expenseId = expenseId;
        this.expenseName = expenseName;
        this.groupId = groupId;
        this.groupName = groupName;
        this.payeeId = payeeId;
        this.payeeMail = payeeMail;
        this.payerId = payerId;
        this.payerMail = payerMail;
    }

    public ExpenseSplitDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public int getExpenseId() {
        return expenseId;
    }

    public void setExpenseId(int expenseId) {
        this.expenseId = expenseId;
    }

    public String getExpenseName() {
        return expenseName;
    }

    public void setExpenseName(String expenseName) {
        this.expenseName = expenseName;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public int getPayeeId() {
        return payeeId;
    }

    public void setPayeeId(int payeeId) {
        this.payeeId = payeeId;
    }

    public String getPayeeMail() {
        return payeeMail;
    }

    public void setPayeeMail(String payeeMail) {
        this.payeeMail = payeeMail;
    }

    public int getPayerId() {
        return payerId;
    }

    public void setPayerId(int payerId) {
        this.payerId = payerId;
    }

    public String getPayerMail() {
        return payerMail;
    }

    public void setPayerMail(String payerMail) {
        this.payerMail = payerMail;
    }

    @Override
    public String toString() {
        return "ExpenseSplitDTO{" +
                "id=" + id +
                ", amount=" + amount +
                ", expenseId=" + expenseId +
                ", expenseName='" + expenseName + '\'' +
                ", groupId=" + groupId +
                ", groupName='" + groupName + '\'' +
                ", payeeId=" + payeeId +
                ", payeeMail='" + payeeMail + '\'' +
                ", payerId=" + payerId +
                ", payerMail='" + payerMail + '\'' +
                '}';
    }
}
