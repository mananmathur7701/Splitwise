package com.example.Splitwise_Backend.Friends.Repository;
import com.example.Splitwise_Backend.Friends.Entity.Friends;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface FriendsRepo extends JpaRepository<Friends,Integer> {
    public Optional<List<Friends>> findByUserWhoAddedFriend_Id(int userId);
    public Optional<List<Friends>> findByUserWhoIsAddedAsFriend_Id(int userId);
}
