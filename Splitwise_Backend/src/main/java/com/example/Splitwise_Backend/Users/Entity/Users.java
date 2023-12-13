package com.example.Splitwise_Backend.Users.Entity;

import com.example.Splitwise_Backend.ExpenseSplit.Entity.ExpenseSplit;
import com.example.Splitwise_Backend.Friends.Entity.Friends;
import com.example.Splitwise_Backend.Groups.Entity.Groups;
import com.example.Splitwise_Backend.PaymentSplit.Entity.PaymentSplit;
import com.example.Splitwise_Backend.SquareOffTransactions.Entity.SquareOffTransactions;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.List;

@Entity
public class Users implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "f_name", nullable = false)
    @NotEmpty(message = "First name cannot be empty")
    private String firstName;

    @Column(name = "l_name")
    @NotEmpty(message = "Last name cannot be empty")
    private String lastName;

    @Column(name = "email", unique = true, nullable = false)
    @Email(message = "Please provide a valid email address")
    @NotEmpty(message = "Email cannot be empty")
    private String email;

    @Column(name = "number", nullable = false)
    @Size(min = 10, max = 10, message = "Number must have exactly 10 digits")
    @Pattern(regexp = "\\d+", message = "Number must contain only digits")
    private String number;

    @Column(name = "password", nullable = false)
    @NotEmpty(message = "Password cannot be empty")
    private String password;

    @ManyToMany(mappedBy = "users")
    @JsonIgnore
    private List<Groups> groups;

    @OneToMany(mappedBy = "userWhoAddedFriend")
    @JsonIgnore
    private List<Friends> userWhoAddedFriend;

    @OneToMany(mappedBy = "userWhoIsAddedAsFriend")
    @JsonIgnore
    private List<Friends> userWhoIsAddedAsFriend;

    @OneToMany(mappedBy = "users")
    @JsonIgnore
    private List<PaymentSplit> paymentSplit;

    @OneToMany(mappedBy = "payerId")
    @JsonIgnore
    private List<ExpenseSplit> expenseSplit1;

    @OneToMany(mappedBy = "payedToId")
    @JsonIgnore
    private List<ExpenseSplit> expenseSplit2;

    @OneToMany(mappedBy = "payerId")
    @JsonIgnore
    private List<SquareOffTransactions> squareOffTransactions1;

    @OneToMany(mappedBy = "payedToId")
    @JsonIgnore
    private List<SquareOffTransactions> squareOffTransactions2;

    @OneToMany(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH}, mappedBy = "createdBy")
    @JsonIgnore
    private List<Groups> groupsCreatedByUser;

    public Users(int id, String firstName, String lastName, String email, String number, String password, List<Groups> groups, List<Friends> userWhoAddedFriend, List<Friends> userWhoIsAddedAsFriend, List<PaymentSplit> paymentSplit, List<ExpenseSplit> expenseSplit1, List<ExpenseSplit> expenseSplit2, List<SquareOffTransactions> squareOffTransactions1, List<SquareOffTransactions> squareOffTransactions2, List<Groups> groupsCreatedByUser) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.number = number;
        this.password = password;
        this.groups = groups;
        this.userWhoAddedFriend = userWhoAddedFriend;
        this.userWhoIsAddedAsFriend = userWhoIsAddedAsFriend;
        this.paymentSplit = paymentSplit;
        this.expenseSplit1 = expenseSplit1;
        this.expenseSplit2 = expenseSplit2;
        this.squareOffTransactions1 = squareOffTransactions1;
        this.squareOffTransactions2 = squareOffTransactions2;
        this.groupsCreatedByUser = (List<Groups>) groupsCreatedByUser;
    }



    public Users() {
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Groups> getGroups() {
        return groups;
    }

    public void setGroups(List<Groups> groups) {
        this.groups = groups;
    }

    public List<PaymentSplit> getPaymentSplit() {
        return paymentSplit;
    }

    public void setPaymentSplit(List<PaymentSplit> paymentSplit) {
        this.paymentSplit = paymentSplit;
    }

    public List<ExpenseSplit> getExpenseSplit1() {
        return expenseSplit1;
    }

    public void setExpenseSplit1(List<ExpenseSplit> expenseSplit1) {
        this.expenseSplit1 = expenseSplit1;
    }

    public List<ExpenseSplit> getExpenseSplit2() {
        return expenseSplit2;
    }

    public void setExpenseSplit2(List<ExpenseSplit> expenseSplit2) {
        this.expenseSplit2 = expenseSplit2;
    }

    public List<SquareOffTransactions> getSquareOffTransactions1() {
        return squareOffTransactions1;
    }

    public void setSquareOffTransactions1(List<SquareOffTransactions> squareOffTransactions1) {
        this.squareOffTransactions1 = squareOffTransactions1;
    }

    public List<SquareOffTransactions> getSquareOffTransactions2() {
        return squareOffTransactions2;
    }

    public void setSquareOffTransactions2(List<SquareOffTransactions> squareOffTransactions2) {
        this.squareOffTransactions2 = squareOffTransactions2;
    }

    public Groups getGroups1() {
        return (Groups) groupsCreatedByUser;
    }

    public void setGroups1(Groups groupsCreatedByUser) {
        this.groupsCreatedByUser = (List<Groups>) groupsCreatedByUser;
    }

    public List<Friends> getUserWhoAddedFriend() {
        return userWhoAddedFriend;
    }

    public void setUserWhoAddedFriend(List<Friends> userWhoAddedFriend) {
        this.userWhoAddedFriend = userWhoAddedFriend;
    }

    public List<Friends> getUserWhoIsAddedAsFriend() {
        return userWhoIsAddedAsFriend;
    }

    public void setUserWhoIsAddedAsFriend(List<Friends> userWhoIsAddedAsFriend) {
        this.userWhoIsAddedAsFriend = userWhoIsAddedAsFriend;
    }

    public List<Groups> getGroupsCreatedByUser() {
        return groupsCreatedByUser;
    }

    public void setGroupsCreatedByUser(List<Groups> groupsCreatedByUser) {
        this.groupsCreatedByUser = groupsCreatedByUser;
    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", number='" + number + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
