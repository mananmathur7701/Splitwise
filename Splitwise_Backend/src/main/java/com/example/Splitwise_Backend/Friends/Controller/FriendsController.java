package com.example.Splitwise_Backend.Friends.Controller;

import com.example.Splitwise_Backend.Friends.Entity.Friends;
import com.example.Splitwise_Backend.Friends.Service.FriendsServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
public class FriendsController
{
    private final FriendsServiceImplementation friendsServiceImplementation;

    @Autowired
    public FriendsController(FriendsServiceImplementation friendsServiceImplementation) {
        this.friendsServiceImplementation = friendsServiceImplementation;
    }

    @GetMapping("/friendsOfUser/{id}")
    @CrossOrigin("http://localhost:4200")
    public List<String> friendsOfUser(@PathVariable int id)
    {
        return friendsServiceImplementation.EmailsOfFriendsOfUser(id);
    }

}
