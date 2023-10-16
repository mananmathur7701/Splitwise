package com.example.Splitwise_Backend.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.List;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "shopId")
public class groups {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    
    private Timestamp createdAt;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "createdBy")
    private users createdBy;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    private Timestamp updatedAt;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "groupMembers",
            joinColumns = @JoinColumn(name = "users_id"),
            inverseJoinColumns = @JoinColumn(name = "groups_id")
    )
    private List<users> users;

    @OneToMany(mappedBy = "groups")
    private List<expenses> expenses;

    @OneToMany(mappedBy = "groups")
    private List<expenseSplit> expenseSplits;

    public groups(int id, Timestamp createdAt, Timestamp updatedAt, List<com.example.Splitwise_Backend.entity.users> users, List<com.example.Splitwise_Backend.entity.expenses> expenses, List<expenseSplit> expenseSplits) {
        this.id = id;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.users = users;
        this.expenses = expenses;
        this.expenseSplits = expenseSplits;
    }

    public groups() {
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

    public List<com.example.Splitwise_Backend.entity.users> getUsers() {
        return users;
    }

    public void setUsers(List<com.example.Splitwise_Backend.entity.users> users) {
        this.users = users;
    }

    public List<com.example.Splitwise_Backend.entity.expenses> getExpenses() {
        return expenses;
    }

    public void setExpenses(List<com.example.Splitwise_Backend.entity.expenses> expenses) {
        this.expenses = expenses;
    }

    public List<expenseSplit> getExpenseSplits() {
        return expenseSplits;
    }

    public void setExpenseSplits(List<expenseSplit> expenseSplits) {
        this.expenseSplits = expenseSplits;
    }

    @Override
    public String toString() {
        return "groups{" +
                "id=" + id +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", users=" + users +
                ", expenses=" + expenses +
                ", expenseSplits=" + expenseSplits +
                '}';
    }
}
