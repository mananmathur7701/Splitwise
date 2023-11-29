package com.example.Splitwise_Backend.Friends.Service;

import com.example.Splitwise_Backend.Friends.Entity.Friends;

import java.util.List;
import java.util.Optional;

public interface FriendsService
{
    public void addFriends(int userIdWhoAddingFriend1,int userIdWhoIsAddedAsFriend);
    public List<Friends> friendsOfUser(int userId);
    public List<String> EmailsOfFriendsOfUser(int userId);
    public List<Integer> idOfFriendsOfUsers(int userId);
}
