package com.example.Splitwise_Backend.Friends.Repository;

import com.example.Splitwise_Backend.Friends.Entity.Friends;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FriendsRepo extends JpaRepository<Friends,Integer> {
}
