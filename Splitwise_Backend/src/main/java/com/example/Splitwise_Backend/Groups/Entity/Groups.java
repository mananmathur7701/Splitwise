package com.example.Splitwise_Backend.Groups.Entity;

import com.example.Splitwise_Backend.ExpenseSplit.Entity.ExpenseSplit;
import com.example.Splitwise_Backend.Expenses.Entity.Expenses;
import com.example.Splitwise_Backend.Users.Entity.Users;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.List;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "shopId")
public class Groups {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    
    private Timestamp createdAt;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "createdBy")
    private Users createdBy;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    private Timestamp updatedAt;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "groupMembers",
            joinColumns = @JoinColumn(name = "users_id"),
            inverseJoinColumns = @JoinColumn(name = "groups_id")
    )
    private List<Users> users;

    @OneToMany(mappedBy = "groups")
    private List<Expenses> expenses;

    @OneToMany(mappedBy = "groups")
    private List<ExpenseSplit> ExpenseSplits;

    public Groups(int id, Timestamp createdAt, Timestamp updatedAt, List<Users> users, List<Expenses> expenses, List<ExpenseSplit> ExpenseSplits) {
        this.id = id;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.users = users;
        this.expenses = expenses;
        this.ExpenseSplits = ExpenseSplits;
    }

    public Groups() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public List<Users> getUsers() {
        return users;
    }

    public void setUsers(List<Users> users) {
        this.users = users;
    }

    public List<Expenses> getExpenses() {
        return expenses;
    }

    public void setExpenses(List<Expenses> expenses) {
        this.expenses = expenses;
    }

    public List<ExpenseSplit> getExpenseSplits() {
        return ExpenseSplits;
    }

    public void setExpenseSplits(List<ExpenseSplit> ExpenseSplits) {
        this.ExpenseSplits = ExpenseSplits;
    }

    @Override
    public String toString() {
        return "groups{" +
                "id=" + id +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", users=" + users +
                ", expenses=" + expenses +
                ", expenseSplits=" + ExpenseSplits +
                '}';
    }
}
