package com.example.Splitwise_Backend.Groups.Entity;

import com.example.Splitwise_Backend.ExpenseSplit.Entity.ExpenseSplit;
import com.example.Splitwise_Backend.Expenses.Entity.Expenses;
import com.example.Splitwise_Backend.Users.Entity.Users;
import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.List;

@Entity
//@JsonIgnoreProperties({"expenses","ExpenseSplit","createdBy"})
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Groups {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    
    private Timestamp createdAt;
    private String groupName;

    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name = "createdBy")
    @JsonIgnore
    private Users createdBy;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    private Timestamp updatedAt;

    @ManyToMany(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JsonIgnore
    @JoinTable(
            name = "groupMembers",
            joinColumns = @JoinColumn(name = "groups_id"),
            inverseJoinColumns = @JoinColumn(name = "users_id")
    )
    private List<Users> users;

    @OneToMany(mappedBy = "groups")
    @JsonIgnore
    private List<Expenses> expenses;

    @OneToMany(mappedBy = "groups")
    @JsonIgnore
    private List<ExpenseSplit> ExpenseSplits;


    public Groups(int id, Timestamp createdAt, String groupName, Users createdBy, Timestamp updatedAt, List<Users> users, List<Expenses> expenses, List<ExpenseSplit> expenseSplits) {
        this.id = id;
        this.createdAt = createdAt;
        this.groupName = groupName;
        this.createdBy = createdBy;
        this.updatedAt = updatedAt;
        this.users = users;
        this.expenses = expenses;
        ExpenseSplits = expenseSplits;
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

    public Users getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Users createdBy) {
        this.createdBy = createdBy;
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

    public void setExpenseSplits(List<ExpenseSplit> expenseSplits) {
        ExpenseSplits = expenseSplits;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    @Override
    public String toString() {
        return "Groups{" +
                "id=" + id +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", groupName=" + groupName +
//                ", expenses=" + expenses +
//                ", ExpenseSplits=" + ExpenseSplits +
                '}';
    }
}
