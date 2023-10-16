package com.example.Splitwise_Backend.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "f_name")
    private String firstName;

    @Column(name = "l_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "number")
    private String number;
    
    @Column(name = "password")
    private String password;

    @ManyToMany(mappedBy = "users")
    private List<groups> groups;

    @OneToMany(mappedBy = "users")
    private List<paymentSplit> paymentSplit;

    @OneToMany(mappedBy = "payerId")
    private List<expenseSplit> expenseSplit1;

    @OneToMany(mappedBy = "payedToId")
    private List<expenseSplit> expenseSplit2;

    @OneToMany(mappedBy = "payerId")
    private List<squareOffTransactions> squareOffTransactions1;

    @OneToMany(mappedBy = "payedToId")
    private List<squareOffTransactions> squareOffTransactions2;

    @OneToOne(mappedBy = "createdBy")
    private groups groups1;

    public users(int id, String firstName, String lastName, String email, String number, String password, List<com.example.Splitwise_Backend.entity.groups> groups, List<com.example.Splitwise_Backend.entity.paymentSplit> paymentSplit, List<expenseSplit> expenseSplit1, List<expenseSplit> expenseSplit2, List<squareOffTransactions> squareOffTransactions1, List<squareOffTransactions> squareOffTransactions2, groups groups1) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.number = number;
        this.password = password;
        this.groups = groups;
        this.paymentSplit = paymentSplit;
        this.expenseSplit1 = expenseSplit1;
        this.expenseSplit2 = expenseSplit2;
        this.squareOffTransactions1 = squareOffTransactions1;
        this.squareOffTransactions2 = squareOffTransactions2;
        this.groups1=groups1;
    }

    public users() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<com.example.Splitwise_Backend.entity.groups> getGroups() {
        return groups;
    }

    public void setGroups(List<com.example.Splitwise_Backend.entity.groups> groups) {
        this.groups = groups;
    }

    public List<com.example.Splitwise_Backend.entity.paymentSplit> getPaymentSplit() {
        return paymentSplit;
    }

    public void setPaymentSplit(List<com.example.Splitwise_Backend.entity.paymentSplit> paymentSplit) {
        this.paymentSplit = paymentSplit;
    }

    public List<expenseSplit> getExpenseSplit1() {
        return expenseSplit1;
    }

    public void setExpenseSplit1(List<expenseSplit> expenseSplit1) {
        this.expenseSplit1 = expenseSplit1;
    }

    public List<expenseSplit> getExpenseSplit2() {
        return expenseSplit2;
    }

    public void setExpenseSplit2(List<expenseSplit> expenseSplit2) {
        this.expenseSplit2 = expenseSplit2;
    }

    public List<squareOffTransactions> getSquareOffTransactions1() {
        return squareOffTransactions1;
    }

    public void setSquareOffTransactions1(List<squareOffTransactions> squareOffTransactions1) {
        this.squareOffTransactions1 = squareOffTransactions1;
    }

    public List<squareOffTransactions> getSquareOffTransactions2() {
        return squareOffTransactions2;
    }

    public void setSquareOffTransactions2(List<squareOffTransactions> squareOffTransactions2) {
        this.squareOffTransactions2 = squareOffTransactions2;
    }

    public com.example.Splitwise_Backend.entity.groups getGroups1() {
        return groups1;
    }

    public void setGroups1(com.example.Splitwise_Backend.entity.groups groups1) {
        this.groups1 = groups1;
    }

    @Override
    public String toString() {
        return "users{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", number='" + number + '\'' +
                ", password='" + password + '\'' +
                ", groups=" + groups +
                ", paymentSplit=" + paymentSplit +
                ", expenseSplit1=" + expenseSplit1 +
                ", expenseSplit2=" + expenseSplit2 +
                ", squareOffTransactions1=" + squareOffTransactions1 +
                ", squareOffTransactions2=" + squareOffTransactions2 +
                ", groups1=" + groups1 +
                '}';
    }
}
