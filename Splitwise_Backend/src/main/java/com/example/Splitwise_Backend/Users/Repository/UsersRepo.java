package com.example.Splitwise_Backend.Users.Repository;

import com.example.Splitwise_Backend.Users.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepo extends JpaRepository<Users,Integer> {
}
