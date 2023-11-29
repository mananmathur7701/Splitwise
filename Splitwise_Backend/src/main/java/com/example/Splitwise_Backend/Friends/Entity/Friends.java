package com.example.Splitwise_Backend.Friends.Entity;

import com.example.Splitwise_Backend.Users.Entity.Users;
import jakarta.persistence.*;

import java.sql.Timestamp;


@Entity
public class Friends {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name = "userWhoAddedFriend_id")
    private Users userWhoAddedFriend;

    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name = "userWhoIsAddedAsFriend_id")
    private Users userWhoIsAddedAsFriend;

    public Friends(int id, Users userWhoAddedFriend, Users userWhoIsAddedAsFriend) {
        this.id = id;
        this.userWhoAddedFriend = userWhoAddedFriend;
        this.userWhoIsAddedAsFriend = userWhoIsAddedAsFriend;
    }

    public Friends() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Users getUserWhoAddedFriend() {
        return userWhoAddedFriend;
    }

    public void setUserWhoAddedFriend(Users userWhoAddedFriend) {
        this.userWhoAddedFriend = userWhoAddedFriend;
    }

    public Users getUserWhoIsAddedAsFriend() {
        return userWhoIsAddedAsFriend;
    }

    public void setUserWhoIsAddedAsFriend(Users userWhoIsAddedAsFriend) {
        this.userWhoIsAddedAsFriend = userWhoIsAddedAsFriend;
    }

    @Override
    public String toString() {
        return "Friends{" +
                "id=" + id +
                ", userWhoAddedFriend=" + userWhoAddedFriend.getId() +
                ", userWhoIsAddedAsFriend=" + userWhoIsAddedAsFriend.getId() +
                '}';
    }
}
